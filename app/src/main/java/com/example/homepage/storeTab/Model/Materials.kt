package com.example.homepage.storeTab.Model

data class Materials(
    var userId: String? = "",
    var productName: String? = "",
    var productAuthor: String? = "",
    var productCategory: String? = "",
    var productPrice : String? = "",
    var sellersContactNo : String? = "",
    var sellersDetails : String? = ""
){
    fun toMap(): Map<String, Any?> {
        return mapOf(
            "userId" to userId,
            "productName" to productName,
            "productAuthor" to productAuthor,
            "productCategory" to productCategory,
            "productPrice" to productPrice,
            "sellersContactNo " to sellersContactNo,
            "sellersDetails" to sellersDetails


        )
    }
}

