package com.example.homepage.features.plazaDashboard.Model

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.homepage.utils.models.Announcements
import com.example.homepage.features.plazaDashboard.Repo.PlazaDashBoardRepository


class PlazaDashBoardViewModel : ViewModel() {

    private val repository: PlazaDashBoardRepository = PlazaDashBoardRepository().getInstance()
    private val _allUserAnnouncements = MutableLiveData<List<Announcements>>()
    val allUserAnnouncements: LiveData<List<Announcements>> = _allUserAnnouncements

    init {
        repository.loadPlazaDashBoard(_allUserAnnouncements)
    }
}