package com.example.homepage.profileTab

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.navigation.Navigation.findNavController
import androidx.navigation.fragment.findNavController
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
        val dev1Gmail = v.findViewById<ImageView>(R.id.dev1gmail)
        val dev2Gmail = v.findViewById<ImageView>(R.id.dev2gmail)
        val dev3Gmail = v.findViewById<ImageView>(R.id.dev3gmail)

        val dev1Github = v.findViewById<ImageView>(R.id.dev1github)
        val dev2Github = v.findViewById<ImageView>(R.id.dev2github)
        val dev3Github = v.findViewById<ImageView>(R.id.dev3github)

        val dev1fb = v.findViewById<ImageView>(R.id.dev1facebook)
        val dev2fb = v.findViewById<ImageView>(R.id.dev2facebook)
        val dev3fb = v.findViewById<ImageView>(R.id.dev3facebook)

        val projectGithub = v.findViewById<ImageView>(R.id.project_git)
//
//
        val currentState = R.id.fragment_about_dev
//
//
        dev1Gmail.setOnClickListener {
            val email = "taralamia69@gmail.com"
            sendMail(email)
        }
        dev2Gmail.setOnClickListener {
            val email = "parvezdirom2000@gmail.com"
            sendMail(email)
        }
        dev3Gmail.setOnClickListener {
            val email = "say2mahy@gmail.com"
            sendMail(email)
        }


        dev1Github.setOnClickListener {

            val action = AboutDevFragmentDirections.actionAboutDevFragmentToWebView2("https://github.com/taralamia" ,"view")

            findNavController().navigate(action)
        }
        dev2Github.setOnClickListener {

            val action = AboutDevFragmentDirections.actionAboutDevFragmentToWebView2("https://github.com/piru72" ,"view")
            findNavController().navigate(action)
        }
        dev3Github.setOnClickListener {
            val action = AboutDevFragmentDirections.actionAboutDevFragmentToWebView2("https://github.com/arman-mahy" ,"view")
            findNavController().navigate(action)
        }

        dev1fb.setOnClickListener {
            val action = AboutDevFragmentDirections.actionAboutDevFragmentToWebView2("https://www.facebook.com/tabassum.taralamya" ,"view")
            findNavController().navigate(action)
        }
        dev2fb.setOnClickListener {
            val action = AboutDevFragmentDirections.actionAboutDevFragmentToWebView2("https://www.facebook.com/piru72" ,"view")
            findNavController().navigate(action)
        }
        dev3fb.setOnClickListener {
            val action = AboutDevFragmentDirections.actionAboutDevFragmentToWebView2("https://www.facebook.com/arman.mahy.5/" ,"view")
            findNavController().navigate(action)
        }

        projectGithub.setOnClickListener {
            val action = AboutDevFragmentDirections.actionAboutDevFragmentToWebView2("https://github.com/piru72/Uni_buddy" ,"view")
            findNavController().navigate(action)
        }


        return v
    }


}