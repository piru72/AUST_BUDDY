package com.example.homepage

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import com.example.homepage.CgpaFunctions
import com.example.homepage.R
import com.example.homepage.ReplaceFragment

class CGPAFragment : ReplaceFragment()  {

    @SuppressLint("SetTextI18n")
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        container?.removeAllViews()

        val v = inflater.inflate(R.layout.fragment_c_g_p_a, container, false)
        val cgFun = CgpaFunctions()

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


        cgFun.hideLayout(layoutResultList,layoutResult,layoutCalculate)


        btnCalculate.setOnClickListener{

        }


        btnSelect.setOnClickListener {


            cgFun.showLayout(layoutCalculate,layoutResult,layoutResultList)
            cgFun.hideLayout(layoutInstruction)

            cgFun.set3Credit(c1Credit,c2Credit,c3Credit,c4Credit,c5Credit)
            when (semesterList.selectedItem.toString()) {
                "3rd Year 2nd Semester" -> {
                    cgFun.showLayout(c9,c10)
                    cgFun.set75Credit(c6Credit,c7Credit,c8Credit)
                    cgFun.set75Credit(c9Credit,c10Credit)

                }
                "4th Year 1st Semester"-> {
                    cgFun.showLayout(c9,c10)
                    c6Credit.text = "3 Credit"
                    cgFun.set75Credit(c7Credit,c8Credit,c9Credit)
                    cgFun.set75Credit(c10Credit)
                }
                "4th Year 2nd Semester" -> {
                    cgFun.hideLayout(c9,c10)
                    cgFun.set75Credit(c6Credit,c7Credit,c8Credit)
                }

                else -> {
                    cgFun.showLayout(c9)
                    cgFun.hideLayout(c10)

                    when(semesterList.selectedItem.toString() ){
                        "1st Year 1st Semester","1st Year 2nd Semester","2nd Year 1st Semester" ->{
                            cgFun.set15Credit(c6Credit,c7Credit,c8Credit)
                            cgFun.set75Credit(c9Credit)

                        }
                       "2nd Year 2nd Semester" -> {
                           cgFun.set15Credit(c6Credit,c7Credit)
                           cgFun.set75Credit(c8Credit,c9Credit)
                       }
                        "3rd Year 1st Semester" -> {
                            cgFun.set15Credit(c6Credit)
                            cgFun.set75Credit(c7Credit,c8Credit,c9Credit)
                        }
                    }
                }
            }
        }

        return v
    }

}