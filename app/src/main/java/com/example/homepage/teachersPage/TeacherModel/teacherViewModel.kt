package com.example.homepage.teachersPage.TeacherModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.homepage.teachersPage.TeacherRepo.TeacherRepository


class teacherViewModel : ViewModel() {

    private val repository : TeacherRepository
    private val _allUsers = MutableLiveData<List<teacherData>>()
    val allUsers : LiveData<List<teacherData>> = _allUsers


    init {

        repository = TeacherRepository().getInstance()
        repository.loadUsers(_allUsers)

    }

}