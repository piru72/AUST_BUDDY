package com.example.homepage.network.cache


import android.content.Context

class SharedPreference {

    fun saveUserDetailsToCache(context: Context, semester: String, section: String) {
        val sharedPreferences = context.getSharedPreferences("UserCache", Context.MODE_PRIVATE)
        val editor = sharedPreferences.edit()
        editor.putString("userSemester", semester)
        editor.putString("userSection", section)
        editor.apply()
    }
    fun getUserDetailsFromCache(context: Context): Pair<String?, String?> {
        val sharedPreferences = context.getSharedPreferences("UserCache", Context.MODE_PRIVATE)
        val semester = sharedPreferences.getString("userSemester", "")
        val section = sharedPreferences.getString("userSection", "")

        return Pair(semester, section)
    }

    fun saveAdminDetailsToCache(context: Context, isAdmin: String) {
        val sharedPreferences = context.getSharedPreferences("UserCache", Context.MODE_PRIVATE)
        val editor = sharedPreferences.edit()
        editor.putString("isAdmin", isAdmin)

        editor.apply()
    }

    fun getAdminDetailsFromCache(context: Context): String? {
        val sharedPreferences = context.getSharedPreferences("UserCache", Context.MODE_PRIVATE)
        return sharedPreferences.getString("isAdmin", "")
    }


}
