package com.kekulta.events.data.mock.repository

import com.kekulta.events.data.mock.service.MockAuthService
import com.kekulta.events.data.mock.service.MockCommunityMembersService
import com.kekulta.events.data.mock.service.MockCommunityService
import com.kekulta.events.data.mock.service.MockEventsService
import com.kekulta.events.domain.models.base.CommunityModel
import com.kekulta.events.domain.models.id.CommunityId
import com.kekulta.events.domain.models.id.UserId
import com.kekulta.events.domain.models.info.CommunityInfo
import com.kekulta.events.domain.models.pagination.CommunitiesQuery
import com.kekulta.events.domain.models.pagination.Page
import com.kekulta.events.domain.models.status.AuthStatus
import com.kekulta.events.domain.repository.api.CommunitiesRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.map

internal class CommunitiesRepositoryMock(
    private val authService: MockAuthService,
    private val communityMembersService: MockCommunityMembersService,
    private val communityService: MockCommunityService,
    private val eventsService: MockEventsService,
) : CommunitiesRepository {
    override fun observeCommunitiesForQuery(query: CommunitiesQuery): Flow<Page<CommunityModel>> {
        return when (query) {
            is CommunitiesQuery.Communities -> {
                val ids = query.ids.toSet()

                communityService.fetchCommunities().map { communities ->
                    Page(
                        communities.filter { community -> community.id in ids },
                        0,
                        total = ids.size,
                    )
                }
            }

            is CommunitiesQuery.Community -> {
                communityService.fetchCommunities().map { communities ->
                    Page(communities.filter { community -> community.id == query.id }, 0, total = 1)
                }
            }

            is CommunitiesQuery.Recommendation -> {
                communityService.fetchCommunities().map { communities ->
                    Page(
                        communities.drop(query.offset).take(query.limit),
                        query.offset,
                        total = communities.size,
                    )
                }
            }

            is CommunitiesQuery.Search -> throw NotImplementedError("Search is not implemented!")

            is CommunitiesQuery.User -> {
                combine(
                    communityMembersService.fetchMembers().map { communities ->
                        communities.mapNotNull { (userId, communityId) -> communityId.takeIf { userId == query.id } }
                    }, communityService.fetchCommunities()
                ) { ids, communities ->
                    val idsSet = ids.toSet()
                    val filteredCommunities =
                        communities.filter { community -> community.id in idsSet }

                    Page(
                        filteredCommunities
                            .drop(query.offset).take(query.limit),
                        query.offset,
                        filteredCommunities.size,
                    )
                }
            }
        }
    }

    override suspend fun joinCommunity(id: CommunityId) {
        (authService.observeAuthStatus().value as? AuthStatus.Authorized)?.id?.let { userId ->
            communityMembersService.add(userId, id)
        }
    }

    override suspend fun leaveCommunity(id: CommunityId) {
        (authService.observeAuthStatus().value as? AuthStatus.Authorized)?.id?.let { userId ->
            communityMembersService.remove(userId, id)
        }
    }

    override suspend fun createCommunity(info: CommunityInfo) {
        communityService.createCommunity(info)
    }

    override suspend fun changeCommunity(id: CommunityId, info: CommunityInfo) {
        communityService.changeCommunity(id, info)
    }

    override suspend fun deleteCommunity(id: CommunityId) {
        communityMembersService.remove(id)
        communityService.deleteCommunity(id)
        eventsService.deleteEvents(id)
    }

    override suspend fun kickFromCommunity(id: CommunityId, userId: UserId) {
        communityMembersService.remove(userId, id)
    }
}
