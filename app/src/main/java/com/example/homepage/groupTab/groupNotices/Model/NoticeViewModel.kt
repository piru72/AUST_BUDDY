package com.example.homepage.groupTab.groupNotices.Model

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.homepage.groupTab.groupNotices.Repo.NoticeRepo

class NoticeViewModel : ViewModel() {

    private val repository : NoticeRepo = NoticeRepo().getInstance()
    private val _allNotices = MutableLiveData<List<GroupNoticeData>>()
    val allNotices : LiveData<List<GroupNoticeData>> = _allNotices

    init {
        repository.loadNotices(_allNotices)
    }
}
