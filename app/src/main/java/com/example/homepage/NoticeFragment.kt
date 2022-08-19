package com.example.homepage

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button


class NoticeFragment : Fragment() {


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val v = inflater.inflate(R.layout.fragment_notice, container, false)

        val button = v.findViewById<Button>(R.id.notice_button)

        button.setOnClickListener{
//            val fragment = RequestFragment()
//            val fragmentManager = requireActivity().supportFragmentManager
//            val fragmentTransaction = fragmentManager.beginTransaction()
//            fragmentTransaction.replace(R.id.fragment_notice, fragment)
//            fragmentTransaction.addToBackStack(null)
//            fragmentTransaction.commit()
            replaceFragment(RequestFragment(),R.id.fragment_notice)
        }

        return v
    }

    private fun replaceFragment(fragment: Fragment, xml_file_name: Int) {
        val fragmentManager = requireActivity().supportFragmentManager
        val fragmentTransaction = fragmentManager.beginTransaction()
        fragmentTransaction.replace(xml_file_name, fragment)
        fragmentTransaction.addToBackStack(null)
        fragmentTransaction.commit()



    }


}