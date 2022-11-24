package com.example.homepage.groupTab.groupNotices.Model

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.homepage.groupTab.groupNotices.Repo.GroupNoticeRepo

class GroupNoticeViewModel : ViewModel() {


    private lateinit var repository : GroupNoticeRepo
    private val _allNotices = MutableLiveData<List<GroupNoticeData>>()
    val allNotices : LiveData<List<GroupNoticeData>> = _allNotices

    fun initialize(groupId: String) {
        repository =  GroupNoticeRepo(groupId).getInstance()
        repository.loadNotices(_allNotices)
    }
}

