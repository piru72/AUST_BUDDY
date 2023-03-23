package com.example.homepage.features.adminPanel.adminRequest.Model

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.homepage.utils.models.Admin
import com.example.homepage.features.adminPanel.adminRequest.Repo.AdminRequestRepository
import com.google.firebase.database.FirebaseDatabase

class AdminRequestViewModel : ViewModel() {

    private var repository: AdminRequestRepository = AdminRequestRepository(FirebaseDatabase.getInstance())
    private val _allAdminRequest = MutableLiveData<List<Admin>>()
    val allAdminRequest: LiveData<List<Admin>> = _allAdminRequest

    init{
        repository.loadAdminRequest(_allAdminRequest)
    }

}