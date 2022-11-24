package com.example.homepage.groupTab.Group.Model

data class GroupData(
    var userEmail: String? = "",
    var universityId: String? = "",
    var groupName: String? = "",
    var groupDetails: String? = "",
    var path: String? = ""
) {
    fun toMap(): Map<String, Any?> {
        return mapOf(
            "userId" to userEmail,
            "universityId" to universityId,
            "groupName" to groupName,
            "groupDetails" to groupDetails,
            "path" to path
        )
    }

}