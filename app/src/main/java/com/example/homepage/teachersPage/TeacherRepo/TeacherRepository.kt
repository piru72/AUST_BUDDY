package com.example.homepage.teachersPage.TeacherRepo

import androidx.lifecycle.MutableLiveData
import com.example.homepage.teachersPage.TeacherModel.teacherData
import com.google.firebase.database.*

class TeacherRepository {
    //TODO here the name is changed of the parent repo
    private val databaseReference : DatabaseReference = FirebaseDatabase.getInstance().getReference("teachers")
    @Volatile private var INSTANCE : TeacherRepository ?= null
    fun getInstance() : TeacherRepository {
        return INSTANCE ?: synchronized(this) {

            val instance = TeacherRepository()
            INSTANCE = instance
            instance
        }
    }

    fun loadUsers(userList : MutableLiveData<List<teacherData>>){

        databaseReference.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {

                try {

                    val _userList : List<teacherData> = snapshot.children.map { dataSnapshot ->

                        dataSnapshot.getValue(teacherData::class.java)!!

                    }

                    userList.postValue(_userList)

                }catch (_: Exception){


                }


            }

            override fun onCancelled(error: DatabaseError) {
                TODO("Not yet implemented")
            }


        })


    }
}