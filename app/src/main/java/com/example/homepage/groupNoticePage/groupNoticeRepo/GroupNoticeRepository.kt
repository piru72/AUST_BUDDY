package com.example.homepage.groupNoticePage.groupNoticeRepo

import androidx.lifecycle.MutableLiveData
import com.example.homepage.groupNoticePage.groupNoticeModel.GroupNoticeData
import com.google.firebase.auth.ktx.auth
import com.google.firebase.database.*
import com.google.firebase.ktx.Firebase

class GroupNoticeRepository(private var groupSelected: String) {
    val auth = Firebase.auth
    val user = auth.currentUser!!.uid

    private val scheduleReference: DatabaseReference =
        FirebaseDatabase.getInstance().getReference("group-notice").child(groupSelected)

    @Volatile
    private var INSTANCE: GroupNoticeRepository? = null
    fun getInstance(): GroupNoticeRepository {
        return INSTANCE ?: synchronized(this) {

            val instance = GroupNoticeRepository(groupSelected)
            INSTANCE = instance
            instance
        }
    }

    fun loadSchedules(allSchedules: MutableLiveData<List<GroupNoticeData>>) {

        scheduleReference.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {

                try {

                    val scheduleList: List<GroupNoticeData> =
                        snapshot.children.map { dataSnapshot ->

                            dataSnapshot.getValue(GroupNoticeData::class.java)!!

                        }
                    allSchedules.postValue(scheduleList)

                } catch (_: Exception) {

                }

            }

            override fun onCancelled(error: DatabaseError) {

            }


        })
    }
}