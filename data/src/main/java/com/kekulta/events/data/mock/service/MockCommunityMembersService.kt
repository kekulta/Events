package com.kekulta.events.data.mock.service

import com.kekulta.events.data.mock.functions.mockMembers
import com.kekulta.events.domain.models.id.CommunityId
import com.kekulta.events.domain.models.id.UserId
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.update

internal class MockCommunityMembersService {

    private val members = MutableStateFlow(mockMembers(50, 50))

    fun fetchMembers(): Flow<List<Pair<UserId, CommunityId>>> = members

    fun add(userId: UserId, communityId: CommunityId) {
        members.update { registrations ->
            registrations.filterNot { it == userId to communityId } + (userId to communityId)
        }
    }

    fun remove(userId: UserId, communityId: CommunityId) {
        members.update { registrations ->
            registrations.filterNot { it == userId to communityId }
        }
    }


    fun remove(userId: UserId) {
        members.update { registrations ->
            registrations.filterNot { it.first == userId }
        }
    }

    fun remove(communityId: CommunityId) {
        members.update { registrations ->
            registrations.filterNot { it.second == communityId }
        }
    }
}