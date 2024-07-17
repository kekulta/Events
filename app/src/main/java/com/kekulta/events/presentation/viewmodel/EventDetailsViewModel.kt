package com.kekulta.events.presentation.viewmodel

import android.os.Parcelable
import com.kekulta.events.presentation.ui.loremIpsum
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.filterNotNull
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.mapLatest
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.withContext
import kotlinx.datetime.Clock
import kotlinx.datetime.DateTimeUnit
import kotlinx.datetime.LocalDateTime
import kotlinx.datetime.TimeZone
import kotlinx.datetime.format
import kotlinx.datetime.format.DateTimeFormat
import kotlinx.datetime.format.char
import kotlinx.datetime.plus
import kotlinx.datetime.toLocalDateTime
import kotlinx.parcelize.Parcelize
import kotlinx.serialization.Serializable
import logcat.logcat

fun <T, M> StateFlow<T>.map(
    coroutineScope: CoroutineScope, mapper: (value: T) -> M
): StateFlow<M> = map { mapper(it) }.stateIn(
    coroutineScope, SharingStarted.Eagerly, mapper(value)
)

@JvmInline
value class UserId(val id: String)

@JvmInline
@Parcelize
@Serializable
value class EventId(val id: String) : Parcelable

data class EventModel(
    val id: EventId,
    val name: String,
    val description: String,
    val avatarUrl: Avatar,
    val mapUrl: String,
    val tags: List<String>,
    val date: LocalDateTime,
    // Should be some kind of geo tag probably
    val location: String,
    val attendees: List<UserId>
)

data class UserModel(
    val id: UserId,
    val name: String,
    val avatarUrl: String?,
)

@JvmInline
value class AccessToken(val token: String)

data class ProfileModel(
    val id: UserId,
    val accessToken: AccessToken,
    val number: String,
    val name: String,
    val surname: String?,
    val avatar: Avatar,
)

class ProfileRepositoryMock : ProfileRepository {
    private val profile = MutableStateFlow<ProfileModel?>(
        ProfileModel(
            id = UserId("2"),
            accessToken = MockToken,
            number = "+79959177242",
            name = "Ruslan",
            surname = "Russkikh",
            avatar = Avatar(null),
        )
    )

    override fun observeCurrentProfile(): StateFlow<ProfileModel?> = profile.asStateFlow()

    override suspend fun getCurrentProfile(): ProfileModel? = profile.value

    override suspend fun sendVerification(number: String): Boolean {
        return true
    }

    override suspend fun checkVerification(number: String, code: String): AccessToken {
        return MockToken
    }

    override suspend fun createProfile(profile: ProfileModel, token: AccessToken): Boolean {
        this.profile.update { profile }
        return true
    }

    companion object {
        private val MockToken = AccessToken("Mock Token")
    }
}

class EventsRepositoryMock : EventsRepository {
    private val eventsMap = mockEventModels(25).associateBy { event -> event.id }.toMutableMap()
    private val events = MutableStateFlow(eventsMap.values.toSet())

    override fun observeEventDetails(id: EventId): Flow<EventModel?> {
        return events.map { events -> events.firstOrNull { event -> event.id == id } }
    }

    override suspend fun registerForEvent(id: EventId, userId: UserId): Boolean {
        val event = eventsMap[id] ?: return false
        val isRegistered = event.attendees.contains(userId)
        if (isRegistered) {
            return false
        }
        eventsMap[id] = event.copy(attendees = event.attendees + userId)
        events.update { eventsMap.values.toSet() }

        return true
    }

    override suspend fun cancelRegistration(id: EventId, userId: UserId): Boolean {
        val event = eventsMap[id] ?: return false
        val attendees = event.attendees.filterNot { user -> user.id == userId.id }
        if (attendees.size == event.attendees.size) {
            return false
        }
        eventsMap[id] = event.copy(attendees = attendees)
        events.update { eventsMap.values.toSet() }

        return true
    }
}

class UsersRepositoryMock : UsersRepository {
    private val users = MutableStateFlow(mockUsers(50))

    override fun observeUser(id: UserId): Flow<UserModel?> {
        return users.map { users -> users.firstOrNull { user -> user.id == id } }
    }

    override fun observeUsers(ids: List<UserId>): Flow<List<UserModel>> {
        val requestedIds = ids.toSet()

        return users.map { users -> users.filter { user -> requestedIds.contains(user.id) } }
    }
}

interface EventsRepository {
    fun observeEventDetails(id: EventId): Flow<EventModel?>
    suspend fun registerForEvent(id: EventId, userId: UserId): Boolean
    suspend fun cancelRegistration(id: EventId, userId: UserId): Boolean
}

interface ProfileRepository {
    fun observeCurrentProfile(): StateFlow<ProfileModel?>
    suspend fun getCurrentProfile(): ProfileModel?
    suspend fun sendVerification(number: String): Boolean
    suspend fun checkVerification(number: String, code: String): AccessToken
    suspend fun createProfile(profile: ProfileModel, token: AccessToken): Boolean
}

interface UsersRepository {
    fun observeUser(id: UserId): Flow<UserModel?>
    fun observeUsers(ids: List<UserId>): Flow<List<UserModel>>
}

class EventDetailsUseCase(
    private val profileRepository: ProfileRepository,
    private val eventsRepository: EventsRepository,
    private val usersRepository: UsersRepository,
    private val eventDetailsFormatter: EventDetailsFormatter,
) {
    @OptIn(ExperimentalCoroutinesApi::class)
    fun execute(
        id: EventId,
    ): Flow<EventDetailsVo?> {
        return combine(
            profileRepository.observeCurrentProfile(), eventsRepository.observeEventDetails(id)
        ) { profile, event ->
            if (event != null) {
                usersRepository.observeUsers(event.attendees).mapLatest { users ->
                    eventDetailsFormatter.format(event, users, profile)
                }
            } else {
                flow<EventDetailsVo?> { emit(null) }
            }
        }.flattenLatest()
    }
}

