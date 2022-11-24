package com.example.homepage.groupTab.groupNotices.Model

data class GroupNoticeData(
    val userId: String,
    val taskName: String,
    val taskDescription: String,
    val taskDate: String,
    val key: String?,
    val groupId: String
) {
    fun toMap(): Map<String, Any?> {
        return mapOf(
            "userId" to userId,
            "taskName" to taskName,
            "askDescription" to taskDescription,
            "taskDate" to taskDate,
            "key" to key,
            "groupId" to groupId
        )
    }
}

