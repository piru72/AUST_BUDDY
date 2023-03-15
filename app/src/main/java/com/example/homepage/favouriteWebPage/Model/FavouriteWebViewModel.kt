package com.example.homepage.favouriteWebPage.Model

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.homepage.Model.FavouriteWebpageData
import com.example.homepage.favouriteWebPage.Repo.FavouriteWebRepo

class FavouriteWebViewModel : ViewModel() {

    private val repository: FavouriteWebRepo = FavouriteWebRepo().getInstance()
    private val _allSchedules = MutableLiveData<List<FavouriteWebpageData>>()
    val allSchedules: LiveData<List<FavouriteWebpageData>> = _allSchedules

    init {
        repository.loadWebsites(_allSchedules)
    }
}

