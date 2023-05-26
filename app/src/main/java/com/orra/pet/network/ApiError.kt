package com.orra.pet.network


sealed class ApiError(override val message: String = "") : RuntimeException(message) {

    interface HumanMessageError {
        val message: String
    }

    class CommonError(message: String, val code: String) : ApiError(message), HumanMessageError
    class JsonSyntaxError(message: String) : ApiError(message)
    class UnknownError(val e: Exception) : ApiError()

}


val Throwable.humanErrorMessage get() = (this as? ApiError.HumanMessageError)?.message?.takeIf { it.isNotBlank() }