package com.example.homepage.adminPanel.adminRequest.Model

data class Admin(
    var department: String? = null,
    var year: String? = null,
    var semester: String? = null,
    var email: String? = null
) {
    fun toMap(): Map<String, Any?> {

        return mapOf(
            "department" to department,
            "year" to year,
            "semester" to semester,
            "email" to email,
            "path" to "year" + year + "semester" + semester
        )
    }

}