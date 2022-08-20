package com.example.homepage

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.core.content.ContextCompat.getSystemService


class CGPAFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        container?.removeAllViews()

        val v = inflater.inflate(R.layout.fragment_c_g_p_a, container, false)

//        val spinner = v.findViewById<Spinner>(R.id.grade_list)
//        val textInputBox = v.findViewById<EditText>(R.id.result_list)
//        val btnCalculate = v.findViewById<Button>(R.id.btn_calculate)
//        val textViewResult = v.findViewById<TextView>(R.id.result)
//        val textViewGrade = v.findViewById<TextView>(R.id.grade)
//
//
//
//        btnCalculate.setOnClickListener{
//            val text = spinner.selectedItem.toString()
//            val text2 = textInputBox.text.toString()
//            textViewResult.text = text
//            textViewGrade.text = text2
//            spinner.visibility = View.GONE;
//            //spinner.visibility = View.VISIBLE;
//
//        }




        return v
    }





}