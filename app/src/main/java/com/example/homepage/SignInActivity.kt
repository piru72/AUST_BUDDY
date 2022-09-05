package com.example.homepage

import android.app.ProgressDialog
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.util.Log
import android.util.Patterns
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import com.example.homepage.databinding.ActivitySignInBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.auth.ktx.auth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase
import java.util.*

class SignInActivity : AppCompatActivity() {

    private lateinit var auth: FirebaseAuth
    private lateinit var progressDialog: ProgressDialog
    private lateinit var database: DatabaseReference
    private lateinit var binding: ActivitySignInBinding


    var PREFS_NAME = "Hello"

    var finalValue = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_sign_in)

        auth = Firebase.auth
        database = Firebase.database.reference

        progressDialog = ProgressDialog(this)
        progressDialog.setTitle("Please wait")
        progressDialog.setMessage("Signing into your Account in few seconds...")
        progressDialog.setCanceledOnTouchOutside(false)

        val text2 = findViewById<TextView>(R.id.text2)

        binding.signinBtn.setOnClickListener {

            val email = findViewById<TextView>(R.id.emailEt1).text.toString()
            val password = findViewById<TextView>(R.id.passwordEt1).text.toString()

            //** USER EMAIL AND PASSWORD IS VALIDATED IF THEY ARE IN THE RIGHT FORMAT OR NOT
            val situation = validateData(email, password)

            if (situation == "USER EMAIL PASS VERIFIED") {
                fireBaseSignin(email, password)
            } else {
                Toast.makeText(this, situation, Toast.LENGTH_SHORT).show()
            }
        }

        text2.setOnClickListener {

            val intent = Intent(this, SignUpActivity::class.java)
            startActivity(intent)
        }
    }

    public override fun onStart() {
        super.onStart()
        val currentUser = auth.currentUser
        updateUI(currentUser)

    }

    private fun updateUI(currentUser: FirebaseUser?) {
        if (currentUser != null) {
            Log.i("LoginActivity", "Update UI Called")
            val intent = Intent(this, MainHomePage::class.java)
            startActivity(intent)
            finish()
        }
    }

    private fun writeNewUser(userId: String, name: String, email: String?) {
        val user = User(name, email)
        database.child("users").child(userId).setValue(user)
    }

    private fun usernameFromEmail(email: String): String {
        return if (email.contains("@")) {
            email.split("@".toRegex()).dropLastWhile { it.isEmpty() }.toTypedArray()[0]
        } else {
            email
        }
    }

    private fun onAuthSuccess(user: FirebaseUser) {
        val username = usernameFromEmail(user.email!!)
        // Write new user
        writeNewUser(user.uid, username, user.email)
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

    private fun fireBaseSignin(email: String, password: String) {
        progressDialog.show()
        auth.signInWithEmailAndPassword(email, password).addOnCompleteListener { task ->
            when {
                task.isSuccessful -> {
                    progressDialog.dismiss()
                    finalValue = verifyMail()
                    when (finalValue) {
                        "OK" -> {
                            val user = auth.currentUser
                            onAuthSuccess(task.result?.user!!)
                            updateUI(user)
                        }
                    }
                }
                else -> {
                    progressDialog.dismiss()
                }
            }
        }


    }


    private fun verifyMail(): String {

        val firebaseUser: FirebaseUser? = FirebaseAuth.getInstance().currentUser
        val vEmail: Boolean? = firebaseUser?.isEmailVerified
        var situation = ""
        if (vEmail!!) {
            situation = "OK"
        } else {
            Toast.makeText(this, "Please verify your email", Toast.LENGTH_SHORT).show()
            //firebaseAuth.signOut()
            situation = "NOT OK"
        }
        return situation
    }
}
