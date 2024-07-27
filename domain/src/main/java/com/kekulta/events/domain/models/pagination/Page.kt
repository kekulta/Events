package com.kekulta.events.domain.models.pagination

const val BASE_PAGE_SIZE = 25

data class Page<T>(val values: List<T>, val offset: Int, val total: Int) : List<T> by values

data class PageConfig(val offset: Int, val limit: Int)

fun firstPage() = PageConfig(0, BASE_PAGE_SIZE)

fun <T> emptyPage() = Page<T>(emptyList(), offset = 0, total = 0)

fun <T> List<T>.toPage(offset: Int , total: Int) = Page<T>(this, offset = offset, total = total)
