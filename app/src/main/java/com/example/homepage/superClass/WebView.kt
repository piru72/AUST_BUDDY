package com.example.homepage.superClass

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.homepage.R


class WebView(webLink: String) : ReplaceFragment() {

    private val webSite = webLink
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        container?.removeAllViews()
        val v = inflater.inflate(R.layout.fragment_web_view, container, false)
        loadWebSite(webSite, v)
        return v
    }
}