package com.example.homepage.courseTab.Model

class CourseData(
    var courseCode: String? = null,
    var courseName: String? = null,
    var driveLink: String? = null
){
    fun toMap(): Map<String, Any?> {

        return mapOf(
            "courseCode" to courseCode,
            "courseName" to courseName,
            "courseDriveLink" to driveLink
        )
    }
}