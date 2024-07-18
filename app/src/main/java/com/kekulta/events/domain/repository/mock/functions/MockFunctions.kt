package com.kekulta.events.domain.repository.mock.functions

import com.kekulta.events.domain.models.Avatar
import com.kekulta.events.domain.models.EventId
import com.kekulta.events.domain.models.EventModel
import com.kekulta.events.domain.models.UserId
import com.kekulta.events.domain.models.UserModel
import com.kekulta.events.presentation.ui.loremIpsum
import com.kekulta.events.domain.models.GroupId
import com.kekulta.events.domain.models.GroupModel
import kotlinx.datetime.Clock
import kotlinx.datetime.DateTimeUnit
import kotlinx.datetime.TimeZone
import kotlinx.datetime.plus
import kotlinx.datetime.toLocalDateTime

fun mockUsers(size: Int): List<UserModel> {

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


fun mockGroupModels(size: Int): List<GroupModel> {
    val names = listOf("Developer Meeting", "Code'n'code", "Mobile Submarine", "Mobius")

    return List(size) { index ->
        GroupModel(
            id = GroupId(index.toString()),
            name = names[index % names.size],
            avatar = Avatar("https://avatars.githubusercontent.com/u/33986203?s=400&u=e890dc6a3d5835a8d26850faec9a0095809a3243&v=4".takeIf { index % 2 == 0 }),
            members = mockUsers(index).map { user -> user.id },
        )
    }
}
