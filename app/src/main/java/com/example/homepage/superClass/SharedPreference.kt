package com.example.homepage.superClass

import android.content.SharedPreferences

class SharedPreference {

    lateinit var sharedPreferences: SharedPreferences
    var PREFS_KEY = "prefs"
    var EMAIL_KEY = "email"
    var PWD_KEY = "pwd"

    // variable for email and password.
    var emailPreferences = ""
    var pwdPreferences = ""


    fun setupPreference(email : String , password:String){
        //sharedPreferences = getSharedPreferences(PREFS_KEY, Context.MODE_PRIVATE)
        emailPreferences = sharedPreferences.getString(EMAIL_KEY, "").toString()
        pwdPreferences = sharedPreferences.getString(PWD_KEY, "").toString()

        val editor: SharedPreferences.Editor = sharedPreferences.edit()
        editor.putString(EMAIL_KEY, email)
        editor.putString(PWD_KEY, password)
        editor.apply()
    }



}