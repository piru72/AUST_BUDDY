package com.example.homepage.adminPanel

data class Courses(
    var courseCode: String? = "", var courseName: String? = "", var courseDriveLink: String? = ""
) {
    fun toMap(): Map<String, Any?> {

        return mapOf(
            "courseCode" to courseCode,
            "courseName" to courseName,
            "courseDriveLink" to courseDriveLink
        )
    }
}