class EventRegistrationUseCase(
    private val profileRepository: ProfileRepository,
    private val eventsRepository: EventsRepository,
) {
    suspend fun register(id: EventId): Boolean {
        return withContext(Dispatchers.IO) {
            val userId = profileRepository.getCurrentProfile()?.id
            return@withContext userId?.let { eventsRepository.registerForEvent(id, it) } ?: false
        }
    }

    suspend fun cancel(id: EventId): Boolean {
        return withContext(Dispatchers.IO) {
            val userId = profileRepository.getCurrentProfile()?.id
            return@withContext userId?.let { eventsRepository.cancelRegistration(id, it) } ?: false
        }
    }
}

class EventDetailsFormatter {
    fun format(
        event: EventModel,
        users: List<UserModel>,
        profile: ProfileModel?,
        format: DateTimeFormat<LocalDateTime> = DateFormat,
    ): EventDetailsVo {
        val missingUsersCount = (event.attendees.size - users.size).coerceAtLeast(0)
        val avatars =
            (users.map { user -> user.avatarUrl } + arrayOfNulls(missingUsersCount)).map { url ->
                Avatar(url)
            }
        val isAttending =
            profile?.id != null && users.firstOrNull { user -> user.id == profile.id } != null

        return EventDetailsVo(
            name = event.name,
            description = event.description,
            dateLocation = "${event.date.format(format)} — ${event.location}",
            tags = event.tags,
            mapUrl = event.mapUrl,
            attendees = avatars,
            isAttending = isAttending,
        )
    }

    companion object {
        private val DateFormat = LocalDateTime.Format {
            dayOfMonth()
            char('.')
            monthNumber()
            char('.')
            year()
        }
    }
}

@OptIn(ExperimentalCoroutinesApi::class)
inline fun <reified T> Flow<Flow<T>>.flattenLatest(): Flow<T> = flatMapLatest { flow -> flow }


@OptIn(ExperimentalCoroutinesApi::class)
class EventDetailViewModel(
    private val eventDetailsUseCase: EventDetailsUseCase,
    private val eventRegistrationUseCase: EventRegistrationUseCase,
) : AbstractCoroutineViewModel() {
    private val currId = MutableStateFlow<EventId?>(null)

    private val state: StateFlow<ScreenState<EventDetailsVo>> =
        currId.filterNotNull().flatMapLatest { id -> eventDetailsUseCase.execute(id) }.map { vo ->
            if (vo != null) {
                ScreenState.Success(vo)
            } else {
                ScreenState.Error("Something went wrong!")
            }
        }.asStateFlow(ScreenState.Loading())

    fun observeState(): StateFlow<ScreenState<EventDetailsVo>> = state

    fun setId(eventId: EventId) {
        currId.update { eventId }
    }

    fun registerOnEvent() {
        val id = currId.value ?: return
        launchScope {
            eventRegistrationUseCase.register(id)
        }
    }

    fun cancelRegistration() {
        val id = currId.value ?: return
        launchScope {
            eventRegistrationUseCase.cancel(id)
        }
    }

    override fun onCleared() {
        super.onCleared()
        logcat { "On cleared!" }
    }
}

@JvmInline
value class Avatar(val url: String?)

sealed class ScreenState<T> {
    class Loading<T> : ScreenState<T>()
    data class Error<T>(val message: String) : ScreenState<T>()
    data class Success<T>(val state: T) : ScreenState<T>()
}

data class EventDetailsVo(
    val name: String,
    val description: String,
    val dateLocation: String,
    val tags: List<String>,
    val mapUrl: String,
    val attendees: List<Avatar>,
    val isAttending: Boolean,
)

fun mockUsers(size: Int): List<UserModel> {

    val users = List(size) { index ->
        UserModel(
            id = UserId(index.toString()),
            name = "User #$index",
            avatarUrl = "https://avatars.githubusercontent.com/u/33986203?s=400&u=e890dc6a3d5835a8d26850faec9a0095809a3243&v=4".takeIf { index % 2 == 0 },
        )
    }

    return users
}

fun mockEventModels(size: Int): List<EventModel> {

    val places = listOf("Moscow", "SPb", "Kazan", "Tbilisi")

    val names = listOf(
        "Developer Meeting",
        "Code'n'code",
        "Mobile Submarine",
        "Mobius",
        "Very long name of the software event Conf."
    )

    val tags = listOf("Mobile", "Junior", "Middle", "Senior", "Kotlin", "Android", "Java", "LISP")

    val mocks = List(size) { index ->
        EventModel(
            name = names[index % names.size],
            id = EventId(index.toString()),
            description = loremIpsum((100 * index) % 600),
            avatarUrl = Avatar("https://avatars.githubusercontent.com/u/33986203?s=400&u=e890dc6a3d5835a8d26850faec9a0095809a3243&v=4".takeIf { index % 2 == 0 }),
            mapUrl = "https://i.ibb.co/Lphf2PK/map.jpg",
            tags = List(index % tags.size) { tagIndex -> tags[(index + tagIndex) % tags.size] },
            date = Clock.System.now().plus(24 * index, DateTimeUnit.HOUR)
                .toLocalDateTime(TimeZone.currentSystemDefault()),
            location = places[index % places.size],
            attendees = mockUsers(index % 15).map { user -> user.id },
        )
    }

    return mocks
}
