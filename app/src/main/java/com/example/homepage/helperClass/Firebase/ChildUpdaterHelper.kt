package com.example.homepage.helperClass.Firebase

import com.example.homepage.courseTab.Model.CourseData
import com.example.homepage.teachersPage.TeacherModel.TeacherData

class ChildUpdaterHelper {
    private var firebaseDatabase = FirebaseUtils.getDatabaseReference()

    fun writeNewTeacher(
        teacher: TeacherData,
        mainPushingPath: String,
        currentUserId: String
    ) {
        val newPush = teacher.email?.replace(".", "-")

        val teachersInformation = teacher.toMap()
        val childUpdate = hashMapOf<String, Any>(
            "/$mainPushingPath/$newPush" to teachersInformation
        )

        if (mainPushingPath == "admin-teacher-request-list") {
            val pushKey = teacher.email
            val newPush1 = pushKey?.replace(".", "-")

            val childUpdate1 = hashMapOf<String, Any>(
                "user-favouriteTeachers/$currentUserId/$newPush1" to teachersInformation
            )
            firebaseDatabase.updateChildren(childUpdate1)

        }

        firebaseDatabase.updateChildren(childUpdate)

    }

    fun writeNewCourse(
        course: CourseData,
        mainPushingPath: String
    ) {
        val courseDetails = course.toMap()
        if (mainPushingPath == "admin-course-request-list") {
            val childUpdateRequest = hashMapOf<String, Any>(
                "/$mainPushingPath/${course.courseCode}" to courseDetails
            )
            firebaseDatabase.updateChildren(childUpdateRequest)
        } else {
            val childUpdateAdmin = hashMapOf<String, Any>(
                "/$mainPushingPath/${course.coursePath}" to courseDetails
            )

            firebaseDatabase.updateChildren(childUpdateAdmin)
        }


    }
}