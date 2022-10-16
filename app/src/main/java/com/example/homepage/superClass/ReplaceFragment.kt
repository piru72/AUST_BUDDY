package com.example.homepage.superClass

import android.annotation.SuppressLint
import android.content.Context.CONNECTIVITY_SERVICE
import android.content.Intent
import android.net.ConnectivityManager
import android.net.Uri
import android.view.View
import android.webkit.WebView
import android.webkit.WebViewClient
import android.widget.Toast
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


    fun replaceFragment(fragment: Fragment, xml_file_name: Int, message: String) {
        val fragmentManager = requireActivity().supportFragmentManager
        val fragmentTransaction = fragmentManager.beginTransaction()
        fragmentTransaction.replace(xml_file_name, fragment)
        fragmentTransaction.addToBackStack(null)
        fragmentTransaction.commit()
    }

    @SuppressLint("SetJavaScriptEnabled")
    fun loadWebSite(webSite: String, v: View) {


        val webView = v.findViewById<WebView>(R.id.mWebView)
        webView.webViewClient = WebViewClient()

        val connectivityManager =
            context?.getSystemService(CONNECTIVITY_SERVICE) as ConnectivityManager

        if (!(connectivityManager.activeNetworkInfo != null && connectivityManager.activeNetworkInfo!!.isAvailable && connectivityManager.activeNetworkInfo!!.isConnected)) {
            Toast.makeText(context, "No Internet Connection!", Toast.LENGTH_SHORT).show()
        }
        else
        {
            webView.apply {
                loadUrl(webSite)
                settings.javaScriptEnabled = true
                settings.setSupportZoom(true)
                settings.safeBrowsingEnabled = true
                settings.allowFileAccess = true
                settings.domStorageEnabled = true
                settings.javaScriptCanOpenWindowsAutomatically = true
                settings.supportMultipleWindows()
            }

        }




    }

    fun sendMail(email: String) {

        val addresses = email.split(",".toRegex()).toTypedArray()
        val intent = Intent(Intent.ACTION_SENDTO).apply {
            data = Uri.parse("mailto:")
            putExtra(Intent.EXTRA_EMAIL, addresses)
            putExtra(Intent.EXTRA_SUBJECT, "A LITTLE CHIT CHAT ABOUT UNIBUDDY APP")
        }
        startActivity(intent)

    }


}