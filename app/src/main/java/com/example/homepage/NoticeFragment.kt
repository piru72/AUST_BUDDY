package com.example.homepage

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import com.example.homepage.homeTab.RequestFragment
import com.example.homepage.superClass.ReplaceFragment


class NoticeFragment : ReplaceFragment()  {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        container?.removeAllViews()
        val v = inflater.inflate(R.layout.fragment_notice, container, false)
        val button = v.findViewById<Button>(R.id.notice_button)
        button.setOnClickListener{
            replaceFragment(RequestFragment(),R.id.fragment_notice)
        }
        return v
    }

}