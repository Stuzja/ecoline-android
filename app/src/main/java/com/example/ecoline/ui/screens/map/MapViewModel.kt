package com.example.ecoline.ui.screens.calendar

import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.ecoline.data.remote.dto.RecyclePointDTO
import com.example.ecoline.data.remote.dto.toRecyclePoint
import com.example.ecoline.domain.models.RecyclePoint
import com.example.ecoline.domain.repository.RecyclePointRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MapViewModel @Inject constructor(
    private val repository: RecyclePointRepository,
) : ViewModel() {

    val categoryType = MutableStateFlow(mutableListOf<String>())

    val filterCategories = mutableListOf<String>()

    private val _recyclePoints = mutableStateListOf<RecyclePoint>()
    var recyclePoints: List<RecyclePoint> = _recyclePoints

    init {
        initRecyclePoint()
    }

    fun updateRecyclePoints() {
        val categoryType = if(filterCategories.isEmpty()) null else filterCategories
        viewModelScope.launch {
            val response = repository.getRecyclePoints(categoryType = categoryType)
            val handleResponse = response.body()?.map { it.toRecyclePoint() } ?: emptyList()
            _recyclePoints.clear()
            _recyclePoints.addAll(handleResponse)
        }
    }

    fun addRecyclePoint(
        recyclePointDTO: RecyclePointDTO
    ) {
        viewModelScope.launch(Dispatchers.IO) {
            val response = repository.addRecyclePoint(categoryType = categoryType.value.toList(), recyclePointDTO = recyclePointDTO)
            val responseBody = response.body()!!.toRecyclePoint()
            _recyclePoints.add(responseBody)
        }
    }

    private fun initRecyclePoint() {
        viewModelScope.launch(Dispatchers.IO) {
            runCatching {
                repository.getRecyclePoints()
            }.onSuccess {
                val handleResponse = it.body()?.map { it.toRecyclePoint() } ?: emptyList()
                _recyclePoints.addAll(handleResponse)
            }.onFailure {
                _recyclePoints.addAll(emptyList())
            }
        }
    }
}