package com.example.homepage.teachersPage.TeacherModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.homepage.teachersPage.TeacherRepo.TeacherRepository


class teacherViewModel : ViewModel() {

    private val repository : TeacherRepository = TeacherRepository().getInstance()
    private val _allUsers = MutableLiveData<List<TeacherData>>()
    val allUsers : LiveData<List<TeacherData>> = _allUsers


    init {

        repository.loadUsers(_allUsers)

    }

}