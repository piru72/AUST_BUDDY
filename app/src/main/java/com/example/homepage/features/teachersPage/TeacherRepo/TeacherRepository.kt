package com.example.homepage.features.teachersPage.TeacherRepo

import androidx.lifecycle.MutableLiveData
import com.example.homepage.utils.models.TeacherData
import com.google.firebase.database.*

class TeacherRepository(viewPath: String) {
    private var databaseParentNode = viewPath
    private val databaseReference: DatabaseReference =
        FirebaseDatabase.getInstance().getReference(databaseParentNode)

    @Volatile
    private var INSTANCE: TeacherRepository? = null
    fun getInstance(): TeacherRepository {
        return INSTANCE ?: synchronized(this) {

            val instance = TeacherRepository(databaseParentNode)
            INSTANCE = instance
            instance
        }
    }

    fun loadUsers(userList: MutableLiveData<List<TeacherData>>) {

        databaseReference.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {

                try {

                    val _userList: List<TeacherData> = snapshot.children.map { dataSnapshot ->

                        dataSnapshot.getValue(TeacherData::class.java)!!

                    }

                    userList.postValue(_userList)

                } catch (_: Exception) {


                }


            }

            override fun onCancelled(error: DatabaseError) {

            }


        })
        databaseReference.keepSynced(true)


    }
}