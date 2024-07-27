package com.kekulta.events.domain.models.pagination

import com.kekulta.events.domain.models.id.CommunityId
import com.kekulta.events.domain.models.id.UserId

sealed interface CommunitiesQuery {

    data class Community(
        val id: CommunityId,
    ) : CommunitiesQuery

    data class Communities(
        val ids: List<CommunityId>,
    ) : CommunitiesQuery

    data class Search(
        val query: String,
        val limit: Int,
        val offset: Int,
    ) : CommunitiesQuery

    data class Recommendation(
        val limit: Int,
        val offset: Int,
    ) : CommunitiesQuery

    data class User(
        val id: UserId,
        val limit: Int,
        val offset: Int,
    ) : CommunitiesQuery
}