package com.example.homepage.adminPanel.adminRequest.Repo

import androidx.lifecycle.MutableLiveData
import com.example.homepage.Model.Admin
import com.google.firebase.database.*

class AdminRequestRepository(database: FirebaseDatabase) {

    private var databaseParentNode = "/admin-admin-request/"
    private val adminRequestReference: DatabaseReference = database.getReference(databaseParentNode)


    fun loadAdminRequest(allAdminRequestList: MutableLiveData<List<Admin>>) {

        adminRequestReference.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                try {
                    val adminRequestList: List<Admin> =
                        snapshot.children.mapNotNull { dataSnapshot ->
                            dataSnapshot.getValue(Admin::class.java)!!

                        }
                    allAdminRequestList.postValue(adminRequestList)
                } catch (_: Exception) {
                    // Provide error message
                }
            }

            override fun onCancelled(error: DatabaseError) {
                // Provide cancel message
            }


        })

        adminRequestReference.keepSynced(true)
    }
}






