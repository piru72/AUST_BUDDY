package com.example.homepage.plaza.Repo

import androidx.lifecycle.MutableLiveData
import com.example.homepage.plaza.Model.Materials
import com.google.firebase.auth.ktx.auth
import com.google.firebase.database.*
import com.google.firebase.ktx.Firebase

class StoreRepository {
    val auth = Firebase.auth
    val user = auth.currentUser!!.uid
    private val storeReference: DatabaseReference =
        FirebaseDatabase.getInstance().getReference("public-posts")

    @Volatile
    private var INSTANCE: StoreRepository? = null
    fun getInstance(): StoreRepository {
        return INSTANCE ?: synchronized(this) {

            val instance = StoreRepository()
            INSTANCE = instance
            instance
        }
    }

    fun loadStore(allStoreList: MutableLiveData<List<Materials>>) {

        storeReference.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                try {
                    val storeList: List<Materials> = snapshot.children.map { dataSnapshot ->
                        dataSnapshot.getValue(Materials::class.java)!!

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