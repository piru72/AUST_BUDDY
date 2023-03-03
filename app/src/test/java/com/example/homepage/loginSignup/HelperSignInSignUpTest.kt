package com.example.homepage.loginSignup


import com.google.common.truth.Truth
import junit.framework.Assert.assertEquals
import org.junit.Test

class HelperSignInSignUpTest {

    @Test
    fun test()
    {
        // ARRANGE


        // ACT


        // ASSERT
    }



    @Test
    fun validateEmailPasswordFormat() {
        val expected = "Valid Data"
        val result  = HelperSignInSignUp().validateEmailPasswordFormat("parvez.cse.20010419@aust.edu" , "123456@" , "123456@")

        Truth.assertThat(expected).isEqualTo(result )
    }


    @Test
    fun cuteValueTest() {

        // ARRANGE
        val expected = "Cute Value"

        // ACT
        val result  = HelperSignInSignUp().cuteValue()


        // ASSERT
        assertEquals(expected, result )
    }
}