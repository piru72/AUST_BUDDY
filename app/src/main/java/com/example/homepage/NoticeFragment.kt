package com.example.homepage

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.*
import com.example.homepage.superClass.ReplaceFragment


class NoticeFragment : ReplaceFragment() {

    @SuppressLint("SetJavaScriptEnabled")
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        container?.removeAllViews()
        val v = inflater.inflate(R.layout.fragment_notice, container, false)
        val webSite = "https://www.aust.edu/notice"
        loadWebSite(webSite,v)
        return v
    }




}