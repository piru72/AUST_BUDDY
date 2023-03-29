package com.example.homepage.features.homeTab.courseTab.YouTubeVideo

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.homepage.utils.models.YouTubeVideoData
import com.google.firebase.database.FirebaseDatabase

class YouTubeVideoViewModel : ViewModel() {
    private var repository: YouTubeVideoRepository =
        YouTubeVideoRepository(FirebaseDatabase.getInstance())

    private val mutableLiveData = MutableLiveData<List<YouTubeVideoData>>()
    val liveData: LiveData<List<YouTubeVideoData>> = mutableLiveData

    init {
        repository.loadData(mutableLiveData)
    }

}
