package com.example.homepage

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

class SignInActivity : AppCompatActivity() {

    private lateinit var firebaseAuth: FirebaseAuth

    private  var email = ""
    private  var password =""
    private  var a = 0
    var PREFS_NAME = "Hello"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_in)

        firebaseAuth = FirebaseAuth.getInstance()



        val signInButton = findViewById<Button>(R.id.signinBtn)
        val text2 = findViewById<TextView>(R.id.text2)

        signInButton.setOnClickListener {


            email = findViewById<TextView>(R.id.emailEt1).text.toString()
            password = findViewById<TextView>(R.id.passwordEt1).text.toString()
            val situation = validateData(email, password)

            val intent = Intent(this, MainHomePage::class.java)
            startActivity(intent)


//            if (situation == "OK") {
//                Toast.makeText(applicationContext, "EVERYTHING ALL RIGHT BUDDY SITUATION OK", Toast.LENGTH_SHORT).show()
//                if (fireBaseSignin() == "OK")
//                {
//
//                    Toast.makeText(applicationContext, "CALLING MAIN HOME PAGE", Toast.LENGTH_SHORT)
//                    val intent = Intent(this, MainHomePage::class.java)
//                    startActivity(intent)
//                }
//                else
//                    Toast.makeText(applicationContext, "FAILED FIREBASE SIGN IN", Toast.LENGTH_SHORT).show()
//
//            } else {
//                Toast.makeText(applicationContext, situation, Toast.LENGTH_SHORT).show()
//            }


        }

        text2.setOnClickListener {

            val intent = Intent(this, SignUpActivity::class.java)
            startActivity(intent)
        }
    }


    private fun validateData(emailf: String, passwordf: String): String {

        email = emailf
        password = passwordf
        var situation = "NOT POSSIBLE";

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
            situation = "GOD KNOWS WHAT HAPPEND!"

        return situation

    }

    private fun fireBaseSignin(): String {
        // progressDialog.show()
        var situation = ""
        firebaseAuth.signInWithEmailAndPassword(email,password).addOnCompleteListener {
            if(it.isSuccessful)
            {

                //  progressDialog.dismiss()
                situation = verifyMail()

            }
            else
            {
                //progressDialog.dismiss()
                Toast.makeText(this,it.exception.toString(), Toast.LENGTH_SHORT).show()
                situation= "NOT OK"
            }
        }

        return  situation

    }


    private fun verifyMail() :String {

        val firebaseUser: FirebaseUser? = FirebaseAuth.getInstance().currentUser
        val vEmail:Boolean? = firebaseUser?.isEmailVerified
        var situation = ""
        if(vEmail!!)
        {


            var sharedPreferences = getSharedPreferences("PREFS_NAME",0)
            sharedPreferences.edit()
            sharedPreferences.edit().putBoolean("hasLoggedIn",true)
            sharedPreferences.edit().commit()

            println("$PREFS_NAME")
            //startActivity(Intent(this@SigninActivity,UserProfile::class.java))
            finish()
            //Toast.makeText(this@SigninActivity,"Sign in successful with email $email", Toast.LENGTH_SHORT).show()

            situation ="OK"


        }
        else
        {
            Toast.makeText(this,"Please verify your email",Toast.LENGTH_SHORT).show()
            firebaseAuth.signOut()

            situation =" NOT OK BUDDY"
        }

        return  situation
    }
}
