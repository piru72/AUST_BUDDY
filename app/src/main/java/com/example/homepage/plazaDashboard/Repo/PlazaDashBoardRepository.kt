package com.example.homepage.plazaDashboard.Repo

import androidx.lifecycle.MutableLiveData
import com.example.homepage.plaza.Model.Announcements

import com.google.firebase.auth.ktx.auth
import com.google.firebase.database.*
import com.google.firebase.ktx.Firebase

class PlazaDashBoardRepository {

    val auth = Firebase.auth
    val user = auth.currentUser!!.uid
    private val storeReference: DatabaseReference =
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

    fun loadStoreDashBoard(allStoreList: MutableLiveData<List<Announcements>>) {

        storeReference.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                try {
                    val storeList: List<Announcements> = snapshot.children.map { dataSnapshot ->
                        dataSnapshot.getValue(Announcements::class.java)!!

                    }
                    allStoreList.postValue(storeList)
                } catch (_: Exception) {

                }
            }

            override fun onCancelled(error: DatabaseError) {

            }


        })

        storeReference.keepSynced(true)
    }
}