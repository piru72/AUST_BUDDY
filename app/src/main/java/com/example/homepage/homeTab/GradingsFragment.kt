package com.example.homepage.homeTab

import android.os.Bundle

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.homepage.R
import com.example.homepage.superClass.ReplaceFragment


class GradingsFragment : ReplaceFragment() {


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        container?.removeAllViews()
        val v = inflater.inflate(R.layout.fragment_notice, container, false)
        val webSite = "https://www.aust.edu/cse/faculty_members"
        loadWebSite(webSite,v)
        return v
    }


}