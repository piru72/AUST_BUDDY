package com.example.homepage.teachersPage.TeacherModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.homepage.Model.TeacherData
import com.example.homepage.teachersPage.TeacherRepo.TeacherRepository


class teacherViewModel : ViewModel() {

    private lateinit var repository: TeacherRepository
    private val _allUsers = MutableLiveData<List<TeacherData>>()
    val allUsers: LiveData<List<TeacherData>> = _allUsers


    fun initialize(viewPath: String) {
        repository = TeacherRepository(viewPath).getInstance()
        repository.loadUsers(_allUsers)

    }

}