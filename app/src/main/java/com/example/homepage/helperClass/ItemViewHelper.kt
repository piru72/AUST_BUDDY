package com.example.homepage.helperClass

import android.content.Context
import android.widget.Toast

class ItemViewHelper(private val context: Context) {

    fun makeToast(text: String) {

        Toast.makeText(context, text, Toast.LENGTH_SHORT).show()

    }

}