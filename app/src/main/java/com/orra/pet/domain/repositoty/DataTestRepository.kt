package com.orra.pet.domain.repositoty

import com.orra.pet.data.dto.AmericaHoliday

interface DataTestRepository {

    suspend fun getTestData(): List<AmericaHoliday>
}