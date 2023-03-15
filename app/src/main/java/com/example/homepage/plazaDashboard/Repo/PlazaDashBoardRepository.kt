package com.example.homepage.plazaDashboard.Repo

import androidx.lifecycle.MutableLiveData
import com.example.homepage.Model.Announcements

import com.google.firebase.auth.ktx.auth
import com.google.firebase.database.*
import com.google.firebase.ktx.Firebase

class PlazaDashBoardRepository {

    val auth = Firebase.auth
    val user = auth.currentUser!!.uid
    private val announcersDashboardReference: DatabaseReference =
        FirebaseDatabase.getInstance().getReference("user-posted-items").child(user)

    @Volatile
    private var INSTANCE: PlazaDashBoardRepository? = null
    fun getInstance(): PlazaDashBoardRepository {
        return INSTANCE ?: synchronized(this) {

            val instance = PlazaDashBoardRepository()
            INSTANCE = instance
            instance
        }
    }

    fun loadPlazaDashBoard(allStoreList: MutableLiveData<List<Announcements>>) {

        announcersDashboardReference.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                try {
                    val announcementList: List<Announcements> = snapshot.children.map { dataSnapshot ->
                        dataSnapshot.getValue(Announcements::class.java)!!

                    }
                    allStoreList.postValue(announcementList)
                } catch (_: Exception) {

                }
            }

            override fun onCancelled(error: DatabaseError) {

            }


        })

        announcersDashboardReference.keepSynced(true)
    }
}