package com.example.homepage.plaza.Repo

import androidx.lifecycle.MutableLiveData
import com.example.homepage.plaza.Model.Announcements
import com.google.firebase.auth.ktx.auth
import com.google.firebase.database.*
import com.google.firebase.ktx.Firebase

class PlazaRepository(viewPath: String) {
    private var databaseParentNode = viewPath
    val auth = Firebase.auth
    val user = auth.currentUser!!.uid
    private val announcementReference: DatabaseReference =
        FirebaseDatabase.getInstance().getReference(databaseParentNode)

    @Volatile
    private var INSTANCE: PlazaRepository? = null
    fun getInstance(): PlazaRepository {
        return INSTANCE ?: synchronized(this) {

            val instance = PlazaRepository(databaseParentNode)
            INSTANCE = instance
            instance
        }
    }

    fun loadStore(allAnnouncementList: MutableLiveData<List<Announcements>>) {

        announcementReference.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                try {
                    val announcementList: List<Announcements> = snapshot.children.map { dataSnapshot ->
                        dataSnapshot.getValue(Announcements::class.java)!!

                    }
                    allAnnouncementList.postValue(announcementList)
                } catch (_: Exception) {

                }
            }

            override fun onCancelled(error: DatabaseError) {

            }


        })

        announcementReference.keepSynced(true)
    }
}