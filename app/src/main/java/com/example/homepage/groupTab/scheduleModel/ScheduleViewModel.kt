package com.example.homepage.groupTab.scheduleModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.homepage.groupTab.scheduleRepo.ScheduleRepository

class ScheduleViewModel : ViewModel(){

    private val repository : ScheduleRepository = ScheduleRepository().getInstance()
    private val _allSchedules = MutableLiveData<List<ScheduleData>>()
            val allSchedules : LiveData<List<ScheduleData>> = _allSchedules

    init {
        repository.loadSchedules(_allSchedules)
    }
}