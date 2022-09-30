package com.example.homepage.onBoarding

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.util.Log
import android.widget.Button
import com.example.homepage.MainHomePage
import com.example.homepage.R
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

class OnBoarding1 : AppCompatActivity() {

    lateinit var handler: Handler
    private lateinit var auth: FirebaseAuth
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_on_boarding1)

        auth = Firebase.auth

        val nextButton = findViewById<Button>(R.id.onboard_1_button)
        nextButton.setOnClickListener {
            val intent = Intent(this, OnBoarding2::class.java)
            startActivity(intent)
        }

//        handler = Handler()
//        handler.postDelayed({
//            val intent = Intent(this, OnBoarding2::class.java)
//            startActivity(intent)
//            finish()
//        }, 3000)
    }

//    public override fun onStart() {
//        super.onStart()
//        val currentUser = auth.currentUser
//        updateUI(currentUser)
//
//    }
//    private fun updateUI(currentUser: FirebaseUser?) {
//        if (currentUser != null) {
//            Log.i("LoginActivity", "Update UI Called")
//            val intent = Intent(this, MainHomePage::class.java)
//            startActivity(intent)
//            finish()
//        }
//    }





}