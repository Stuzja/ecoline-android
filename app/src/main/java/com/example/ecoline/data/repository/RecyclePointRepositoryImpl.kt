package com.example.ecoline.data.repository

import com.example.ecoline.data.remote.api.EcolineRecycleAPI
import com.example.ecoline.data.remote.dto.CalendarEventDTO
import com.example.ecoline.data.remote.dto.RecyclePointDTO
import com.example.ecoline.domain.repository.RecyclePointRepository
import retrofit2.Response
import javax.inject.Inject

class RecyclePointRepositoryImpl @Inject constructor(private val ecolineRecycleAPI: EcolineRecycleAPI): RecyclePointRepository {

    override suspend fun getRecyclePoints(categoryType: List<String>?): Response<List<RecyclePointDTO>> {
        return ecolineRecycleAPI.getRecyclePoints(categoryType = categoryType)
    }

    override suspend fun getEvents(): Response<List<CalendarEventDTO>> {
        return ecolineRecycleAPI.getEvents()
    }

    override suspend fun addRecyclePoint(categoryType: List<String>, recyclePointDTO: RecyclePointDTO): Response<RecyclePointDTO> {
        return ecolineRecycleAPI.addRecyclePoint(categoryType = categoryType, recyclePointDTO = recyclePointDTO)
    }
}