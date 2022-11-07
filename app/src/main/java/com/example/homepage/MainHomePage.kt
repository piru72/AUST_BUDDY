package com.example.homepage

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.example.homepage.courseTab.DepartmentSemesterChooserFragment
import com.example.homepage.databinding.ActivityMainHomePageBinding
import com.example.homepage.profileTab.ProfileFragment
import com.example.homepage.scheduleTab.SchedulesFragment
import com.example.homepage.storeTab.StoreFragment

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
                R.id.bot_nav_store -> replaceFragment(StoreFragment())
                R.id.bot_nav_schedule -> replaceFragment(SchedulesFragment())
                R.id.bot_nav_courses -> replaceFragment(DepartmentSemesterChooserFragment())

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