package com.example.homepage


import com.google.common.truth.Truth.assertThat
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4


@RunWith(JUnit4::class)
class UserValidatorTest
{

    @Test
    fun whenInputIsValid()
    {
        val email = "parvezdirom2000@aust.edu"
        val password = "1234@666"
        val result = UserValidator.validateInput(email,password)
        assertThat(result).isEqualTo(true)
    }

    @Test
    fun whenInputIsINvalid()
    {
        val email = "parvezdirom2000@aust.edu"
        val password = "1234"
        val result = UserValidator.validateInput(email,password)
        assertThat(result).isEqualTo(false)
    }

}