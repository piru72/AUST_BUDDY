package com.example.homepage.loginSignup


import com.example.homepage.features.loginSignup.HelperSignInSignUp
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.Parameterized

@RunWith(Parameterized::class)
class HelperSignInSignUpTest(
    private val testData: EmailPasswordTestData

) {
    companion object {
        @JvmStatic
        @Parameterized.Parameters(name = "{index}: {0}")
        fun data() = getTestData().map { arrayOf(it) }
    }

    private lateinit var testingClass: HelperSignInSignUp

    @Before
    fun setUp() {
        testingClass = HelperSignInSignUp()
    }

    @Test
    fun `given email, password, and retype password, when emailPasswordValidation is called, then it should return expected result`() {

        // ACT
        val result = testingClass.emailPasswordValidation(
            testData.email,
            testData.password,
            testData.retypePassword
        )

        // ASSERT
        assertEquals(testData.expected, result)
    }


}