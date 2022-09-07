package com.example.homepage.teachersPage.TeacherModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.homepage.teachersPage.TeacherRepo.teacherRepository


class teacherViewModel : ViewModel() {

    private val repository : teacherRepository
    private val _allUsers = MutableLiveData<List<teacherData>>()
    val allUsers : LiveData<List<teacherData>> = _allUsers


    init {

        repository = teacherRepository().getInstance()
        repository.loadUsers(_allUsers)

    }

}