package com.example.homepage.utils.models

data class UserAllData(
    var userName: String ? = "",
    var userEmail: String? = "",
    var userStudentId: String? = "",
    var userSession: String? = "",
    var userDepartment: String? = "",
    var userSemester: String? = "",
    var userSection: String? = ""

) {


     fun toMap(): Map<String, Any?> {

        return mapOf(
            "userName" to userName,
            "userEmail" to userEmail,
            "userStudentId" to userStudentId,
            "userSession" to userSession,
            "userDepartment" to userDepartment ,
            "userSemester" to userSemester,
            "userSection" to userSection
        )
    }
}