package com.example.homepage.profileTab

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.homepage.R
import com.example.homepage.superClass.ReplaceFragment


class AboutDevFragment : ReplaceFragment() {


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        container?.removeAllViews()

        val v = inflater.inflate(R.layout.fragment_about_dev, container, false)
//        val dev1Gmail = v.findViewById<Button>(R.id.dev1Fb)
//        val dev2Gmail = v.findViewById<Button>(R.id.dev2Fb)
//        val dev3Gmail = v.findViewById<Button>(R.id.dev3Fb)
//        val dev4Gmail = v.findViewById<Button>(R.id.dev4Fb)
//
//        val dev1Git = v.findViewById<Button>(R.id.dev1Git)
//        val dev2Git = v.findViewById<Button>(R.id.dev2Git)
//        val devGit = v.findViewById<Button>(R.id.dev4Git)
//
//
//        val currentState = R.id.fragment_about_dev
//
//
//        dev1Gmail.setOnClickListener {
//            val email = "taralamia69@gmail.com"
//            sendMail(email)
//        }
//        dev2Gmail.setOnClickListener {
//            val email = "parvezdirom2000@gmail.com"
//            sendMail(email)
//        }
//        dev3Gmail.setOnClickListener {
//            val email = "mirazzim38@gmail.com"
//            sendMail(email)
//        }
//        dev4Gmail.setOnClickListener {
//            val email = "sabbirahmedsalman2000@gmail.com"
//            sendMail(email)
//        }
//
//        dev1Git.setOnClickListener {
//
//            val action = AboutDevFragmentDirections.actionAboutDevFragmentToWebView2("https://github.com/piru72" ,"view")
//
//            findNavController().navigate(action)
//        }
//        dev2Git.setOnClickListener {
//
//            val action = AboutDevFragmentDirections.actionAboutDevFragmentToWebView2("https://github.com/piru72" ,"view")
//            findNavController().navigate(action)
//        }
//        dev4Git.setOnClickListener {
//            val action = AboutDevFragmentDirections.actionAboutDevFragmentToWebView2("https://github.com/arman-mahy" ,"view")
//            findNavController().navigate(action)
//        }

        return v
    }


}