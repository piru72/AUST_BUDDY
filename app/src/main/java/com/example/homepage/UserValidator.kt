package com.example.homepage

object UserValidator {

    fun validateInput (email : String , password :String ) : Boolean
    {
        return email.length >5 && password.length >5
    }
}