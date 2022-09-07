package com.example.homepage.onBoarding

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.widget.Button
import com.example.homepage.R
import com.example.homepage.SignInActivity
import com.example.homepage.SignUpActivity

class OnBoarding3 : AppCompatActivity() {

    lateinit var handler: Handler
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_on_boarding3)


        val nextButton = findViewById<Button>(R.id.onboarding_3_button)
        nextButton.setOnClickListener {
            val intent = Intent(this, SignInActivity::class.java)
            startActivity(intent)
        }


//        handler = Handler()
//        handler.postDelayed({
//            val intent = Intent(this, SignInActivity::class.java)
//            startActivity(intent)
//            finish()
//        }, 3000)

    }
}