package com.example.homepage.adminPanel.adminRequest.Model

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.homepage.adminPanel.adminRequest.Repo.AdminRequestRepository

class AdminRequestViewModel : ViewModel() {

    private var repository: AdminRequestRepository = AdminRequestRepository().getInstance()
    private val _allAdminRequest = MutableLiveData<List<Admin>>()
    val allAdminRequest: LiveData<List<Admin>> = _allAdminRequest

    init{
        repository.loadAdminRequest(_allAdminRequest)
    }

}