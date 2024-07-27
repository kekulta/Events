package com.kekulta.events.data.stubs.functions

import com.kekulta.events.common.utils.loremIpsum
import com.kekulta.events.domain.models.base.CommunityModel
import com.kekulta.events.domain.models.base.EventModel
import com.kekulta.events.domain.models.base.ProfileModel
import com.kekulta.events.domain.models.base.UserModel
import com.kekulta.events.domain.models.id.CommunityId
import com.kekulta.events.domain.models.id.EventId
import com.kekulta.events.domain.models.id.UserId
import com.kekulta.events.domain.models.values.Avatar
import com.kekulta.events.domain.models.values.EmailAddress
import com.kekulta.events.domain.models.values.Identifier
import com.kekulta.events.domain.models.values.Location
import com.kekulta.events.domain.models.values.PhoneNumber
import kotlinx.datetime.Clock
import kotlinx.datetime.DateTimeUnit
import kotlinx.datetime.TimeZone
import kotlinx.datetime.plus
import kotlinx.datetime.toLocalDateTime

internal fun generateStubUsers(size: Int): List<UserModel> {
    val users = generateStubProfiles(size).map { profile ->
        UserModel(
            id = profile.id,
            name = profile.name,
            surname = profile.surname,
            avatar = profile.avatar,
            email = profile.identifier.address,
            number = profile.identifier.number,
        )
    }

    return users
}

internal fun generateStubEvents(size: Int): List<EventModel> {

    val places = listOf("Moscow", "SPb", "Kazan", "Tbilisi")

    val names = listOf(
        "Developer Meeting",
        "Code'n'code",
        "Mobile Submarine",
        "Mobius",
        "Very long name of the software event Conf."
    )

    val tags = listOf("Mobile", "Junior", "Middle", "Senior", "Kotlin", "Android", "Java", "LISP")

    val stubs = List(size) { index ->
        EventModel(
            id = EventId(index.toString()),
            community = generateStubCommunities(10)[index % 10].id,
            name = "${names[index % names.size]} #$index",
            description = loremIpsum((500 * index) % 2000),
            avatar = Avatar("https://avatars.githubusercontent.com/u/33986203?s=400&u=e890dc6a3d5835a8d26850faec9a0095809a3243&v=4".takeIf { index % 2 == 0 }),
            tags = List(index % tags.size) { tagIndex -> tags[(index + tagIndex) % tags.size] },
            date = Clock.System.now().plus((24 * (index - 4)), DateTimeUnit.HOUR)
                .toLocalDateTime(TimeZone.currentSystemDefault()),
            location = Location(places[index % places.size]),
        )
    }

    return stubs
}

internal fun generateStubProfiles(size: Int): List<ProfileModel> {
    return List(size) { index ->
        ProfileModel(
            id = UserId(index.toString()),
            name = "User #$index",
            surname = null,
            avatar = Avatar("https://avatars.githubusercontent.com/u/33986203?s=400&u=e890dc6a3d5835a8d26850faec9a0095809a3243&v=4".takeIf { index % 2 == 0 }),
            identifier = Identifier.Both(
                number = PhoneNumber(
                    "RU", "+7", (9170000000 + index).toString()
                ), address = EmailAddress("user#$index@events.app")
            ),
        )
    }
}

internal fun generateStubCommunities(size: Int): List<CommunityModel> {
    val names = listOf("Developer Meeting", "Code'n'code", "Mobile Submarine", "Mobius")

    return List(size) { index ->
        CommunityModel(
            id = CommunityId(index.toString()),
            name = "${names[index % names.size]} #$index",
            description = loremIpsum((500 * index) % 2000),
            avatar = Avatar("https://avatars.githubusercontent.com/u/33986203?s=400&u=e890dc6a3d5835a8d26850faec9a0095809a3243&v=4".takeIf { index % 2 == 0 }),
        )
    }
}

internal fun generateStubMembers(communitiesCount: Int, membersMax: Int): List<Pair<UserId, CommunityId>> {
    val communities = generateStubCommunities(communitiesCount)
    return communities.mapIndexed { index, community ->
        generateStubProfiles(index % membersMax).map { profile -> profile.id to community.id }
    }.flatten()
}

internal fun generateStubVisitors(
    eventsCount: Int,
    visitorsMax: Int
): List<Pair<UserId, EventId>> {
    val events = generateStubEvents(eventsCount)
    return events.mapIndexed { index, event ->
        generateStubProfiles(index % visitorsMax).map { profile -> profile.id to event.id }
    }.flatten()
}
