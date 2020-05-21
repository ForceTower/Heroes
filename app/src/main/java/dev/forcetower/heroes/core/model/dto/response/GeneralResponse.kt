package dev.forcetower.heroes.core.model.dto.response

data class GeneralResponse<T>(
    val code: Int,
    val data: PagedResponse<T>
)