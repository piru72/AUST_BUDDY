package com.example.homepage.adminPanel.adminRequest.Repo

import androidx.lifecycle.MutableLiveData
import com.example.homepage.adminPanel.adminRequest.Model.Admin
import com.google.firebase.auth.ktx.auth
import com.google.firebase.database.*
import com.google.firebase.ktx.Firebase

class AdminRequestRepository {

    private var databaseParentNode = "/admin-admin-request/"
    val auth = Firebase.auth
    val user = auth.currentUser!!.uid
    private val adminRequestReference: DatabaseReference =
        FirebaseDatabase.getInstance().getReference(databaseParentNode)

    @Volatile
    private var INSTANCE: AdminRequestRepository ? = null
    fun getInstance(): AdminRequestRepository  {
        return INSTANCE ?: synchronized(this) {

            val instance = AdminRequestRepository ()
            INSTANCE = instance
            instance
        }
    }

    fun loadAdminRequest(allAdminRequestList: MutableLiveData<List<Admin>>) {

        adminRequestReference.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                try {
                    val adminRequestList: List<Admin> = snapshot.children.map { dataSnapshot ->
                        dataSnapshot.getValue(Admin::class.java)!!

                    }
                    allAdminRequestList.postValue(adminRequestList)
                } catch (_: Exception) {

                }
            }

            override fun onCancelled(error: DatabaseError) {

            }


        })

        adminRequestReference.keepSynced(true)
    }
}






