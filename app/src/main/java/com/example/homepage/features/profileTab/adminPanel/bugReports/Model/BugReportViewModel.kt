package com.example.homepage.features.profileTab.adminPanel.bugReports.Model

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.homepage.utils.models.BugReportsData
import com.example.homepage.features.profileTab.adminPanel.bugReports.Repository.BugReportRepository

class BugReportViewModel : ViewModel() {

    private val repository: BugReportRepository = BugReportRepository().getInstance()
    private val _allBugReports = MutableLiveData<List<BugReportsData>>()
    val allBugReport: LiveData<List<BugReportsData>> = _allBugReports

    init {
        repository.loadBugReports(_allBugReports)
    }


}