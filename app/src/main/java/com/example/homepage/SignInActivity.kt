package com.example.homepage

import android.app.ProgressDialog
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.util.Patterns
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import java.util.*
import kotlin.concurrent.schedule
import kotlinx.coroutines.delay as delay1

class SignInActivity : AppCompatActivity() {

    private lateinit var firebaseAuth: FirebaseAuth
    private lateinit var progressDialog: ProgressDialog


    var PREFS_NAME = "Hello"

    var finalValue=""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_in)

        progressDialog = ProgressDialog(this)
        progressDialog.setTitle("Please wait")
        progressDialog.setMessage("Signing into your Account in few seconds...")
        progressDialog.setCanceledOnTouchOutside(false)

        firebaseAuth = FirebaseAuth.getInstance()

        val signInButton = findViewById<Button>(R.id.signinBtn)
        val text2 = findViewById<TextView>(R.id.text2)

        signInButton.setOnClickListener {

            val email = findViewById<TextView>(R.id.emailEt1).text.toString()
            val password = findViewById<TextView>(R.id.passwordEt1).text.toString()

            //** USER EMAIL AND PASSWORD IS VALIDATED IF THEY ARE IN THE RIGHT FORMAT OR NOT
            val situation = validateData(email, password)

            if (situation == "USER EMAIL PASS VERIFIED") {
                fireBaseSignin(email,password)
            } else {
                Toast.makeText(this, situation, Toast.LENGTH_SHORT).show()
            }
        }

        text2.setOnClickListener {

            val intent = Intent(this, SignUpActivity::class.java)
            startActivity(intent)
        }
    }

    private fun validateData(emailf: String, passwordf: String): String {

        var situation = ""

        if (!Patterns.EMAIL_ADDRESS.matcher(emailf).matches())
            situation = "Invalid email format"
        else if (emailf.length >= 35)
            situation = "Too long characters"
        else if (emailf.contains("@gmail.com", ignoreCase = true) || emailf.contains(
                "@yahoo.com",
                ignoreCase = true
            )
        )
            situation = "Provide your edu mail"
        else if (TextUtils.isEmpty(passwordf))
            situation = "Enter a password"
        else if (passwordf.length <= 6)
            situation = "Password is too short"
        else if (passwordf.contains("@") || passwordf.contains("#") || passwordf.contains("%")
            || passwordf.contains("$") || passwordf.contains("*")
        )
            situation = "USER EMAIL PASS VERIFIED"
        else
            situation = "Give a special character such as @,$,#.."
        return situation

    }

    private fun fireBaseSignin(email :String , password: String) {
         progressDialog.show()
        firebaseAuth.signInWithEmailAndPassword(email,password).addOnCompleteListener {
            when {
                it.isSuccessful -> {
                    progressDialog.dismiss()
                    finalValue = verifyMail()
                    if (finalValue=="OK")
                    {

                        val intent = Intent(this, MainHomePage::class.java)
                        startActivity(intent)

                    }

                }
                else -> {
                    progressDialog.dismiss()
                }
            }
        }


    }


    private fun verifyMail() :String {

        val firebaseUser: FirebaseUser? = FirebaseAuth.getInstance().currentUser
        val vEmail:Boolean? = firebaseUser?.isEmailVerified
        var situation = ""
        if(vEmail!!)
        {
            situation ="OK"
        }
        else
        {
            Toast.makeText(this,"Please verify your email",Toast.LENGTH_SHORT).show()
            //firebaseAuth.signOut()
            situation ="NOT OK"
        }
        return  situation
    }
}
