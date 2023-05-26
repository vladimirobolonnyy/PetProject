package com.orra.pet.network

import android.util.Log
import com.orra.pet.exceptions.ConvertDtoException
import kotlinx.datetime.LocalDate

interface Dto<out Model> {

    fun convert(): Model

}

fun <T> getNotNull(item: T?, dto: String, field: String): T = getNotNull(item, "$dto/$field")

fun <T> getNotNull(item: T?, field: String): T = item ?: convertError("'$field' must not be null")

fun convertError(error: String): Nothing = throw ConvertDtoException(error)

fun <T> Dto<T>.safeConvert(): T? = runCatching { convert() }.getOrNull()

fun <T> List<Dto<T>>?.convertList(): List<T> = this?.map { it.convert() }.orEmpty()

fun <T : Any> List<Dto<T>>?.safeConvertList(): List<T> {
    return this?.mapNotNull {
        try {
            it.convert()
        } catch (e: Exception) {
            Log.w("ResponseConverter", "", e)
            null
        }
    }.orEmpty()
}

fun String?.getValidColor(default: String = "ffffff"): String {
    val textColor = "#" + (this ?: default).trimStart('#')
    try {
        textColor.substring(1).toLong(16)
    } catch (e: Exception) {
        convertError("Invalid HEX color value: '$textColor'")
    }

    // для нас валидны только 2 формата: #AARRGGBB и #RRGGBB
    if (textColor.length != 7 && textColor.length != 9) {
        convertError("Invalid color value: '$textColor'")
    }
    return textColor
}

fun String.convertLocalDate(): LocalDate = LocalDate.parse(this)

