package com.example.homepage.groupTab.Group.Model

data class GroupData(
    var userId: String? = "",
    var universityId: String? = "",
    var groupName: String? = "",
    var groupDetails: String? = "",
    var path: String? = "",
    val groupId: String?= ""
) {
    fun toMap(): Map<String, Any?> {
        return mapOf(
            "userId" to userId,
            "universityId" to universityId,
            "groupName" to groupName,
            "groupDetails" to groupDetails,
            "path" to path,
            "groupId" to groupId
        )
    }

}