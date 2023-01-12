package com.example.homepage.plaza.Repo

import androidx.lifecycle.MutableLiveData
import com.example.homepage.plaza.Model.Announcements
import com.google.firebase.auth.ktx.auth
import com.google.firebase.database.*
import com.google.firebase.ktx.Firebase

class PlazaRepository {
    val auth = Firebase.auth
    val user = auth.currentUser!!.uid
    private val storeReference: DatabaseReference =
        FirebaseDatabase.getInstance().getReference("public-posts")

    @Volatile
    private var INSTANCE: PlazaRepository? = null
    fun getInstance(): PlazaRepository {
        return INSTANCE ?: synchronized(this) {

            val instance = PlazaRepository()
            INSTANCE = instance
            instance
        }
    }

    fun loadStore(allStoreList: MutableLiveData<List<Announcements>>) {

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