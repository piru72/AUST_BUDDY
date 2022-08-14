package com.example.homepage

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class MainActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.welcome)


        val nextButton = findViewById<Button>(R.id.get_started)
        nextButton.setOnClickListener{
            val intent = Intent(this,OnBoarding1::class.java)
            startActivity(intent)
        }
    }
}