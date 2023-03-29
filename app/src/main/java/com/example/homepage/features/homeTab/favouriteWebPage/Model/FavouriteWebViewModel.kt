package com.example.homepage.features.homeTab.favouriteWebPage.Model

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.homepage.utils.models.FavouriteWebpageData
import com.example.homepage.features.homeTab.favouriteWebPage.Repo.FavouriteWebRepo

class FavouriteWebViewModel : ViewModel() {

    private val repository: FavouriteWebRepo = FavouriteWebRepo().getInstance()
    private val _allSchedules = MutableLiveData<List<FavouriteWebpageData>>()
    val allSchedules: LiveData<List<FavouriteWebpageData>> = _allSchedules

    init {
        repository.loadWebsites(_allSchedules)
    }
}

