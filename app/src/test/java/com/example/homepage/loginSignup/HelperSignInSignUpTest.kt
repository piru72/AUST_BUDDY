package com.example.homepage.loginSignup


import junit.framework.Assert.assertEquals
import org.junit.Before
import org.junit.Test

class HelperSignInSignUpTest {

    private lateinit var testingClass: HelperSignInSignUp

    @Before
    fun setUp() {
        testingClass = HelperSignInSignUp()
    }

    @Test
    fun `given valid email and password, when emailPasswordValidation is called, then it should return __ Valid Data`() {
        // ARRANGE
        val email = "parvez.cse.200104129@aust.edu"
        val pass = "123456@"
        val retypePass = "123456@"

        val expected = "Valid Data"

        // ACT
        val result = testingClass.emailPasswordValidation(email, pass, retypePass)

        // ASSERT
        assertEquals(expected, result)
    }

    @Test
    fun `given too long email , when emailPasswordValidation is called, then it should return __ Too long characters`() {
        // ARRANGE
        val email =
            "parvez.cse.200104129@aust.eduparvez.cse.200104129@aust.eduparvez.cse.200104129@aust.eduparvez.cse.200104129@aust.edu"
        val pass = "123456@"
        val retypePass = "123456@"

        val expected = "Too long characters"

        // ACT
        val result = testingClass.emailPasswordValidation(email, pass, retypePass)

        // ASSERT
        assertEquals(expected, result)
    }

    @Test
    fun `given external email, when emailPasswordValidation is called, then it should return __ Provide your aust edu email`() {
        // ARRANGE
        val email = "parvezdirom2000@gmail.com"
        val pass = "123456@"
        val retypePass = "123456@"

        val expected = "Provide your @aust.edu email"

        // ACT
        val result = testingClass.emailPasswordValidation(email, pass, retypePass)

        // ASSERT
        assertEquals(expected, result)
    }

    @Test
    fun `given empty password, when emailPasswordValidation is called, then it should return __ Enter a password`() {
        // ARRANGE
        val email = "parvez.cse.200104129@aust.edu"
        val pass = ""
        val retypePass = "123456@"

        val expected = "Enter a password"

        // ACT
        val result = testingClass.emailPasswordValidation(email, pass, retypePass)

        // ASSERT
        assertEquals(expected, result)
    }

    @Test
    fun `given password length less than 6, when emailPasswordValidation is called, then it should return __ Password is too short`() {
        // ARRANGE
        val email = "parvez.cse.200104129@aust.edu"
        val pass = "123456"
        val retypePass = "123456@"

        val expected = "Password is too short"

        // ACT
        val result = testingClass.emailPasswordValidation(email, pass, retypePass)

        // ASSERT
        assertEquals(expected, result)
    }

    @Test
    fun `given  password not matching retype password, when emailPasswordValidation is called, then it should return __ Passwords didn't match retype passwords`() {
        // ARRANGE
        val email = "parvez.cse.200104129@aust.edu"
        val pass = "123456@"
        val retypePass = "123456@@@"

        val expected = "Passwords didn't match retype passwords"

        // ACT
        val result = testingClass.emailPasswordValidation(email, pass, retypePass)

        // ASSERT
        assertEquals(expected, result)
    }

    @Test
    fun `given password without special character, when emailPasswordValidation is called, then it should return __ Give a special character such as`() {
        // ARRANGE
        val email = "parvez.cse.200104129@aust.edu"
        val pass = "1234567"
        val retypePass = "1234567"

        val expected = "Give a special character such as @,$,#.."

        // ACT
        val result = testingClass.emailPasswordValidation(email, pass, retypePass)

        // ASSERT
        assertEquals(expected, result)
    }


}