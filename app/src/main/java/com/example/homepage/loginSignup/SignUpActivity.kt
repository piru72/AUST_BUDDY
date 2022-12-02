package com.example.homepage.loginSignup

import android.content.Intent
import android.os.Bundle
import android.text.TextUtils
import android.util.Patterns
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.homepage.R
import com.example.homepage.databinding.ActivitySignUpBinding
import com.google.firebase.auth.FirebaseAuth

class SignUpActivity : AppCompatActivity() {
    private lateinit var binding: ActivitySignUpBinding

    private lateinit var firebaseAuth: FirebaseAuth
    private var email = ""
    private var password = ""
    private var a = 0
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivitySignUpBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
        firebaseAuth = FirebaseAuth.getInstance()

        binding.signupBtn.setOnClickListener {
            email = findViewById<TextView>(R.id.emailEt).text.toString()
            password = findViewById<TextView>(R.id.passwordEt).text.toString()
            val situation = validateData(email, password)
            if (situation == "OK") {
                Toast.makeText(
                    applicationContext,
                    "CHECK YOUR EMAILS SPAM BOX FOR VERIFICATION EMAIL",
                    Toast.LENGTH_SHORT
                )
                    .show()
                fireBaseSignup()
                val intent = Intent(this, SignInActivity::class.java)
                startActivity(intent)
            } else {
                Toast.makeText(applicationContext, situation, Toast.LENGTH_SHORT).show()
            }
        }
        binding.goToSignInPage.setOnClickListener {

            val intent = Intent(this, SignInActivity::class.java)
            startActivity(intent)
        }

    }

    private fun validateData(emailFunction: String, passwordFunction: String): String {

        email = emailFunction
        password = passwordFunction
        val situation: String

        if (!Patterns.EMAIL_ADDRESS.matcher(email).matches())
            situation = "Invalid email format"
        else if (email.length >= 35)
            situation = "Too long characters"
        else if (email.contains("@gmail.com", ignoreCase = true) || email.contains(
                "@yahoo.com",
                ignoreCase = true
            )
        )
            situation = "Provide your edu mail"
        else if (TextUtils.isEmpty(password))
            situation = "Enter a password"
        else if (password.length <= 6)
            situation = "Password is too short"
        else if (password.contains("@") || password.contains("#") || password.contains("%")
            || password.contains("$") || password.contains("*")
        )
            situation = "OK"
        else if (a != 1)
            situation = "Give a special character such as @,$,#.."
        else
            situation = "GOD KNOWS WHAT HAPPENED!"
        return situation
    }


    private fun fireBaseSignup() {

        firebaseAuth.createUserWithEmailAndPassword(email, password).addOnCompleteListener {
            if (it.isSuccessful) {

                checkMail()
                Toast.makeText(this, "Account created with email $email", Toast.LENGTH_SHORT).show()

            } else {
                Toast.makeText(this, it.exception.toString(), Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun checkMail() {

        firebaseAuth.currentUser?.sendEmailVerification()?.addOnCompleteListener { task ->

            if (task.isSuccessful) {
                Toast.makeText(
                    this,
                    "Verification mail has been sent on this email $email",
                    Toast.LENGTH_SHORT
                ).show()
            } else {
                Toast.makeText(this, "Error Occurred", Toast.LENGTH_SHORT).show()
            }
        }
    }
}