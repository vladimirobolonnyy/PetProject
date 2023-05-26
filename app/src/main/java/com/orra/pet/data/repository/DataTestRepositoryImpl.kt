package com.orra.pet.data.repository

import com.orra.pet.data.dto.AmericaHoliday
import com.orra.pet.data.dto.AmericaHolidayDto
import com.orra.pet.domain.repositoty.DataTestRepository
import com.orra.pet.network.Api
import com.orra.pet.network.NetworkModule
import com.orra.pet.network.ResponseConverter

class DataTestRepositoryImpl(
    private val converter: ResponseConverter = NetworkModule.converter,
    private val api: Api = NetworkModule.api,
) : DataTestRepository {

    override suspend fun getTestData(): List<AmericaHoliday> {
        return api.getAmericaHolidays().let {
            converter.convertList(it)
        }
    }
}