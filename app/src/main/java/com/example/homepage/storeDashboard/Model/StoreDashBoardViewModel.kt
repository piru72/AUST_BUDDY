package com.example.homepage.storeDashboard.Model

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.homepage.storeDashboard.Repo.StoreDashBoardRepository
import com.example.homepage.storeTab.Model.Materials

class StoreDashBoardViewModel : ViewModel() {

    private val repository: StoreDashBoardRepository = StoreDashBoardRepository().getInstance()
    private val _allStores = MutableLiveData<List<Materials>>()
    val allStore: LiveData<List<Materials>> = _allStores

    init {
        repository.loadStoreDashBoard(_allStores)
    }
}