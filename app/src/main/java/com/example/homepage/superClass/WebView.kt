package com.example.homepage.superClass

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.navArgs
import com.example.homepage.R


class WebView : ReplaceFragment() {

    private val args: WebViewArgs by navArgs()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        container?.removeAllViews()
        val view = inflater.inflate(R.layout.fragment_web_view, container, false)
        loadWebSite(args.websiteLink, view)
        return view
    }
}