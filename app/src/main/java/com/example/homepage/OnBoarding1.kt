package com.example.homepage

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class OnBoarding1 : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_on_boarding1)

        val nextButton = findViewById<Button>(R.id.onboard_1_button)
        nextButton.setOnClickListener{
            val intent = Intent(this,OnBoarding2::class.java)
            startActivity(intent)
        }
    }



}