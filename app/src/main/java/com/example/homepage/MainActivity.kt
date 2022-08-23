package com.example.homepage

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import androidx.fragment.app.Fragment
import com.example.homepage.databinding.ActivityMainHomePageBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainHomePageBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.welcome)

        val nextButton = findViewById<Button>(R.id.get_started)
        nextButton.setOnClickListener {
            val intent = Intent(this, OnBoarding1::class.java)
            startActivity(intent)
        }
    }









}
