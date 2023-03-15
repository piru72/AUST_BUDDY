package com.example.homepage.Model

data class Admin(
    val department: String? = null,
    val year: String? = null,
    val semester: String? = null,
    val email: String? = null
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