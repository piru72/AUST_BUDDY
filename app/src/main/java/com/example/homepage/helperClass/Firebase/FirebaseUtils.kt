package com.example.homepage.helperClass.Firebase

import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

object FirebaseUtils {

    private val databaseRef: DatabaseReference by lazy {
        FirebaseDatabase.getInstance().reference
    }

    fun getDatabaseReference(): DatabaseReference {
        return databaseRef
    }
}