package com.example.homepage.utils.models

data class FavouriteWebpageData(
    var uid: String? = "",
    var websiteName: String? = "",
    var websiteLink: String? = "",
    val websiteID: String? = ""

) {
    fun toMap(): Map<String, Any?> {
        return mapOf(
            "uid" to uid,
            "websiteName" to websiteName,
            "websiteLink" to websiteLink,
            "websiteID" to websiteID
        )
    }
}


