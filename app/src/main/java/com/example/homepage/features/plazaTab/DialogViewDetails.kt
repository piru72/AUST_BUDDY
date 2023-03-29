package com.example.homepage.features.plazaTab

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.example.homepage.R
import com.google.android.material.bottomsheet.BottomSheetDialogFragment

class DialogViewDetails(private val sellersDetails: String?) : BottomSheetDialogFragment() {
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.bottom_sheet_view_details, container, false)

        val name = view.findViewById<TextView>(R.id.textView_name)
        val id = view.findViewById<TextView>(R.id.textView_id)
        val email = view.findViewById<TextView>(R.id.textView_email)
        val phone = view.findViewById<TextView>(R.id.textView_phone)

        name.text = sellersDetails?.split(" ")?.get(1)
        id.text = sellersDetails?.split(" ")?.get(3)
        email.text = sellersDetails?.split(" ")?.get(2)
        phone.text = sellersDetails?.split(" ")?.get(0)

        val contactNo = sellersDetails?.split(" ")?.get(0)

        phone.setOnClickListener {
            val i = Intent(Intent.ACTION_DIAL, Uri.parse("tel:$contactNo"))
            context?.startActivity(i)
        }

        email.setOnClickListener {
            val emailSend = sellersDetails?.split(" ")?.get(2)
            val addresses = emailSend ?.split(",".toRegex())?.toTypedArray()
            val i = Intent(Intent.ACTION_SENDTO, Uri.parse("mailto:"))
            i.putExtra(Intent.EXTRA_EMAIL, addresses)
            context?.startActivity(i)
        }


        return view
    }
}