package com.example.homepage.utils.models

data class BugReportsData(
    var userId: String? = "",
    var reportersDetails: String? = "",
    var reportDetails: String? = "",
    var key: String? = ""
) {
    fun toMap(): Map<String, Any?> {
        return mapOf(
            "userId" to userId,
            "reportersDetails" to reportersDetails,
            "reportDetails" to reportDetails,
            "key" to key
        )
    }
}