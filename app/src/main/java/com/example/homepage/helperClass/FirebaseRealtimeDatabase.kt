package com.example.homepage.helperClass

import android.util.Log
import com.google.firebase.database.*

class FirebaseRealtimeDatabase {
    // Given parentNode and childNode this function will remove the child from the parent
    fun removeChild(parentNode: String, childNode: String) {
        val parentReference = FirebaseDatabase.getInstance().getReference(parentNode)
        parentReference.child(childNode).removeValue()
    }
    // Given Source and destination path this function will copy a node from one node to another
     fun moveChild(fromPath: String, toPath: String) {
         val fromReference =  FirebaseDatabase.getInstance().getReference(fromPath)
         val toReference = FirebaseDatabase.getInstance().getReference(toPath)

         fromReference.addListenerForSingleValueEvent(object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                toReference.setValue(
                    dataSnapshot.value
                )
            }

            override fun onCancelled(databaseError: DatabaseError) {}
        })
    }

    fun <T> fetchData(reference: DatabaseReference, dataClass: Class<T>): T? {
        var data: T? = null
        val listener = object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                data = dataSnapshot.getValue(dataClass)
            }

            override fun onCancelled(databaseError: DatabaseError) {
                Log.w("BUG", "loadData:onCancelled", databaseError.toException())
            }
        }
        reference.addValueEventListener(listener)
        return data
    }

}