package com.example.homepage.utils.models


data class TeacherData(
    var name: String? = null,
    var img: String? = null,
    var phone: String? = null,
    var designation: String? = null,
    var email: String? = null
) {
    fun toMap(): Map<String, Any?> {
        return mapOf(
            "name" to name,
            "designation" to phone,
            "phone" to designation,
            "email" to email,
            "img" to img
        )
    }
}