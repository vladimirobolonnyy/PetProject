package com.orra.pet.network

import kotlinx.serialization.Serializable

@Serializable
class ErrorDto : Dto<ApiError> {

    private val code: String? = null
    private val description: String? = null
    private val traceId: String? = null

    override fun convert(): ApiError {
        return when {
            description != null -> ApiError.CommonError(description, code.orEmpty())
            traceId != null -> ApiError.UnknownError(Exception("traceId: $traceId"))
            else -> ApiError.UnknownError(Exception("empty description"))
        }
    }
}