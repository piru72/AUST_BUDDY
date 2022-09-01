package com.example.homepage

import android.annotation.SuppressLint
import android.app.ProgressDialog
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.util.Patterns
import android.widget.Button
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.example.homepage.Entry.SigninActivity
import com.example.homepage.databinding.ActivityMainBinding
import com.example.homepage.databinding.ActivityMainHomePageBinding
import com.example.homepage.onBoarding.OnBoarding1
import com.example.homepage.profileTab.ProfileFragment
import com.google.firebase.auth.FirebaseAuth

class MainActivity : AppCompatActivity() {


    //********************** For opening in the first onboarding page ************//
//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//        setContentView(R.layout.welcome)
//
//        val nextButton = findViewById<Button>(R.id.get_started)
//        nextButton.setOnClickListener {
//            val intent = Intent(this, OnBoarding1::class.java)
//            startActivity(intent)
//        }
//    }

//********************** For opening in the third onboarding page ************//

//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//        setContentView(R.layout.activity_on_boarding3)
//
//        val nextButton = findViewById<Button>(R.id.onboarding_3_button)
//        nextButton.setOnClickListener {
//            val intent = Intent(this, MainHomePage::class.java)
//            startActivity(intent)
//        }
//    }


    //********************** For opening directly in the main Home Page onboarding page ************//

    private lateinit var binding: ActivityMainHomePageBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainHomePageBinding.inflate(layoutInflater)
        setContentView(binding.root)
        replaceFragment(HomeFragment())


        binding.bottomNavigationView.setOnItemSelectedListener {

            when (it.itemId) {

                R.id.bot_nav_home -> replaceFragment(HomeFragment())
                R.id.bot_nav_profile -> replaceFragment(ProfileFragment())
                R.id.bot_nav_notice -> replaceFragment(NoticeFragment())
                R.id.bot_nav_schedule -> replaceFragment(ScheduleFragment())
                R.id.bot_nav_courses -> replaceFragment(ViewCourses())

                else -> {

                }

            }
            true

        }
    }

    private fun replaceFragment(fragment: Fragment) {
        val fragmentManager = supportFragmentManager
        val fragmentTransaction = fragmentManager.beginTransaction()

        fragmentTransaction.replace(R.id.frameLayout, fragment)
        fragmentTransaction.commit()

    }


//    private lateinit var binding: ActivityMainBinding
//    private lateinit var progressDialog: ProgressDialog
//
//    private lateinit var firebaseAuth: FirebaseAuth
//    private var email = ""
//    private var password = ""
//    private var a = 0
//
////    private lateinit var actionBar: ActionBar
//
//    @SuppressLint("RestrictedApi")
//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//        binding = ActivityMainBinding.inflate(layoutInflater)
//
//        setContentView(binding.root)
//
////        actionBar = supportActionBar!!
////        actionBar.title = "Sign up"
////        actionBar.setDefaultDisplayHomeAsUpEnabled(true)
////        actionBar.setDisplayShowHomeEnabled(true)
//
//        progressDialog = ProgressDialog(this)
//        progressDialog.setTitle("Please wait")
//        progressDialog.setMessage("Creating Account in...")
//        progressDialog.setCanceledOnTouchOutside(false)
//
//        firebaseAuth = FirebaseAuth.getInstance()
//
//        binding.text1.setOnClickListener {
//            startActivity(Intent(this, SigninActivity::class.java))
//
//        }
//        binding.signupBtn.setOnClickListener {
//            validateData()
//        }
//    }
//
//
//    private fun validateData() {
//
//        email = binding.emailEt.text.toString()
//        password = binding.passwordEt.text.toString()
//
//        if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
//            binding.emailEt.error = "Invalid email format"
//        } else if (email.length >= 35) {
//            binding.emailEt.error = "Too long characters"
//
//        } else if (email.contains("@gmail.com", ignoreCase = true) || email.contains(
//                "@yahoo.com",
//                ignoreCase = true
//            )
//        ) {
//            binding.emailEt.error = "Provide your edu mail"
//
//        } else if (TextUtils.isEmpty(password)) {
//            binding.passwordEt.error = "Enter a password"
//
//        } else if (password.length <= 6) {
//            binding.passwordEt.error = "Password is too short"
//
//        } else if (password.contains("@") || password.contains("#") || password.contains("%")
//            || password.contains("$") || password.contains("*")
//        ) {
//            a = 1
//            fireBaseSignup()
//        } else if (a != 1) {
//            binding.passwordEt.error = "Give a special character such as @,$,#.."
//        }
//
//
//    }
//
//    private fun fireBaseSignup() {
//        progressDialog.show()
//
//        firebaseAuth.createUserWithEmailAndPassword(email, password).addOnCompleteListener {
//            if (it.isSuccessful) {
//
//                progressDialog.dismiss()
//                checkMail()
//                Toast.makeText(this, "Account created with email $email", Toast.LENGTH_SHORT).show()
//
//            } else {
//                progressDialog.dismiss()
//                Toast.makeText(this, it.exception.toString(), Toast.LENGTH_SHORT).show()
//            }
//        }
//
//    }
//
//    private fun checkMail() {
//
//        firebaseAuth.currentUser?.sendEmailVerification()?.addOnCompleteListener { task ->
//
//            if (task.isSuccessful) {
//                Toast.makeText(
//                    this,
//                    "Verification mail has been sent on this email $email",
//                    Toast.LENGTH_SHORT
//                ).show()
//                firebaseAuth.signOut()
//                finish()
//                startActivity(Intent(this, SigninActivity::class.java))
//            } else {
//                Toast.makeText(this, "Error Occured", Toast.LENGTH_SHORT).show()
//
//
//            }
//
//
//        }
//
//
//    }


}
