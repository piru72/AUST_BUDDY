package com.example.homepage.scheduleTab.scheduleModel

data class ScheduleData(

    var uid: String? = "",
    var taskname: String? = "",
    var taskdescription: String? = "",
    var taskdate: String? = "",
    var path : String ? ="",
    var groupId : String ? = ""

) {
    fun toMap(): Map<String, Any?> {
        return mapOf(
            "uid" to uid,
            "taskname" to taskname,
            "taskdescription" to taskdescription,
            "taskdate" to taskdate,
            "path" to path,
            "groupId" to groupId
        )
    }

}