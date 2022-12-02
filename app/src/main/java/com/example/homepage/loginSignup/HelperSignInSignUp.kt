package com.example.homepage.loginSignup

import android.content.Context
import android.text.TextUtils
import android.util.Patterns
import android.widget.Toast

class HelperSignInSignUp(private val applicationContext : Context) {

   fun  makeToast(text: String) {
        Toast.makeText(applicationContext, text, Toast.LENGTH_SHORT).show()
    }

    fun validateEmailPasswordFormat(
        email: String,
        pass: String,
        passRetype: String
    ): String {

        if (!Patterns.EMAIL_ADDRESS.matcher(email).matches())
            return "Invalid email format"
        else if (email.length >= 35)
            return "Too long characters"
        else if (!email.contains("@aust.edu"))
            return "Provide your @aust.edu email"
        else if (TextUtils.isEmpty(pass))
            return "Enter a password"
        else if (pass.length <= 6)
            return "Password is too short"
        else if (pass != passRetype)
            return "Passwords didn't match retype passwords"
        else if (pass.contains("@") || pass.contains("#") || pass.contains("%") || pass.contains("$") || pass.contains(
                "*"
            )
        )
            return "Valid Data"
        else
            return "Give a special character such as @,$,#.."

    }
}