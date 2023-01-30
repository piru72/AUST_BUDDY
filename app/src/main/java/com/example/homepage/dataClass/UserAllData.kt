package com.example.homepage.dataClass

data class UserAllData(
    var userName: String,
    var userEmail: String,
    var studentId: String,
    var session: String,
    var department: String,
    var selectedSemester: String
) {


    fun toMap(): Map<String, Any?> {

        return mapOf(
            "userName" to userName,
            "userEmail" to userEmail,
            "userStudentId" to studentId,
            "userSession" to session,
            "UserDepartment" to department ,
            "UserSemester" to selectedSemester
        )
    }
}