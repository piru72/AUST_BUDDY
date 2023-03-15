package com.example.homepage.groupTab.Group.Model

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.homepage.Model.GroupData
import com.example.homepage.groupTab.Group.Repository.UserGroupRepo

class UserGroupsViewModel : ViewModel() {
    private val repository: UserGroupRepo = UserGroupRepo().getInstance()
    private val _allUserGroups = MutableLiveData<List<GroupData>>()
    val allUserGroups: LiveData<List<GroupData>> = _allUserGroups

    init {
        repository.loadUserGroups(_allUserGroups)
    }
}