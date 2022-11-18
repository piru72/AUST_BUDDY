package com.example.homepage.adminPanel.bugReports.Repository

import androidx.lifecycle.MutableLiveData
import com.example.homepage.adminPanel.bugReports.Model.BugReportsData
import com.google.firebase.auth.ktx.auth
import com.google.firebase.database.*
import com.google.firebase.ktx.Firebase

class BugReportRepository {

    val auth = Firebase.auth
    val user = auth.currentUser?.uid
    private val bugReportReference: DatabaseReference =
        FirebaseDatabase.getInstance().getReference("admin-bug-reports")
    private  var INSTANCE : BugReportRepository? = null

    fun getInstance() : BugReportRepository{
        return INSTANCE ?: synchronized(this){
            val instance = BugReportRepository()
            INSTANCE = instance
            instance
        }
    }

    fun loadBugReports(allBugReports : MutableLiveData<List<BugReportsData>>)
    {
        bugReportReference.addValueEventListener(object : ValueEventListener{
            override fun onDataChange(snapshot: DataSnapshot) {
                try {
                    val bugReportList : List<BugReportsData> = snapshot.children.map { dataSnapshot ->
                        dataSnapshot.getValue(BugReportsData::class.java)!!
                    }
                    allBugReports.postValue(bugReportList)
                }
                catch (_:Exception){

                }
            }

            override fun onCancelled(error: DatabaseError) {
                TODO("Not yet implemented")
            }
        })
    }
}