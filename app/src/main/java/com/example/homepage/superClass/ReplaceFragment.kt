package com.example.homepage.superClass

import android.annotation.SuppressLint
import android.view.View
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.fragment.app.Fragment
import com.example.homepage.R


open class ReplaceFragment : Fragment() {


    fun replaceFragment(fragment: Fragment, xml_file_name: Int) {
        val fragmentManager = requireActivity().supportFragmentManager
        val fragmentTransaction = fragmentManager.beginTransaction()
        fragmentTransaction.replace(xml_file_name, fragment)
        fragmentTransaction.addToBackStack(null)
        fragmentTransaction.commit()
    }

    @SuppressLint("SetJavaScriptEnabled")
    fun loadWebSite(webSite : String, v : View){
        val webView = v.findViewById<WebView>(R.id.mWebView)
        webView.webViewClient = WebViewClient()
        webView.canGoBack()
        webView.apply {
            loadUrl(webSite)
            settings.javaScriptEnabled = true
            settings.safeBrowsingEnabled = true
        }

    }


}