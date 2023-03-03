package com.example.homepage.loginSignup

fun getTestData() = listOf(
    EmailPasswordTestData(
        email = "parvez.cse.200104129@aust.edu",
        password = "123456@",
        retypePassword = "123456@",
        expected = "Valid Data"
    ),
    EmailPasswordTestData(
        email = "arrayOf(\"parvez.cse.200104129@aust.edu\", \"123456@\", \"123456@\", \"Valid Data\"),",
        password = "123456@",
        retypePassword = "123456@",
        expected = "Too long characters"
    ),
    EmailPasswordTestData(
        email = "parvezdirom2000@gmail.com",
        password = "123456@",
        retypePassword = "123456@",
        expected = "Provide your @aust.edu email"
    ),
    EmailPasswordTestData(
        email = "parvez.cse.200104129@aust.edu",
        password = "",
        retypePassword = "123456@",
        expected = "Enter a password"
    ),
    EmailPasswordTestData(
        email = "parvez.cse.200104129@aust.edu",
        password = "123456",
        retypePassword = "123456@",
        expected = "Password is too short"
    ),
    EmailPasswordTestData(
        email = "parvez.cse.200104129@aust.edu",
        password = "123456@",
        retypePassword = "123456@@@",
        expected = "Passwords didn't match retype passwords"
    ),
    EmailPasswordTestData(
        email = "parvez.cse.200104129@aust.edu",
        password = "1234567",
        retypePassword = "1234567",
        expected = "Give a special character such as @,$,#.."
    )
)
data class EmailPasswordTestData (
    val email: String,
    val password: String,
    val retypePassword: String,
    val expected: String
    )

