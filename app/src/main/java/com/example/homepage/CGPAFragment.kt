package com.example.homepage

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.fragment.app.Fragment


class CGPAFragment : ReplaceFragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        container?.removeAllViews()

        val v = inflater.inflate(R.layout.fragment_c_g_p_a, container, false)

        val resultPan = v.findViewById<LinearLayout>(R.id.result_pan)
        val btnSelect = v.findViewById<Button>(R.id.btn_select)
        val semesterList = v.findViewById<Spinner>(R.id.semester_list)
        val result = v.findViewById<TextView>(R.id.result)
        val grade = v.findViewById<TextView>(R.id.grade)
        val c10 = v.findViewById<LinearLayout>(R.id.c10)
        val c9 = v.findViewById<LinearLayout>(R.id.c9)
        val btnCalculate = v.findViewById<Button>(R.id.btn_calculate)

        resultPan.visibility = View.GONE
        btnCalculate.visibility = View.GONE
        var currentSemester = ""

        btnCalculate.setOnClickListener{
            grade.text = currentSemester


        }

        btnSelect.setOnClickListener {
            resultPan.isEnabled = true
            btnCalculate.visibility = View.VISIBLE
            currentSemester = semesterList.selectedItem.toString()
            when (semesterList.selectedItem.toString()) {
                "4th Year 1st Semester", "3rd Year 2nd Semester" -> {
                    resultPan.visibility = View.VISIBLE
                    c10.visibility = View.VISIBLE
                    c9.visibility = View.VISIBLE

                }
                "4th Year 2nd Semester" -> {
                    resultPan.visibility = View.VISIBLE
                    c10.visibility = View.GONE
                    c9.visibility = View.GONE
                }
                else -> {
                    resultPan.visibility = View.VISIBLE
                    c9.visibility = View.VISIBLE
                    c10.visibility = View.GONE
                }
            }
        }


        return v
    }


}