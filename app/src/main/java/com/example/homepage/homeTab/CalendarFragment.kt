package com.example.homepage.homeTab

import android.os.Bundle

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.homepage.R
import com.example.homepage.superClass.ReplaceFragment

class CalendarFragment : ReplaceFragment() {


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        container?.removeAllViews()
        val v = inflater.inflate(R.layout.fragment_notice, container, false)
        val webSite = "https://www.aust.edu/academics/academic_calendar"
        loadWebSite(webSite, v)
        return v
    }

}