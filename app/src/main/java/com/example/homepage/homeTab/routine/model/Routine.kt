package com.example.homepage.homeTab.routine.model

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
