package com.example.homepage.onBoarding

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import com.example.homepage.MainHomePage
import com.example.homepage.R

class OnBoarding3 : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_on_boarding3)

        val nextButton = findViewById<Button>(R.id.onboarding_3_button)
        nextButton.setOnClickListener{
            val intent = Intent(this, MainHomePage::class.java)
            startActivity(intent)
        }
    }
}