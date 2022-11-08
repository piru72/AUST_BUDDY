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
import java.util.*


open class ReplaceFragment : Fragment() {

    private var currentEmail = ""
    private var currentName = ""
    private var currentId = ""
    private var currentDept = ""
    private var currentSession = ""
    fun replaceFragment(fragment: Fragment, xml_file_name: Int) {
        val fragmentManager = requireActivity().supportFragmentManager
        fragmentManager.beginTransaction().replace(xml_file_name, fragment).addToBackStack("tag").commit()
    }




    @SuppressLint("SetJavaScriptEnabled")
    fun loadWebSite(webSite: String, v: View) {


        val webView = v.findViewById<WebView>(R.id.mWebView)
        webView.webViewClient = WebViewClient()

        val connectivityManager =
            context?.getSystemService(CONNECTIVITY_SERVICE) as ConnectivityManager

        if (!(connectivityManager.activeNetworkInfo != null && connectivityManager.activeNetworkInfo!!.isAvailable && connectivityManager.activeNetworkInfo!!.isConnected)) {
            Toast.makeText(context, "No Internet Connection!", Toast.LENGTH_SHORT).show()
        } else {
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

    fun makeToast(text: String) {
        Toast.makeText(context, text, Toast.LENGTH_SHORT).show()
    }

    fun setInformation(userEmail: String) {
        currentEmail = userEmail
        currentName = setUserName()
        currentId =  setUserId()
        currentDept =  setDepartment()
        currentSession = setSession()

    }

    private fun setSession(): String{
        return if (currentId[2].toString() == "1" ) "FALL" else "SPRING" + " " + currentId[0].toString() + currentId[1].toString()
    }

    private fun setDepartment(): String {
        var department = ""
        if (currentEmail.contains("cse"))
            department = getString(R.string.cse)
        else if (currentEmail.contains("eee"))
            department = getString(R.string.eee)
        else if (currentEmail.contains("ce"))
            department = getString(R.string.ce)
        else if (currentEmail.contains("mpe"))
            department = getString(R.string.mpe)
        else if (currentEmail.contains("te"))
            department = getString(R.string.te)
        return department
    }
    private fun setUserName(): String {
        return (currentEmail.split(".")[0]).replaceFirstChar {
            if (it.isLowerCase()) it.titlecase(
                Locale.ROOT
            ) else it.toString()
        }
    }

    private fun setUserId(): String {
        return (currentEmail.split(".")[2]).split("@")[0]
    }

    fun getDepartment(): String {
        return currentDept
    }
    fun getUserName(): String {
        return currentName
    }
    fun getUserId(): String {
        return currentId
    }
    fun getUserEmail(): String {
        return currentEmail
    }
    fun getSession() : String {
        return currentSession
    }

    fun validNumber(sellersContactNoWrite : String) : Boolean{

        return sellersContactNoWrite.length == 11 && sellersContactNoWrite[0] == '0' && sellersContactNoWrite[1] == '1'
    }

    fun getDatabasePath(foundString: String): String {

        var yearSemester= foundString.replace("[^\\d.]".toRegex(), "")

        return   "year" + yearSemester[0] + "semester" + yearSemester[1]
    }



}