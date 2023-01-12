package com.example.homepage.plazaDashboard.Model

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.homepage.plaza.Model.Announcements
import com.example.homepage.plazaDashboard.Repo.PlazaDashBoardRepository


class PlazaDashBoardViewModel : ViewModel() {

    private val repository: PlazaDashBoardRepository = PlazaDashBoardRepository().getInstance()
    private val _allStores = MutableLiveData<List<Announcements>>()
    val allStore: LiveData<List<Announcements>> = _allStores

    init {
        repository.loadStoreDashBoard(_allStores)
    }
}