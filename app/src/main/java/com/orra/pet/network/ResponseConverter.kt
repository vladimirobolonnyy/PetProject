package com.orra.pet.network

import android.util.Log
import com.orra.pet.exceptions.ConvertDtoException
import com.orra.pet.exceptions.ServerError
import com.orra.pet.utils.makeList
import okhttp3.ResponseBody
import org.json.JSONObject
import retrofit2.Response

class ResponseConverter(
    private val errorConverter: ErrorConverter = ErrorConverter()
) {

    fun <T, R> customConvert(response: Response<T>, converter: (T) -> R): R? {
        return try {
            converter.invoke(response.body()!!)
        } catch (e: Exception) {
            Log.w("ResponseConverter", "", e)
            null
        }
    }

    fun <DtoT, T> convert(response: Response<DtoT>): T where DtoT : Dto<T> {
        return if (response.isSuccessful) {
            response.body()!!.convert()
        } else {
            throw errorConverter.convert(response)
        }
    }

    fun convertResult(response: Response<*>) {
        if (!response.isSuccessful) {
            throw errorConverter.convert(response)
        }
    }

    fun <T> convertScalar(response: Response<T>): T {
        return if (response.isSuccessful) {
            response.body()!!
        } else {
            throw errorConverter.convert(response)
        }
    }

    fun <DtoT, T> convertList(result: Response<DtoT>): List<T> where DtoT : List<Dto<T>> {
        return convertListResult(result, false)
    }

    fun <DtoT, T> safeConvertList(result: Response<DtoT>): List<T> where DtoT : List<Dto<T>> {
        return convertListResult(result, true)
    }

    private fun <DtoT, T> convertListResult(
        response: Response<DtoT>,
        skipErrorItems: Boolean
    ): List<T> where DtoT : List<Dto<T>> {
        return if (!response.isSuccessful) {
            throw convertErrorResponse(response)
        } else {
            makeList {
                response.body()?.forEach {
                    try {
                        add(it.convert())
                    } catch (e: Exception) {
                        if (e is ConvertDtoException) {
                            Log.w("ResponseConverter", "", e)
                        }
                        if (!skipErrorItems) {
                            throw e
                        }
                    }
                }
            }
        }
    }

    private fun convertErrorResponse(response: Response<*>): Throwable {
        return if (response.code() >= 500) {
            val message = response.message().takeIf { !it.isNullOrEmpty() }
            ServerError(
                message = message ?: response.errorBody().getStackTrace(),
                code = response.code()
            )
        } else {
            errorConverter.convert(response)
        }
    }

    private fun ResponseBody?.getStackTrace(): String {
        this ?: return ""
        return runCatching {
            val trace = JSONObject(string())
                .getString("traceId")
                .takeIf { !it.isNullOrBlank() }
            trace?.let { "traceId: $trace" }
        }.getOrNull().orEmpty()
    }
}