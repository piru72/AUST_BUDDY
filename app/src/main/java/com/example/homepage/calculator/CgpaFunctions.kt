package com.example.homepage.calculator

import android.annotation.SuppressLint
import android.view.View
import android.widget.Button
import android.widget.LinearLayout

class CgpaFunctions {


    // Functions needed for
    fun hideLayout(layout: LinearLayout) {
        layout.visibility = View.GONE
    }

    fun hideLayout(layout1: LinearLayout, layout2: LinearLayout) {
        layout1.visibility = View.GONE
        layout2.visibility = View.GONE
    }

    fun hideLayout(layout1: LinearLayout, layout2: LinearLayout, layout3: LinearLayout) {
        layout1.visibility = View.GONE
        layout2.visibility = View.GONE
        layout3.visibility = View.GONE
    }

    fun showLayout(layout: LinearLayout) {
        layout.visibility = View.VISIBLE
    }

    fun showLayout(layout1: LinearLayout, layout2: LinearLayout) {
        layout1.visibility = View.VISIBLE
        layout2.visibility = View.VISIBLE
    }

    fun showLayout(layout1: LinearLayout, layout2: LinearLayout, layout3: LinearLayout) {
        layout1.visibility = View.VISIBLE
        layout2.visibility = View.VISIBLE
        layout3.visibility = View.VISIBLE
    }

    @SuppressLint("SetTextI18n")
    fun set3Credit(
        c1Credit: Button,
        c2Credit: Button,
        c3Credit: Button,
        c4Credit: Button,
        c5Credit: Button
    ) {

        c1Credit.text = "3 Credit"
        c2Credit.text = "3 Credit"
        c3Credit.text = "3 Credit"
        c4Credit.text = "3 Credit"
        c5Credit.text = "3 Credit"
    }

    fun set15Credit(c6Credit: Button, c7Credit: Button, c8Credit: Button) {

        c6Credit.text = "1.5 Credit"
        c7Credit.text = "1.5 Credit"
        c8Credit.text = "1.5 Credit"
    }

    fun set15Credit(c6Credit: Button, c7Credit: Button) {

        c6Credit.text = "1.5 Credit"
        c7Credit.text = "1.5 Credit"
    }


    fun set15Credit(c6Credit: Button) {

        c6Credit.text = "1.5 Credit"
    }

    fun set75Credit(c9Credit: Button) {

        c9Credit.text = ".75 Credit"
    }

    fun set75Credit(c8Credit: Button, c9Credit: Button) {

        c8Credit.text = ".75 Credit"
        c9Credit.text = ".75 Credit"
    }

    fun set75Credit(c7Credit: Button, c8Credit: Button, c9Credit: Button) {
        c7Credit.text = ".75 Credit"
        c8Credit.text = ".75 Credit"
        c9Credit.text = ".75 Credit"
    }

    fun getSemesterNumber(semester: String): Any {

        var semesterNumber = 0
        when (semester) {
            "1st Year 1st Semester" -> semesterNumber = 1
            "1st Year 2nd Semester" -> semesterNumber = 2
            "2nd Year 1st Semester" -> semesterNumber = 3
            "2nd Year 2nd Semester" -> semesterNumber = 4
            "3rd Year 1st Semester" -> semesterNumber = 5
            "3rd Year 2nd Semester" -> semesterNumber = 6
            "4th Year 1st Semester" -> semesterNumber = 7
            "4th Year 2nd Semester" -> semesterNumber = 8
        }



        return semesterNumber
    }


    private fun findPoint(toString: String): Float {

        var point = 0f

        if (toString.contains("A+"))
            point = 4f
        else if (toString.contains("A-"))
            point = 3.5f
        else if (toString.contains("A"))
            point = 3.75f
        else if (toString.contains("B+"))
            point = 3.25f
        else if (toString.contains("B-"))
            point = 2.75f
        else if (toString.contains("B"))
            point = 3.00f
        else if (toString.contains("C+"))
            point = 2.5f
        else if (toString.contains("C"))
            point = 2.25f
        else if (toString.contains("D"))
            point = 2.00f
        else if (toString.contains("F"))
            point = 0.00f


        return point

    }

    fun getTheoryGpa(
        toString: String,
        toString1: String,
        toString2: String,
        toString3: String,
        toString4: String
    ): Float {

        var totalPoint: Float =
            findPoint(toString) + findPoint(toString1) + findPoint(toString2) + findPoint(toString3) + findPoint(
                toString4
            )
        totalPoint *= 3

        return totalPoint
    }

    fun getTheoryGpa(
        toString: String,
        toString1: String,
        toString2: String,
        toString3: String,
        toString4: String, toString5: String
    ): Float {

        var totalPoint: Float =
            findPoint(toString) + findPoint(toString1) + findPoint(toString2) + findPoint(toString3) + findPoint(
                toString4
            ) + findPoint(toString5)
        totalPoint *= 3

        return totalPoint
    }

    fun getLabGpa(toString: String, toString1: String, toString2: String): Float {

        var totalPoint: Float = findPoint(toString) + findPoint(toString1) + findPoint(toString2)
        totalPoint *= 1.5f

        return totalPoint
    }

    fun getLabGpa(toString: String, toString1: String): Float {

        var totalPoint: Float = findPoint(toString) + findPoint(toString1)
        totalPoint *= 1.5f

        return totalPoint
    }

    fun getLabGpa(toString: String): Float {

        var totalPoint: Float = findPoint(toString)
        totalPoint *= 1.5f

        return totalPoint
    }

    fun getSesGpa(toString: String): Float {
        var totalPoint: Float = findPoint(toString)
        totalPoint *= .75f
        return totalPoint
    }

    fun getSesGpa(toString: String, toString1: String): Float {
        var totalPoint: Float = findPoint(toString) + findPoint(toString1)
        totalPoint *= .75f
        return totalPoint
    }

    fun getSesGpa(toString: String, toString1: String, toString2: String): Float {
        var totalPoint: Float = findPoint(toString) + findPoint(toString1) + findPoint(toString2)
        totalPoint *= .75f
        return totalPoint
    }

    fun getSesGpa(
        toString: String,
        toString1: String,
        toString2: String,
        toString3: String,
        toString4: String
    ): Float {

        var totalPoint: Float =
            findPoint(toString) + findPoint(toString1) + findPoint(toString2) + findPoint(toString3) + findPoint(
                toString4
            )
        totalPoint *= .75f

        return totalPoint
    }

    fun getSesGpa(
        toString: String,
        toString1: String,
        toString2: String,
        toString3: String,

        ): Float {

        var totalPoint: Float =
            findPoint(toString) + findPoint(toString1) + findPoint(toString2) + findPoint(toString3)
        totalPoint *= .75f

        return totalPoint
    }

    fun checkFail(
        toString: String,
        toString1: String,
        toString2: String,
        toString3: String,
        toString4: String,
        toString5: String,
        toString6: String,
        toString7: String,
        toString8: String,
        toString9: String
    ): Boolean {

        var isFailed = false


        if (toString.contains("F") || toString1.contains("F") || toString2.contains("F") || toString3.contains(
                "F"
            ) || toString4.contains("F") || toString5.contains("F") || toString6.contains("F") || toString7.contains(
                "F"
            ) || toString8.contains("F") || toString9.contains("F")
        )
            isFailed = true






        return isFailed

    }


}