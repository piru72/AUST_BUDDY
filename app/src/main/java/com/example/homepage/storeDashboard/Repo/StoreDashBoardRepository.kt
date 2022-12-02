package com.example.homepage.storeDashboard.Repo

import androidx.lifecycle.MutableLiveData
import com.example.homepage.storeTab.Model.Materials
import com.google.firebase.auth.ktx.auth
import com.google.firebase.database.*
import com.google.firebase.ktx.Firebase

class StoreDashBoardRepository {

    val auth = Firebase.auth
    val user = auth.currentUser!!.uid
    private val storeReference: DatabaseReference =
        FirebaseDatabase.getInstance().getReference("user-posted-items").child(user)

    @Volatile
    private var INSTANCE: StoreDashBoardRepository? = null
    fun getInstance(): StoreDashBoardRepository {
        return INSTANCE ?: synchronized(this) {

            val instance = StoreDashBoardRepository()
            INSTANCE = instance
            instance
        }
    }

    fun loadStoreDashBoard(allStoreList: MutableLiveData<List<Materials>>) {

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
    }
}