package com.example.homepage

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*


class CGPAFragment : ReplaceFragment() {

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


        // All the buttons object
        val btnSelect = v.findViewById<Button>(R.id.btn_select)



        val semesterList = v.findViewById<Spinner>(R.id.semester_list)
        val result = v.findViewById<TextView>(R.id.result)
        val c10 = v.findViewById<LinearLayout>(R.id.c10)


        val c9 = v.findViewById<LinearLayout>(R.id.c9)
        val btnCalculate = v.findViewById<Button>(R.id.btn_calculate)


        hideLayout(layoutResultList)
        hideLayout(layoutResult)
        hideLayout(layoutCalculate)


        btnSelect.setOnClickListener {
            layoutResultList.isEnabled = true
            btnCalculate.visibility = View.VISIBLE
            layoutResult.visibility = View.VISIBLE
            layoutCalculate.visibility = View.VISIBLE
            layoutInstruction.visibility = View.GONE


            when (semesterList.selectedItem.toString()) {
                "4th Year 1st Semester", "3rd Year 2nd Semester" -> {
                    layoutResultList.visibility = View.VISIBLE
                    c10.visibility = View.VISIBLE
                    c9.visibility = View.VISIBLE

                }
                "4th Year 2nd Semester" -> {
                    layoutResultList.visibility = View.VISIBLE
                    c10.visibility = View.GONE
                    c9.visibility = View.GONE
                }
                else -> {

                    layoutResultList.visibility = View.VISIBLE
                    c9.visibility = View.VISIBLE
                    c10.visibility = View.GONE
                }
            }
        }


        return v
    }

    private fun hideLayout(layout :LinearLayout) {
        layout.visibility = View.GONE
    }
    private fun showLayout(layout :LinearLayout) {
        layout.visibility = View.VISIBLE
    }


}