package com.example.homepage.helperClass

import java.time.LocalDate
import java.time.format.DateTimeFormatter

class CustomFormatters {

    private fun makeDateString(dateStr: String): String {
        val parts = dateStr.split("/")
        val day = parts[0]
        var month = parts[1]
        if (month.length == 1) {
            month = "0$month"
        }
        val year = parts[2]

        return "$day/$month/$year"
    }

    fun formatDateString(dateStr: String): LocalDate {
        val date = makeDateString(dateStr)
        val formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy")
        return LocalDate.parse(date, formatter)
    }
}