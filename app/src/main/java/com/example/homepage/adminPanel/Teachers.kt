package com.example.homepage.adminPanel

data class Teachers(var teachersName :String?="",var teachersDesignation :String?="",var teachersContactNo :String? ="",var teachersEmail:String?= "") {

    fun toMap():Map<String,Any?>{
        return mapOf(
            "name" to teachersName,
             "designation" to teachersDesignation,
            "phone" to teachersContactNo,
            "email" to teachersEmail
        )
    }
}