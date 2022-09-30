package com.example.homepage

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import com.example.homepage.calculator.CGPAFragment
import com.example.homepage.homeTab.*
import com.example.homepage.superClass.ReplaceFragment


class HomeFragment : ReplaceFragment() {


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        container?.removeAllViews()
        val v = inflater.inflate(R.layout.fragment_home, container, false)
        val btnIums = v.findViewById<Button>(R.id.btn_iums)
        val btnAustOj = v.findViewById<Button>(R.id.btn_aust_oj)
        val btnTeachers = v.findViewById<Button>(R.id.btn_teachers)
        val btnSyllabus = v.findViewById<Button>(R.id.btn_syllabus)
        val btnCalender = v.findViewById<Button>(R.id.btn_calender)
        val btnBus = v.findViewById<Button>(R.id.btn_buses)
        val btnCgpa = v.findViewById<Button>(R.id.btn_cgpa)
        val btnGradingSystem = v.findViewById<Button>(R.id.btn_gradings)
        val btnRequest = v.findViewById<Button>(R.id.btn_request)

        val currentState = R.id.fragment_home

        btnIums.setOnClickListener {
            replaceFragment(WebView("https://iums.aust.edu/ums-web/login/"),currentState)
        }
        btnAustOj.setOnClickListener {

            replaceFragment(WebView("https://austoj.com/"),currentState)
        }
        btnTeachers.setOnClickListener {
            replaceFragment(GradingsFragment(), currentState)
        }
        btnSyllabus.setOnClickListener {
            replaceFragment(WebView("https://www.aust.edu/cse/syllabus#spring2020"),currentState)
        }
        btnCalender.setOnClickListener {
            replaceFragment(WebView("https://www.aust.edu/academics/academic_calendar"),currentState)
        }
        btnBus.setOnClickListener {
            replaceFragment(BusFragment(), currentState)
        }
        btnCgpa.setOnClickListener {
            replaceFragment(CGPAFragment(), currentState)
        }
        btnGradingSystem.setOnClickListener {
            replaceFragment(WebView("https://www.aust.edu/academics/examincation_and_grading_system"),currentState)
        }
        btnRequest.setOnClickListener {
            replaceFragment(RequestFragment(), currentState)
        }

        return v
    }


}