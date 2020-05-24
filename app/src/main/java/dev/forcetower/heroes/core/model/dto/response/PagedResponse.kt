package dev.forcetower.heroes.core.model.dto.response

data class PagedResponse<T>(
    val offset: Int,
    val limit: Int,
    val total: Int,
    val count: Int,
    val results: List<T>
)