package com.kekulta.events.data.mock.repository

import android.util.Log
import com.kekulta.events.data.mock.service.MockCommunityMembersService
import com.kekulta.events.data.mock.service.MockEventsRegistrationService
import com.kekulta.events.data.mock.service.MockUsersService
import com.kekulta.events.domain.models.base.UserModel
import com.kekulta.events.domain.models.pagination.Page
import com.kekulta.events.domain.models.pagination.UsersQuery
import com.kekulta.events.domain.models.pagination.toPage
import com.kekulta.events.domain.repository.api.UsersRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.map

internal class UsersRepositoryMock(
    private val mockUsersService: MockUsersService,
    private val eventsRegistrationService: MockEventsRegistrationService,
    private val communityMembersService: MockCommunityMembersService,
) : UsersRepository {
    private val users = mockUsersService.fetchUsers()

    override fun observeUsersForQuery(query: UsersQuery): Flow<Page<UserModel>> {
        return when (query) {
            is UsersQuery.User -> users.map { users ->
                users.filter { user -> user.id == query.id }.toPage(0, 1)
            }

            is UsersQuery.Users -> {
                val ids = query.ids.toSet()

                users.map { users -> users.filter { user -> user.id in ids }.toPage(0, ids.size) }
            }

            is UsersQuery.Recommendation -> {
                users.map { users ->
                    users.drop(query.offset).take(query.limit).toPage(query.offset, users.size)
                }
            }

            is UsersQuery.Search -> throw NotImplementedError("Search is not implemented!")
            is UsersQuery.Event -> {
                combine(
                    eventsRegistrationService.fetchRegistrations().map { registrations ->
                        registrations.mapNotNull { (userId, eventId) ->
                            userId.takeIf { eventId == query.id }
                        }
                    }, users
                ) { ids, users ->
                    val idsSet = ids.toSet()

                    val filteredUsers = users.filter { user -> user.id in idsSet }

                    filteredUsers.drop(query.offset).take(query.limit)
                        .toPage(query.offset, filteredUsers.size)
                }
            }

            is UsersQuery.Community -> {
                combine(
                    communityMembersService.fetchMembers().map { communities ->
                        communities.mapNotNull { (userId, communityId) -> userId.takeIf { communityId == query.id } }
                    }, users
                ) { ids, users ->
                    val idsSet = ids.toSet()
                    val filteredUsers = users.filter { user -> user.id in idsSet }

                    Page(
                        filteredUsers.drop(query.offset).take(query.limit),
                        query.offset,
                        filteredUsers.size,
                    )
                }
            }
        }
    }
}