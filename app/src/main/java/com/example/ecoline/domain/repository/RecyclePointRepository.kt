package com.example.ecoline.domain.repository

import com.example.ecoline.data.remote.dto.CalendarEventDTO
import com.example.ecoline.data.remote.dto.RecyclePointDTO
import retrofit2.Response

interface RecyclePointRepository {

    suspend fun getRecyclePoints(categoryType: List<String>? = null): Response<List<RecyclePointDTO>>

    suspend fun getEvents(): Response<List<CalendarEventDTO>>

    suspend fun addRecyclePoint(categoryType: List<String>, recyclePointDTO: RecyclePointDTO): Response<RecyclePointDTO>
}