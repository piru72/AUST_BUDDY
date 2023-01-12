package com.example.homepage.plaza.Model

data class Materials(
    var userId: String? = "",
    var productName: String? = "",
    var productAuthor: String? = "",
    var productCategory: String? = "",
    var productPrice: String? = "",
    var sellersContactNo: String? = "",
    var sellersDetails: String? = "",
    val productId: String? = "",
    val productDetails: String? = "",
) {
    fun toMap(): Map<String, Any?> {
        return mapOf(
            "userId" to userId,
            "productName" to productName,
            "productAuthor" to productAuthor,
            "productCategory" to productCategory,
            "productPrice" to productPrice,
            "sellersContactNo " to sellersContactNo,
            "sellersDetails" to sellersDetails,
            "productId" to productId,
            "productDetails" to productDetails


        )
    }
}

