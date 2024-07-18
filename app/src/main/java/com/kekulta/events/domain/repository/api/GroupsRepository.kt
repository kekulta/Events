package com.kekulta.events.domain.repository.api

import com.kekulta.events.domain.models.UserId
import com.kekulta.events.domain.models.GroupId
import com.kekulta.events.domain.models.GroupModel
import kotlinx.coroutines.flow.Flow

interface GroupsRepository {
    fun observeGroupsForQuery(query: GroupsQuery): Flow<List<GroupModel>>
    fun observeGroup(id: GroupId): Flow<GroupModel?>
    suspend fun joinGroup(id: GroupId, userId: UserId): Boolean
    suspend fun leaveGroup(id: GroupId, userId: UserId): Boolean
}

sealed interface GroupsQuery {
    val limit: Int

    data class Search(
        val query: String, override val limit: Int,
    ) : GroupsQuery

    data class Recommendation(
        override val limit: Int,
    ) : GroupsQuery


    data class User(
        val id: UserId, override val limit: Int,
    ) : GroupsQuery
}
