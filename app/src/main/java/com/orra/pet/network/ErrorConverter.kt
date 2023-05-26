package com.orra.pet.network

import kotlinx.serialization.SerializationException
import kotlinx.serialization.json.Json
import retrofit2.Response

class ErrorConverter() {

    fun convert(response: Response<*>): ApiError {
        return try {
            val body = response.errorBody()
            val errorDto = Json.decodeFromString<ErrorDto>(body?.string().orEmpty())
            errorDto.convert()
        } catch (e: SerializationException) {
            ApiError.JsonSyntaxError(response.getBeautifulText())
        } catch (e: Exception) {
            ApiError.UnknownError(e)
        }
    }

    private fun Response<*>.getBeautifulText(): String {
        val url = raw().request.url
        return "${message().orEmpty()}, code:=${code()}, url:= $url"
    }

}