package com.example.homepage

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView


class CGPAFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        container?.removeAllViews()

        val v = inflater.inflate(R.layout.fragment_c_g_p_a, container, false)

        val btnCalculateCgpa = v.findViewById<Button>(R.id.btn_calculate_cgpa)
        val textResult = v.findViewById<TextView>(R.id.textView_final_cgpa)

        btnCalculateCgpa.setOnClickListener(){
            textResult.text="Clicked the button "
        }
        return v
    }


}