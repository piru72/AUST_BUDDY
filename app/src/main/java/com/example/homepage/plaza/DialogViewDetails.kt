package com.example.homepage.plaza

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.homepage.R
import com.google.android.material.bottomsheet.BottomSheetDialogFragment

class DialogViewDetails : BottomSheetDialogFragment() {
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.dialog_view_details, container, false)

        return view
    }
}