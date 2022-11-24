package com.example.homepage.groupTab.groupNotices.Model

data class GroupNoticeData(
    val userId: String,
    val taskName: String,
    val taskDescription: String,
    val taskDate: String,
    val path: String?,
    val groupId: String
) {
    fun toMap(): Map<String, Any?> {
        return mapOf(
            "userId" to userId,
            "taskName" to taskName,
            "taskDescription" to taskDescription,
            "taskDate" to taskDate,
            "path" to path,
            "groupId" to groupId
        )
    }
}

