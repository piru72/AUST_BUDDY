package com.example.homepage.superClass

import androidx.fragment.app.Fragment


open class ReplaceFragment : Fragment() {


    fun replaceFragment(fragment: Fragment, xml_file_name: Int) {
        val fragmentManager = requireActivity().supportFragmentManager
        val fragmentTransaction = fragmentManager.beginTransaction()
        fragmentTransaction.replace(xml_file_name, fragment)
        fragmentTransaction.addToBackStack(null)
        fragmentTransaction.commit()
    }
}