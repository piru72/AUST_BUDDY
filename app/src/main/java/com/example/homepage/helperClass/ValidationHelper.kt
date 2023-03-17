package com.example.homepage.helperClass

import com.example.homepage.Model.CourseData
import com.example.homepage.Model.TeacherData
import java.util.regex.Matcher
import java.util.regex.Pattern

class ValidationHelper {

    fun validateCourseForm(
        department: String,
        year: String,
        semester: String,
        course: CourseData,
    ): String {
        val selectYour = "Select your "
        return when {
            department == "Department" -> ("$selectYour $department")
            year == "Year" -> ("$selectYour $year")
            semester == "Semester" -> ("$selectYour $semester")
            course.courseCode?.isBlank() == true -> ("$selectYour Course Code")
            course.courseName?.isBlank() == true -> ("$selectYour  Course Name")
            course.driveLink?.let { validWebsiteLink(it) } == false -> ("Provide an valid drive link. ")
            else -> {
                "Valid Data"
            }

        }
    }

    fun validateTeacherForm(
        teacher: TeacherData
    ): String {

        return when {


            teacher.name?.contains(".") == true -> ("Provide valid teachers name without . and only alphabets")
            teacher.name == "" -> ("Provide teachers name")
            teacher.designation == "" -> ("Provide teachers designation")
            teacher.phone == "" -> ("Provide teachers contact no or type Not Available")
            teacher.email == "" -> (" Provide teachers email or type Not Available")
            teacher.img == "" -> ("Provide teachers image link ")
            !teacher.phone?.let { validNumber(it) }!! && teacher.phone != "Not Available" -> "Provide a valid contact no"
            !teacher.email?.let { validEmail(it) }!! -> ("Provide a valid email")
            !teacher.img?.let { validWebsiteLink(it) }!! -> ("Provide valid image link")

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