package com.example.homepage.helperClass

import org.junit.Assert
import org.junit.Test

class ValidationHelperTest {

    @Test
    fun validateCourseForm() {
    }

    @Test
    fun validateTeacherForm() {
    }

    @Test
    fun validWebsiteLink() {

        // Arrange

        val driveLink = ""
        val expected = true
        // Act
        val result = ValidationHelper().validWebsiteLink(driveLink)
        // Assert
        Assert.assertEquals(expected, result)
    }
}