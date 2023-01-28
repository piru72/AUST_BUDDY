package com.example.homepage.superClass

import com.google.firebase.database.FirebaseDatabase

class FirebaseRealtimeDatabase {

    fun removeChild(parentNode: String, childNode: String) {
        val parentReference = FirebaseDatabase.getInstance().getReference(parentNode)
        parentReference.child(childNode).removeValue()
    }

}