package com.kekulta.events.data.mock.functions

import com.kekulta.events.common.utils.loremIpsum
import com.kekulta.events.domain.models.Avatar
import com.kekulta.events.domain.models.EventId
import com.kekulta.events.domain.models.EventModel
import com.kekulta.events.domain.models.GroupId
import com.kekulta.events.domain.models.GroupModel
import com.kekulta.events.domain.models.PersonalInfo
import com.kekulta.events.domain.models.PhoneNumber
import com.kekulta.events.domain.models.ProfileModel
import com.kekulta.events.domain.models.UserId
import com.kekulta.events.domain.models.UserModel
import kotlinx.datetime.Clock
import kotlinx.datetime.DateTimeUnit
import kotlinx.datetime.TimeZone
import kotlinx.datetime.plus
import kotlinx.datetime.toLocalDateTime

internal fun mockUsers(size: Int): List<UserModel> {

    val users = List(size) { index ->
        UserModel(
            id = UserId(index.toString()),
            name = "User #$index",
            surname = null,
            avatar = Avatar("https://avatars.githubusercontent.com/u/33986203?s=400&u=e890dc6a3d5835a8d26850faec9a0095809a3243&v=4".takeIf { index % 2 == 0 }),
        )
    }

    return users
}

internal fun mockEventModels(size: Int): List<EventModel> {

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
            id = EventId(index.toString()),
            name = "${names[index % names.size]} #$index",
            description = loremIpsum((500 * index) % 2000),
            avatar = Avatar("https://avatars.githubusercontent.com/u/33986203?s=400&u=e890dc6a3d5835a8d26850faec9a0095809a3243&v=4".takeIf { index % 2 == 0 }),
            mapUrl = "https://i.ibb.co/Lphf2PK/map.jpg",
            tags = List(index % tags.size) { tagIndex -> tags[(index + tagIndex) % tags.size] },
            date = Clock.System.now().plus((24 * (index - 4)), DateTimeUnit.HOUR)
                .toLocalDateTime(TimeZone.currentSystemDefault()),
            location = places[index % places.size],
            attendees = mockUsers(index % 15).map { user -> user.id },
        )
    }

    return mocks
}

internal fun mockProfileModels(size: Int): List<ProfileModel> {
    return mockUsers(size).mapIndexed { index, user ->
        ProfileModel(
            id = user.id, number = PhoneNumber("+7", (9170000000 + index).toString()),
            info = PersonalInfo(
                avatar = user.avatar,
                name = user.name,
                surname = user.surname
            )
        )
    }
}

internal fun mockGroupModels(size: Int): List<GroupModel> {
    val names = listOf("Developer Meeting", "Code'n'code", "Mobile Submarine", "Mobius")

    return List(size) { index ->
        GroupModel(
            id = GroupId(index.toString()),
            name = "${names[index % names.size]} #$index",
            description = loremIpsum((500 * index) % 2000),
            avatar = Avatar("https://avatars.githubusercontent.com/u/33986203?s=400&u=e890dc6a3d5835a8d26850faec9a0095809a3243&v=4".takeIf { index % 2 == 0 }),
            members = mockUsers(index).map { user -> user.id },
            events = mockEventModels(index).map { event -> event.id },
        )
    }
}
