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
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase
import java.util.regex.Matcher
import java.util.regex.Pattern


open class ReplaceFragment : Fragment() {

    private var currentEmail = ""
    private var currentName = ""
    private var currentId = ""
    private var currentDept = ""
    private var currentSession = ""

    private lateinit var database: DatabaseReference
    private lateinit var auth: FirebaseAuth
    private val helper = Helper()

    fun replaceFragment(fragment: Fragment, xml_file_name: Int) {
        val fragmentManager = requireActivity().supportFragmentManager
        fragmentManager.beginTransaction().replace(xml_file_name, fragment).addToBackStack("tag")
            .commit()
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
        helper.setInformation(userEmail)
        currentEmail = helper.getUserEmail()
        currentName = helper.setUserName()
        currentId = helper.setUserId()
        currentDept = helper.setDepartment()
        currentSession = helper.setSession()

    }


     fun getShortDepartment(): String{
        return helper.getShortDepartment()
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

    fun getSession(): String {
        return currentSession
    }

    fun validNumber(sellersContactNoWrite: String): Boolean {

        return sellersContactNoWrite.length == 11 && sellersContactNoWrite[0] == '0' && sellersContactNoWrite[1] == '1'
    }

    fun getDatabasePath(foundString: String): String {

        val yearSemester = foundString.replace("[^\\d.]".toRegex(), "")

        return "year" + yearSemester[0] + "semester" + yearSemester[1]
    }


    fun getCurrentUserId(): String {

        auth = Firebase.auth
        database = Firebase.database.reference
        return auth.currentUser!!.uid

    }

    fun validWebsiteLink(url: String): Boolean {

        val regex = ("((http|https)://)(www.)?"
                + "[a-zA-Z0-9@:%._\\+~#?&//=]"
                + "{2,256}\\.[a-z]"
                + "{2,6}\\b([-a-zA-Z0-9@:%"
                + "._\\+~#?&//=]*)")

        val p: Pattern = Pattern.compile(regex)
        val m: Matcher = p.matcher(url)
        return m.matches()

    }

    private val EMAIL_ADDRESS_PATTERN: Pattern = Pattern.compile(
        "[a-zA-Z0-9\\+\\.\\_\\%\\-\\+]{1,256}" +
                "\\@" +
                "[a-zA-Z0-9][a-zA-Z0-9\\-]{0,64}" +
                "(" +
                "\\." +
                "[a-zA-Z0-9][a-zA-Z0-9\\-]{0,25}" +
                ")+"
    )


    fun validEmail(email: String): Boolean {
        return EMAIL_ADDRESS_PATTERN.matcher(email).matches()
    }

    fun getRollOmittedUserId(): String {
        return getUserId().dropLast(3).drop(2)
    }

}