package com.example.homepage

import android.annotation.SuppressLint
import android.view.View
import android.widget.Button
import android.widget.LinearLayout

class CgpaFunctions {


    // Functions needed for
     fun hideLayout(layout : LinearLayout) {
        layout.visibility = View.GONE
    }
    fun hideLayout(layout1 : LinearLayout, layout2 : LinearLayout) {
        layout1.visibility = View.GONE
        layout2.visibility = View.GONE
    }
    fun hideLayout(layout1 : LinearLayout, layout2 : LinearLayout, layout3 : LinearLayout) {
        layout1.visibility = View.GONE
        layout2.visibility = View.GONE
        layout3.visibility = View.GONE
    }
     fun showLayout(layout : LinearLayout) {
        layout.visibility = View.VISIBLE
    }

     fun showLayout(layout1 : LinearLayout, layout2 : LinearLayout) {
        layout1.visibility = View.VISIBLE
        layout2.visibility = View.VISIBLE
    }

     fun showLayout(layout1 : LinearLayout, layout2 : LinearLayout, layout3 : LinearLayout) {
        layout1.visibility = View.VISIBLE
        layout2.visibility = View.VISIBLE
        layout3.visibility = View.VISIBLE
    }

    @SuppressLint("SetTextI18n")
     fun set3Credit(c1Credit : Button, c2Credit : Button, c3Credit : Button, c4Credit : Button, c5Credit : Button) {

        c1Credit.text = "3 Credit"
        c2Credit.text = "3 Credit"
        c3Credit.text = "3 Credit"
        c4Credit.text = "3 Credit"
        c5Credit.text = "3 Credit"
    }
     fun set15Credit(c6Credit : Button, c7Credit : Button, c8Credit : Button) {

        c6Credit.text = "1.5 Credit"
        c7Credit.text = "1.5 Credit"
        c8Credit.text = "1.5 Credit"
    }
     fun set15Credit(c6Credit : Button, c7Credit : Button) {

        c6Credit.text = "1.5 Credit"
        c7Credit.text = "1.5 Credit"
    }
     fun set15Credit(c6Credit : Button) {

        c6Credit.text = "1.5 Credit"
    }
     fun set75Credit(c9Credit : Button) {

        c9Credit.text = ".75 Credit"
    }
     fun set75Credit(c8Credit : Button, c9Credit : Button) {

        c8Credit.text = ".75 Credit"
        c9Credit.text = ".75 Credit"
    }

     fun set75Credit(c7Credit: Button, c8Credit : Button, c9Credit : Button) {
        c7Credit.text = ".75 Credit"
        c8Credit.text = ".75 Credit"
        c9Credit.text = ".75 Credit"
    }


}