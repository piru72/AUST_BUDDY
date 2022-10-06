package com.example.homepage

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.util.Log
import android.widget.Button
import androidx.fragment.app.Fragment
import com.example.homepage.databinding.ActivityMainHomePageBinding
import com.example.homepage.onBoarding.OnBoarding1
import com.example.homepage.profileTab.ProfileFragment
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

class MainActivity : AppCompatActivity() {
    lateinit var handler: Handler

    //********************** For opening in the first onboarding page ************//
    private lateinit var auth: FirebaseAuth
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.welcome)
//        auth = Firebase.auth

        val nextButton = findViewById<Button>(R.id.get_started)
        nextButton.setOnClickListener {
            val intent = Intent(this, OnBoarding1::class.java)
            startActivity(intent)
        }


    }
//    public override fun onStart() {
//        super.onStart()
//        val currentUser = auth.currentUser
//        updateUI(currentUser)
//
//    }
    private fun updateUI(currentUser: FirebaseUser?) {
        if (currentUser != null) {
            Log.i("LoginActivity", "Update UI Called")
            val intent = Intent(this, MainHomePage::class.java)
            startActivity(intent)
            finish()
        }
    }

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


    // ********************** For opening  sign In page  page ************//

//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//        setContentView(R.layout.activity_on_boarding3)
//
//        val nextButton = findViewById<Button>(R.id.onboarding_3_button)
//        nextButton.setOnClickListener {
//            val intent = Intent(this, SignInActivity::class.java)
//            startActivity(intent)
//
//        }
//    }




    //********************** For opening directly in the main Home Page onboarding page ************//

//    private lateinit var binding: ActivityMainHomePageBinding
//
//
//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//
//        binding = ActivityMainHomePageBinding.inflate(layoutInflater)
//        setContentView(binding.root)
//        replaceFragment(HomeFragment())
//
//
//        binding.bottomNavigationView.setOnItemSelectedListener {
//
//            when (it.itemId) {
//
//                R.id.bot_nav_home -> replaceFragment(HomeFragment())
//                R.id.bot_nav_profile -> replaceFragment(ProfileFragment())
//                R.id.bot_nav_notice -> replaceFragment(NoticeFragment())
//                R.id.bot_nav_schedule -> replaceFragment(ScheduleFragment())
//                R.id.bot_nav_courses -> replaceFragment(ViewCourses())
//
//                else -> {
//
//                }
//
//            }
//            true
//
//        }
//    }
//
//    private fun replaceFragment(fragment: Fragment) {
//        val fragmentManager = supportFragmentManager
//        val fragmentTransaction = fragmentManager.beginTransaction()
//
//        fragmentTransaction.replace(R.id.frameLayout, fragment)
//        fragmentTransaction.commit()
//
//    }

    ///small change done


}
