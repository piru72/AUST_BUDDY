package com.example.homepage.plaza.Model

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.homepage.Model.Announcements
import com.example.homepage.plaza.Repo.PlazaRepository


class PlazaViewModel : ViewModel() {


    private lateinit var  repository: PlazaRepository
    private val _allAnnouncements = MutableLiveData<List<Announcements>>()
    val allAnnouncement: LiveData<List<Announcements>> = _allAnnouncements

    // initializing the repository with the given view path
    fun initialize(viewPath: String) {
        repository = PlazaRepository(viewPath).getInstance()
        repository.loadStore(_allAnnouncements)
    }
}