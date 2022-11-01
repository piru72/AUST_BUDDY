package com.example.homepage.store.Model

data class Materials(
    var userId: String? = "",
    var bookName: String? = "",
    var bookYear: String? = "",
    var bookSemester: String? = ""
){
    fun toMap(): Map<String, Any?> {
        return mapOf(
            "userId" to userId,
            "bookName" to  bookName,
            "bookYear" to bookYear,
            "bookSemester" to bookSemester
        )
    }
}

