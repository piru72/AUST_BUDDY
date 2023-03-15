package com.example.homepage.groupNoticePage.groupNoticeModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.homepage.Model.GroupNoticeData
import com.example.homepage.groupNoticePage.groupNoticeRepo.GroupNoticeRepository

class GroupNoticeViewModel : ViewModel() {

    private lateinit var repository: GroupNoticeRepository
    private val _allSchedules = MutableLiveData<List<GroupNoticeData>>()
    val allSchedules: LiveData<List<GroupNoticeData>> = _allSchedules

    fun initialize(groupSelected: String) {
        repository = GroupNoticeRepository(groupSelected).getInstance()
        repository.loadSchedules(_allSchedules)
    }
}