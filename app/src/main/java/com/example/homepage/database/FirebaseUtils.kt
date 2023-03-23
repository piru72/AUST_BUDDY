package com.example.homepage.database

import com.google.firebase.auth.ktx.auth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.ktx.Firebase

object FirebaseUtils {

    private val databaseRef: DatabaseReference by lazy {
        FirebaseDatabase.getInstance().reference
    }

    fun getDatabaseReference(): DatabaseReference {
        return databaseRef
    }

    fun getUserId(): String {
        val auth = Firebase.auth
        val currentUser = auth.currentUser
        return currentUser?.uid ?: throw IllegalStateException("User is not authenticated")
    }

    fun getReference(path: String): DatabaseReference {
        return databaseRef.child(path)
    }
}