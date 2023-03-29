package com.example.homepage.features.plazaTab.plazaDashboard.Model

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.homepage.utils.models.Announcements
import com.example.homepage.features.plazaTab.plazaDashboard.Repo.PlazaDashBoardRepository


class PlazaDashBoardViewModel : ViewModel() {

    private val repository: PlazaDashBoardRepository = PlazaDashBoardRepository().getInstance()
    private val _allUserAnnouncements = MutableLiveData<List<Announcements>>()
    val allUserAnnouncements: LiveData<List<Announcements>> = _allUserAnnouncements

    init {
        repository.loadPlazaDashBoard(_allUserAnnouncements)
    }
}