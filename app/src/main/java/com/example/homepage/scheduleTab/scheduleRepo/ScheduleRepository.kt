package com.example.homepage.scheduleTab.scheduleRepo

import androidx.lifecycle.MutableLiveData
import com.example.homepage.scheduleTab.scheduleModel.ScheduleData
import com.google.firebase.auth.ktx.auth
import com.google.firebase.database.*
import com.google.firebase.ktx.Firebase

class ScheduleRepository {
    val auth = Firebase.auth
    val user = auth.currentUser!!.uid
    private val scheduleReference : DatabaseReference = FirebaseDatabase.getInstance().getReference("user-tasks").child(user)
    @Volatile private var INSTANCE : ScheduleRepository ?= null
    fun getInstance(): ScheduleRepository {
        return INSTANCE ?: synchronized(this) {

            val instance = ScheduleRepository()
            INSTANCE = instance
            instance
        }
    }

    fun loadSchedules(allSchedules: MutableLiveData<List<ScheduleData>>) {

        scheduleReference.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {

                try {

                    val scheduleList : List<ScheduleData> = snapshot.children.map { dataSnapshot ->

                        dataSnapshot.getValue(ScheduleData::class.java)!!

                    }
                    allSchedules.postValue(scheduleList)

                }catch (_: Exception){

                }

            }

            override fun onCancelled(error: DatabaseError) {
                TODO("Not yet implemented")
            }


        })
    }
}