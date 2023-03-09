package com.example.homepage.helperClass.Firebase

import com.example.homepage.teachersPage.TeacherModel.TeacherData

class ChildUpdaterHelper {

    fun writeNewTeacher(
        teacher: TeacherData,
        mainPushingPath: String,
        currentUserId: String
    ) {
        val newPush = teacher.email?.replace(".", "-")
        val database = FirebaseUtils.getDatabaseReference()
        val teachersInformation = teacher.toMap()
        val childUpdate = hashMapOf<String, Any>(
            "/$mainPushingPath/$newPush" to teachersInformation
        )

        if (mainPushingPath == "admin-teacher-request-list") {
            val pushKey = teacher.email?.toString()
            val newPush1 = pushKey?.replace(".", "-")

            val childUpdate1 = hashMapOf<String, Any>(
                "user-favouriteTeachers/$currentUserId/$newPush1" to teachersInformation
            )
            database.updateChildren(childUpdate1)

        }

        database.updateChildren(childUpdate)

    }
}