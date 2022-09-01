package com.example.homepage.Entry

import android.app.ProgressDialog
import android.content.Intent
import android.os.Bundle
import android.text.TextUtils
import android.util.Patterns
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.homepage.MainActivity
import com.example.homepage.databinding.ActivitySigninBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser

class SigninActivity : AppCompatActivity() {

    private  lateinit var binding: ActivitySigninBinding
    private lateinit var firebaseAuth: FirebaseAuth
    private lateinit var progressDialog: ProgressDialog


    private  var email = ""
    private  var password =""
    private  var a = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySigninBinding.inflate(layoutInflater)
        setContentView(binding.root)

        progressDialog = ProgressDialog(this)
        progressDialog.setTitle("Please wait")
        progressDialog.setMessage("Signing into your Account in few seconds...")
        progressDialog.setCanceledOnTouchOutside(false)

        firebaseAuth = FirebaseAuth.getInstance()

        binding.text2.setOnClickListener {
            startActivity(Intent(this, MainActivity::class.java))

        }
        binding.signinBtn.setOnClickListener {
            validateData()

        }

    }
    private fun validateData() {

        email = binding.emailEt1.text.toString()
        password = binding.passwordEt1.text.toString()

        when {
            !Patterns.EMAIL_ADDRESS.matcher(email).matches() -> {
                binding.emailEt1.error="Invalid email format"
            }
            email.length>=35 -> {
                binding.emailEt1.error="Too long characters"
            }
            email.contains("@gmail.com", ignoreCase = true) || email.contains("@yahoo.com", ignoreCase = true) -> {
                binding.emailEt1.error="Provide your edu mail"
            }
            TextUtils.isEmpty(password) -> {
                binding.passwordEt1.error="Enter a password"
            }
            password.length<=6 -> {
                binding.passwordEt1.error="Password is too short"
            }
            password.contains("@") || password.contains("#") || password.contains("%") || password.contains("$") || password.contains("*") -> {
                a = 1
                fireBaseSignin()
            }
            a != 1 -> {
                binding.passwordEt1.error="Give a special character such as @,$,#.."

            }
        }


    }

    private fun fireBaseSignin() {
        progressDialog.show()

        firebaseAuth.signInWithEmailAndPassword(email,password).addOnCompleteListener {
            when {
                it.isSuccessful -> {

                    progressDialog.dismiss()
                    verifyMail()

                }
                else -> {
                    progressDialog.dismiss()
                    Toast.makeText(this,it.exception.toString(), Toast.LENGTH_SHORT).show()
                }
            }
        }

    }

    private fun verifyMail() {

        val firebaseUser: FirebaseUser? = FirebaseAuth.getInstance().currentUser
        val vEmail:Boolean? = firebaseUser?.isEmailVerified
        when {
            vEmail!! -> {
                finish()
                startActivity(Intent(this, UserProfile::class.java))
                Toast.makeText(this,"Sign in successful with email $email", Toast.LENGTH_SHORT).show()
            }
            else -> {
                Toast.makeText(this,"Please verify your email", Toast.LENGTH_SHORT).show()
                firebaseAuth.signOut()
            }
        }
    }
}