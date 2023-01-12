package com.example.homepage.plaza.Model

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.homepage.plaza.Repo.PlazaRepository


class PlazaViewModel : ViewModel() {

    private val repository: PlazaRepository = PlazaRepository().getInstance()
    private val _allStores = MutableLiveData<List<Announcements>>()
    val allStore: LiveData<List<Announcements>> = _allStores

    init {
        repository.loadStore(_allStores)
    }
}