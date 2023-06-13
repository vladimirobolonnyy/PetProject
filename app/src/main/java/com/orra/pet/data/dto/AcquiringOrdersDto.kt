package com.orra.pet.data.dto

import com.orra.pet.network.Dto
import com.orra.pet.network.convertError
import com.orra.pet.network.convertLocalDate
import kotlinx.serialization.Serializable
import org.joda.time.LocalDate

@Serializable
class AmericaHolidayDto : Dto<AmericaHoliday> {

    private val date: String? = null
    private val localName: String? = null
    private val name: String? = null

    override fun convert(): AmericaHoliday {
        return AmericaHoliday(
            date = date?.convertLocalDate() ?: convertError(""),
            localName = localName.orEmpty(),
            name = name.orEmpty()
        )
    }
}

data class AmericaHoliday(
    val date: LocalDate,
    val localName: String,
    val name: String
)