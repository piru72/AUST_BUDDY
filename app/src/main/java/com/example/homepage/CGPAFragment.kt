package com.example.homepage

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*


class CGPAFragment : ReplaceFragment() {

    @SuppressLint("SetTextI18n")
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        container?.removeAllViews()

        val v = inflater.inflate(R.layout.fragment_c_g_p_a, container, false)


        //   All the layouts object

        val layoutResultList = v.findViewById<LinearLayout>(R.id.layout_result_list)
        val layoutCalculate = v.findViewById<LinearLayout>(R.id.layout_calculate)
        val layoutResult = v.findViewById<LinearLayout>(R.id.layout_result)
        val layoutInstruction = v.findViewById<LinearLayout>(R.id.layout_instruction)

        val c10 = v.findViewById<LinearLayout>(R.id.c10)
        val c9 = v.findViewById<LinearLayout>(R.id.c9)


        // All the buttons object
        val btnSelect = v.findViewById<Button>(R.id.btn_select)

        val c1Credit = v.findViewById<Button>(R.id.c1_credit)
        val c2Credit = v.findViewById<Button>(R.id.c2_credit)
        val c3Credit = v.findViewById<Button>(R.id.c3_credit)
        val c4Credit = v.findViewById<Button>(R.id.c4_credit)
        val c5Credit = v.findViewById<Button>(R.id.c5_credit)
        val c6Credit = v.findViewById<Button>(R.id.c6_credit)
        val c7Credit = v.findViewById<Button>(R.id.c7_credit)
        val c8Credit = v.findViewById<Button>(R.id.c8_credit)
        val c9Credit = v.findViewById<Button>(R.id.c9_credit)
        val c10Credit = v.findViewById<Button>(R.id.c10_credit)

        val btnCalculate = v.findViewById<Button>(R.id.btn_calculate)
        val btnReset= v.findViewById<Button>(R.id.btn_reset)


        // All the Spinners object
        val semesterList = v.findViewById<Spinner>(R.id.semester_list)
        val c1ResultList = v.findViewById<Spinner>(R.id.c1_result_list)
        val c2ResultList = v.findViewById<Spinner>(R.id.c2_result_list)
        val c3ResultList = v.findViewById<Spinner>(R.id.c3_result_list)
        val c4ResultList = v.findViewById<Spinner>(R.id.c4_result_list)
        val c5ResultList = v.findViewById<Spinner>(R.id.c5_result_list)
        val c6ResultList = v.findViewById<Spinner>(R.id.c6_result_list)
        val c7ResultList = v.findViewById<Spinner>(R.id.c7_result_list)
        val c8ResultList = v.findViewById<Spinner>(R.id.c8_result_list)
        val c9ResultList = v.findViewById<Spinner>(R.id.c9_result_list)
        val c10ResultList = v.findViewById<Spinner>(R.id.c10_result_list)


        // All the Textview object
        val result = v.findViewById<TextView>(R.id.textview_result)


        hideLayout(layoutResultList,layoutResult,layoutCalculate)

        btnSelect.setOnClickListener {


            showLayout(layoutCalculate,layoutResult,layoutResultList)
            hideLayout(layoutInstruction)

            set3Credit(c1Credit,c2Credit,c3Credit,c4Credit,c5Credit)
            when (semesterList.selectedItem.toString()) {
                "3rd Year 2nd Semester" -> {
                    showLayout(c9,c10)
                    set75Credit(c6Credit,c7Credit,c8Credit)
                    set75Credit(c9Credit,c10Credit)

                }
                "4th Year 1st Semester"-> {
                    showLayout(c9,c10)
                    c6Credit.text = "3 Credit"
                    set75Credit(c7Credit,c8Credit,c9Credit)
                    set75Credit(c10Credit)
                }
                "4th Year 2nd Semester" -> {
                    hideLayout(c9,c10)
                    set75Credit(c6Credit,c7Credit,c8Credit)
                }

                else -> {
                    showLayout(c9)
                    hideLayout(c10)

                    when(semesterList.selectedItem.toString() ){
                        "1st Year 1st Semester","1st Year 2nd Semester","2nd Year 1st Semester" ->{
                            set15Credit(c6Credit,c7Credit,c8Credit)
                            set75Credit(c9Credit)

                        }
                       "2nd Year 2nd Semester" -> {
                           set15Credit(c6Credit,c7Credit)
                           set75Credit(c8Credit,c9Credit)
                       }
                        "3rd Year 1st Semester" -> {
                            set15Credit(c6Credit)
                            set75Credit(c7Credit,c8Credit,c9Credit)
                        }
                    }
                }
            }
        }


        return v
    }

    private fun hideLayout(layout :LinearLayout) {
        layout.visibility = View.GONE
    }
    private fun hideLayout(layout1 :LinearLayout, layout2 :LinearLayout) {
        layout1.visibility = View.GONE
        layout2.visibility = View.GONE
    }
    private fun hideLayout(layout1 :LinearLayout, layout2 :LinearLayout, layout3 :LinearLayout) {
        layout1.visibility = View.GONE
        layout2.visibility = View.GONE
        layout3.visibility = View.GONE
    }
    private fun showLayout(layout :LinearLayout) {
        layout.visibility = View.VISIBLE
    }

    private fun showLayout(layout1 :LinearLayout, layout2 :LinearLayout) {
        layout1.visibility = View.VISIBLE
        layout2.visibility = View.VISIBLE
    }

    private fun showLayout(layout1 :LinearLayout, layout2 :LinearLayout,layout3 :LinearLayout ) {
        layout1.visibility = View.VISIBLE
        layout2.visibility = View.VISIBLE
        layout3.visibility = View.VISIBLE
    }

    @SuppressLint("SetTextI18n")
    private fun set3Credit(c1Credit : Button, c2Credit : Button, c3Credit : Button, c4Credit : Button, c5Credit : Button) {

        c1Credit.text = "3 Credit"
        c2Credit.text = "3 Credit"
        c3Credit.text = "3 Credit"
        c4Credit.text = "3 Credit"
        c5Credit.text = "3 Credit"
    }
    private fun set15Credit(c6Credit : Button, c7Credit : Button, c8Credit : Button) {

        c6Credit.text = "1.5 Credit"
        c7Credit.text = "1.5 Credit"
        c8Credit.text = "1.5 Credit"
    }
    private fun set15Credit(c6Credit : Button, c7Credit : Button) {

        c6Credit.text = "1.5 Credit"
        c7Credit.text = "1.5 Credit"
    }
    private fun set15Credit(c6Credit : Button) {

        c6Credit.text = "1.5 Credit"
    }
    private fun set75Credit(c9Credit : Button ) {

        c9Credit.text = ".75 Credit"
    }
    private fun set75Credit( c8Credit : Button, c9Credit : Button) {

        c8Credit.text = ".75 Credit"
        c9Credit.text = ".75 Credit"
    }

    private fun set75Credit( c7Credit: Button,c8Credit : Button, c9Credit : Button) {
        c7Credit.text = ".75 Credit"
        c8Credit.text = ".75 Credit"
        c9Credit.text = ".75 Credit"
    }


}