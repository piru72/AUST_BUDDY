package com.example.homepage.superClass

import java.util.regex.Matcher
import java.util.regex.Pattern

class ValidationHelper {

    fun validateCourseForm(
        department: String,
        year: String,
        semester: String,
        courseCode: String,
        courseName: String,
    ): String {
        val selectYour = "Select your "
        return when {
            department == "Department" -> ("$selectYour $department")
            year == "Year" -> ("$selectYour $year")
            semester == "Semester" -> ("$selectYour $semester")
            courseCode.isBlank() -> ("$selectYour Course Code")
            courseName.isBlank() -> ("$selectYour  Course Name")
            else -> {
                "Valid Data"
            }

        }
    }

    fun validateTeacherForm(
        teachersName: String,
        teachersDesignation: String,
        teachersContactNo: String,
        teacherEmail: String,
        teachersImageLink: String
    ): String {

        return when {


            teachersName.contains(".") -> ("Provide valid teachers name without . and only alphabets")
            teachersName == "" -> ("Provide teachers name")
            teachersDesignation == "" -> ("Provide teachers designation")
            teachersContactNo == "" -> ("Provide teachers contact no or type Not Available")
            teacherEmail == "" -> (" Provide teachers email or type Not Available")
            teachersImageLink == "" -> ("Provide teachers image link ")
            !validNumber(teachersContactNo) && teachersContactNo != "Not Available" -> "Provide a valid contact no"
            !validEmail(teacherEmail) -> ("Provide a valid email")
            !validWebsiteLink(teachersImageLink) -> ("Provide valid image link")

            else -> {
                "Valid Data"
            }
        }
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

    private fun validEmail(email: String): Boolean {
        return EMAIL_ADDRESS_PATTERN.matcher(email).matches()
    }
    private fun validNumber(sellersContactNoWrite: String): Boolean {

        return sellersContactNoWrite.length == 11 && sellersContactNoWrite[0] == '0' && sellersContactNoWrite[1] == '1'
    }

}