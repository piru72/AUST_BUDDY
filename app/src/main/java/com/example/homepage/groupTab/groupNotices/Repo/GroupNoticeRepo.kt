package com.example.homepage.groupTab.groupNotices.Repo

import androidx.lifecycle.MutableLiveData
import com.example.homepage.groupTab.groupNotices.Model.GroupNoticeData
import com.google.firebase.auth.ktx.auth
import com.google.firebase.database.*
import com.google.firebase.ktx.Firebase

class GroupNoticeRepo(private var groupId: String) {
    val auth = Firebase.auth
    val user = auth.currentUser!!.uid

    private val groupNoticeReference : DatabaseReference = FirebaseDatabase.getInstance().getReference("group-notices").child(groupId)
    @Volatile private var INSTANCE : GroupNoticeRepo ?= null
    fun getInstance(): GroupNoticeRepo {
        return INSTANCE ?: synchronized(this) {

            val instance = GroupNoticeRepo(groupId)
            INSTANCE = instance
            instance
        }
    }

    fun loadNotices(allNotices: MutableLiveData<List<GroupNoticeData>>) {

        groupNoticeReference.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {

                try {

                    val noticeList : List<GroupNoticeData> = snapshot.children.map { dataSnapshot ->

                        dataSnapshot.getValue(GroupNoticeData::class.java)!!

                    }
                    allNotices.postValue(noticeList)

                }catch (_: Exception){

                }

            }

            override fun onCancelled(error: DatabaseError) {
                TODO("Not yet implemented")
            }


        })
    }
}