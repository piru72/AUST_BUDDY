package com.example.homepage.database

import android.util.Log
import com.example.homepage.utils.models.Admin
import com.example.homepage.utils.models.CourseData
import com.example.homepage.utils.models.GroupNoticeData
import com.example.homepage.utils.models.TeacherData
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

    fun writeNewTask(
        userId: String,
        taskName: String,
        taskDescription: String,
        taskDate: String,
        oldKey: String,
        groupId: String
    ) {

        val newKey = firebaseDatabase.child("posts").push().key
        if (newKey == null) {
            Log.w("TodoActivity", "Couldn't get push key for posts")
            return
        }
        val newGroupNotice: Any
        val pushingPath: String

        if (oldKey == "make-key") {
            newGroupNotice =
                GroupNoticeData(userId, taskName, taskDescription, taskDate, newKey, groupId)
            pushingPath = "/group-notice/$groupId/$newKey"

        } else {
            newGroupNotice =
                GroupNoticeData(userId, taskName, taskDescription, taskDate, oldKey, groupId)
            pushingPath = "/group-notice/$groupId/$oldKey"
        }
        val noticeValues = newGroupNotice.toMap()

        val childUpdates = hashMapOf<String, Any>(
            pushingPath to noticeValues
        )

        firebaseDatabase.updateChildren(childUpdates)

    }

    fun removeAdminRequest(admin: Admin) {
        val currentAdminRequest = admin.email?.replace(".", "-")

        removeChild(
            "admin-admin-request",
            currentAdminRequest.toString()
        )

    }

    fun approveAdminRequest(admin: Admin) {
        val currentAdminRequest = admin.email?.replace(".", "-")
        val fromPath = "admin-admin-request/$currentAdminRequest"
        val toPath = "admin-list/$currentAdminRequest"

        moveChild(fromPath, toPath)
        removeChild(
            "admin-admin-request",
            currentAdminRequest.toString()
        )

    }

    // Given parentNode and childNode this function will remove the child from the parent
    fun removeChild(parentNode: String, childNode: String) {
        val parentReference = FirebaseDatabase.getInstance().getReference(parentNode)
        parentReference.child(childNode).removeValue()
    }

    // Given Source and destination path this function will copy a node from one node to another
    fun moveChild(fromPath: String, toPath: String) {
        val fromReference = FirebaseDatabase.getInstance().getReference(fromPath)
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

    fun declineCourseRequest(courseData: CourseData) {
        val parentNode = "admin-course-request-list"
        val childNode = courseData.courseCode.toString()
        removeChild(parentNode, childNode)
    }

    fun approveCourseRequest(courseData: CourseData) {
        val parentNode = "admin-course-request-list"
        val childNode = courseData.courseCode.toString()

        val fromPath = "$parentNode/$childNode"
        val toPath = "course-list/" + courseData.coursePath.toString()

        moveChild(fromPath, toPath)
        removeChild(parentNode, childNode)
    }
}