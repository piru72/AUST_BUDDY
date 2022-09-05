package com.example.homepage.Quiz

data class Tasks(

    var uid: String? ="",
    var taskname: String? ="",
    var taskdescription: String? = ""
){
    fun toMap(): Map<String, Any?>{
        return mapOf(
            "uid" to uid,
            "taskname" to taskname,
            "taskdescription" to taskdescription
        )
    }

}