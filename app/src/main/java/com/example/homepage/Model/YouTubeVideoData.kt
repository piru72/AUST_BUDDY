package com.example.homepage.Model

data class YouTubeVideoData(
    var videoId: String? = "",
    var title: String? = "",
    var thumbnail: String? = "",
) {

    fun toMap(): Map<String, Any?> {

        return mapOf(
            "videoId" to videoId,
            "title" to title,
            "thumbnail" to thumbnail
        )
    }
}

