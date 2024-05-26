package com.example.ecoline.ui.screens.calendar

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.ecoline.data.remote.dto.toCalendarEvent
import com.example.ecoline.domain.models.CalendarEvent
import com.example.ecoline.domain.repository.RecyclePointRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CalendarViewModel @Inject constructor(
    private val repository: RecyclePointRepository,
) : ViewModel() {

    private val _events = MutableStateFlow(listOf<CalendarEvent>())
    val events: StateFlow<List<CalendarEvent>> = _events

    init{
        initRecyclePoint()
    }

    private fun initRecyclePoint() {
        viewModelScope.launch(Dispatchers.IO) {
            runCatching {
                repository.getEvents()
            }.onSuccess {
                val handleResponse = it.body()?.map { it.toCalendarEvent() } ?: emptyList()
                _events.emit(value = handleResponse)
            }.onFailure {
                Log.d("123123", it.toString())
                _events.emit(value = emptyList())
            }
        }
    }
}