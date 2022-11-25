package com.example.homepage.scheduleTab.scheduleModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.homepage.scheduleTab.scheduleRepo.ScheduleRepository

class ScheduleViewModel : ViewModel(){

    private lateinit var repository : ScheduleRepository
    private val _allSchedules = MutableLiveData<List<ScheduleData>>()
    val allSchedules : LiveData<List<ScheduleData>> = _allSchedules

    fun initialize(groupSelected: String) {
        repository = ScheduleRepository(groupSelected).getInstance()
        repository.loadSchedules(_allSchedules )
    }
}