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

class SignInActivity : AppCompatActivity() {

    private lateinit var firebaseAuth: FirebaseAuth
    private lateinit var progressDialog: ProgressDialog


    var PREFS_NAME = "Hello"

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
            val situation = validateData(email, password)

//            val intent = Intent(this, MainHomePage::class.java)
//            startActivity(intent)


            if (situation == "OK") {
                //Toast.makeText(applicationContext, "EVERYTHING ALL RIGHT BUDDY SITUATION OK", Toast.LENGTH_SHORT).show()

                // COMING TILL HERE WITH EASE

                val situation1 = fireBaseSignin(email,password)
                Toast.makeText(applicationContext, situation1 + " <<- FIREBASE RESPONSE", Toast.LENGTH_SHORT).show()
                if (situation1 == "OK")
                {

                    Toast.makeText(applicationContext, "CALLING MAIN HOME PAGE", Toast.LENGTH_SHORT).show()
                    val intent = Intent(this, MainHomePage::class.java)
                    startActivity(intent)
                }
            } else {
                Toast.makeText(applicationContext, situation, Toast.LENGTH_SHORT).show()
            }


        }

        text2.setOnClickListener {

            val intent = Intent(this, SignUpActivity::class.java)
            startActivity(intent)
        }
    }


    private fun validateData(emailf: String, passwordf: String): String {

        var situation = "NOT POSSIBLE";

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
            situation = "OK"
        else
            situation = "Give a special character such as @,$,#.."


        return situation

    }

    private fun fireBaseSignin(email :String , password: String): String {
         progressDialog.show()
        var situationFirebase = ""
        firebaseAuth.signInWithEmailAndPassword(email,password).addOnCompleteListener {
            if(it.isSuccessful)
            {
                situationFirebase = verifyMail()
                Toast.makeText(this,"RETURNING  SITUATION -->>>  " + situationFirebase, Toast.LENGTH_SHORT).show()
            }
            else
                situationFirebase= "NOT OK"
        }

        Toast.makeText(this,"BEFORE RETURN -->>>  " + situationFirebase, Toast.LENGTH_SHORT).show()
        return  situationFirebase
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
