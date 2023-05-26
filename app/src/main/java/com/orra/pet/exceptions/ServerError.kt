package com.orra.pet.exceptions

class ServerError(
    override val message: String,
    val code: Int
) : RuntimeException(message)