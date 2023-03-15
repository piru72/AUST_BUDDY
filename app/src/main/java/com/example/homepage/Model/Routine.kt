package com.example.homepage.Model

data class Routine(
    var image: String ? ="",
    var path: String ?= ""
)
{


    fun toMap(): Map<String, Any?> {

        return mapOf(
            "image" to image,
            "path" to path
        )
    }
}
