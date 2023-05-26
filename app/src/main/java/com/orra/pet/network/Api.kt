package com.orra.pet.network

import com.orra.pet.data.dto.AmericaHolidayDto
import retrofit2.http.GET


interface Api {

    @GET("v2/publicholidays/2023/US")
    suspend fun getAmericaHolidays(): retrofit2.Response<List<AmericaHolidayDto>>

}