package com.example.homepage.scheduleTab.scheduleModel

import androidx.lifecycle.ViewModel
import com.example.homepage.scheduleTab.scheduleRepo.ScheduleRepository

class ScheduleViewModel : ViewModel(){

    private val repository : ScheduleRepository = ScheduleRepository().getInstance()
//    private val _allSchedules : MutableLiveData<List<ScheduleData>>()
//            val allSchedules : LiveData<List<ScheduleData>> = _allSchedules
//
//    init {
//        repository.loadSchedules(_allSchedules)
//    }
}