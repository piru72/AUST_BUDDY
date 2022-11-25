package com.example.homepage.groupTab.groupNotices.Repo

import androidx.lifecycle.MutableLiveData
import com.example.homepage.groupTab.groupNotices.Model.GroupNoticeData
import com.google.firebase.auth.ktx.auth
import com.google.firebase.database.*
import com.google.firebase.ktx.Firebase

class NoticeRepo{
    val auth = Firebase.auth
    val user = auth.currentUser!!.uid
    private val noticeReference : DatabaseReference = FirebaseDatabase.getInstance().getReference("groupNotice").child(user)
    @Volatile private var INSTANCE : NoticeRepo?= null

    fun getInstance(): NoticeRepo {
        return INSTANCE ?: synchronized(this) {

            val instance = NoticeRepo()
            INSTANCE = instance
            instance
        }
    }

    fun loadNotices(allNoticeList: MutableLiveData<List<GroupNoticeData>>) {

        noticeReference.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {

                try {

                    val noticeList : List<GroupNoticeData> = snapshot.children.map { dataSnapshot ->

                        dataSnapshot.getValue(GroupNoticeData::class.java)!!

                    }
                    allNoticeList.postValue(noticeList)

                }catch (_: Exception){

                }

            }

            override fun onCancelled(error: DatabaseError) {
                TODO("Not yet implemented")
            }


        })
    }
}
