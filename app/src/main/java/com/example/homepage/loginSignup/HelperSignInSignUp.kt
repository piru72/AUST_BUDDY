package com.example.homepage.loginSignup

import android.util.Patterns

class HelperSignInSignUp {


    fun validateEmailPasswordFormat(
        email: String,
        pass: String,
        passRetype: String
    ): String {

        return if (validEmailAddress(email))
            "Invalid email format"
        else
            emailPasswordValidation(email, pass, passRetype)


    }

    fun emailPasswordValidation(email: String, pass: String, passRetype: String): String {

        if (email.length >= 50)
            return "Too long characters"
        else if (!email.contains("@aust.edu"))
            return "Provide your @aust.edu email"
        else if (pass == "")
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


    private fun validEmailAddress(email: String): Boolean {
        return !Patterns.EMAIL_ADDRESS.matcher(email).matches()
    }

}