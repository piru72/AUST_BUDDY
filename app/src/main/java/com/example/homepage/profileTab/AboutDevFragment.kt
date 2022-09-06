package com.example.homepage.profileTab

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import com.example.homepage.R
import com.example.homepage.databinding.FragmentAboutDevBinding
import com.example.homepage.superClass.ReplaceFragment


class AboutDevFragment : ReplaceFragment() {


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        container?.removeAllViews()

        val v = inflater.inflate(R.layout.fragment_about_dev, container, false)
        val dev1Gmail = v.findViewById<Button>(R.id.dev1Fb)
        val dev2Gmail = v.findViewById<Button>(R.id.dev2Fb)
        val dev3Gmail = v.findViewById<Button>(R.id.dev3Fb)

        dev1Gmail.setOnClickListener {
            val email = "taralamia69@gmail.com"
            sendMail(email)
        }
        dev2Gmail.setOnClickListener {
            val email = "parvezdirom2000@gmail.com"
            sendMail(email)
        }
        dev3Gmail.setOnClickListener {
            val email = "mirazzim38@gmail.com"
            sendMail(email)
        }




        return v
    }





}