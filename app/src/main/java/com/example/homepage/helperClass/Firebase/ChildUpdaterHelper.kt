package com.example.homepage.helperClass.Firebase

import com.example.homepage.courseTab.Model.CourseData
import com.example.homepage.teachersPage.TeacherModel.TeacherData
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener

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
    // Given parentNode and childNode this function will remove the child from the parent
    fun removeChild(parentNode: String, childNode: String) {
        val parentReference = FirebaseDatabase.getInstance().getReference(parentNode)
        parentReference.child(childNode).removeValue()
    }

    // Given Source and destination path this function will copy a node from one node to another
    fun moveChild(fromPath: String, toPath: String) {
        val fromReference =  FirebaseDatabase.getInstance().getReference(fromPath)
        val toReference = FirebaseDatabase.getInstance().getReference(toPath)

        fromReference.addListenerForSingleValueEvent(object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                toReference.setValue(
                    dataSnapshot.value
                )
            }

            override fun onCancelled(databaseError: DatabaseError) {
                // not yet implemented
            }
        })
    }
}