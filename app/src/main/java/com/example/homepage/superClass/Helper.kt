package com.example.homepage.superClass

import android.content.Context
import com.example.homepage.superClass.spinner.SpinnerAdapter
import com.example.homepage.superClass.spinner.SpinnerItem
import java.util.*


class Helper {

    private var currentEmail = ""
    private var currentName = ""
    private var currentId = ""
    private var currentDept = ""
    private var currentSession = ""

   


    fun setInformation(userEmail: String) {
        var finalUserEmail = userEmail
        if (finalUserEmail.length == 18) {
            finalUserEmail = if (finalUserEmail[5] == '4')
                "Name.cse.$finalUserEmail"
            else if (finalUserEmail[5] == '3')
                "Name.ce.$finalUserEmail"
            else if (finalUserEmail[5] == '5')
                "Name.eee.$finalUserEmail"
            else
                "Name.Department.$finalUserEmail"
        }
        currentEmail = finalUserEmail
        currentName = setUserName()
        currentId = setUserId()
        currentDept = setDepartment()
        currentSession = setSession()

    }

     fun setSession(): String {
        return if (currentId[5].toString() == "1") "SPRING" + " " + currentId.dropLast(7) else "FALL" + " " + currentId.dropLast(7)
    }

    fun setDepartment(): String {
        return if (currentEmail.contains("cse"))
            "Computer Science and Engineering"
        else if (currentEmail.contains("eee"))
            "Electrical and Electronic Engineering"
        else if (currentEmail.contains("ce"))
            "Civil Engineering"
        else if (currentEmail.contains("mpe"))
            "Mechanical and Production Engineering"
        else if (currentEmail.contains("te"))
            "Textile Engineering"
        else
            "Department"
    }

    fun getShortDepartment(): String {
        return if (currentEmail.contains("cse"))
            "cse"
        else if (currentEmail.contains("eee"))
            "eee"
        else if (currentEmail.contains("ce"))
            "ce"
        else if (currentEmail.contains("me"))
            "me"
        else if (currentEmail.contains("te"))
            "te"
        else
            "Department"
    }


     fun setUserName(): String {
        return (currentEmail.split(".")[0]).replaceFirstChar {
            if (it.isLowerCase()) it.titlecase(
                Locale.ROOT
            ) else it.toString()
        }
    }

     fun setUserId(): String {
        val studentId=  (currentEmail.split(".")[2]).split("@")[0]
        return if (studentId.length == 9)
            "20$studentId"
        else
            studentId
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

    fun createSpinnerAdapter(
        context: Context,
        categoryList: Array<SpinnerItem>
    ): SpinnerAdapter {

        val adapter = SpinnerAdapter (context, categoryList)
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        return adapter

    }




}