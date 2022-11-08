package com.example.homepage.favouriteWebPage.Model

data class FavouriteWebpageData (
    var uid: String? = "",
    var websiteName: String? = "",
    var websiteLink: String? = "",

    ) {
        fun toMap(): Map<String, Any?> {
            return mapOf(
                "uid" to uid,
                "websiteName" to websiteName,
                "websiteLink" to websiteLink
            )
        }
}


