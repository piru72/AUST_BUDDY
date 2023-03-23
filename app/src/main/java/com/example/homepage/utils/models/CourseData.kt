package com.example.homepage.utils.models

class CourseData(
    var courseCode: String? = null,
    var courseName: String? = null,
    var driveLink: String? = null,
    var coursePath: String? = null
) {
    fun toMap(): Map<String, Any?> {

        return mapOf(
            "courseCode" to courseCode,
            "courseName" to courseName,
            "driveLink" to driveLink,
            "coursePath" to coursePath
        )
    }
}