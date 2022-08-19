package com.example.homepage

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import com.example.homepage.databinding.ActivityMainHomePageBinding

class MainHomePage : AppCompatActivity() {

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
                R.id.bot_nav_courses -> replaceFragment(CourseFragment())

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

}