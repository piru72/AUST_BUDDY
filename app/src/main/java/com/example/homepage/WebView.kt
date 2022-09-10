package com.example.homepage

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.homepage.superClass.ReplaceFragment


class WebView(webLink: String) : ReplaceFragment() {

    private val webSite = webLink
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        container?.removeAllViews()
        val v = inflater.inflate(R.layout.fragment_notice, container, false)
        loadWebSite(webSite, v)
        return v
    }
}