package com.example.homepage.profileTab

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import com.example.homepage.R
import com.example.homepage.superClass.ReplaceFragment


class InviteFragment : ReplaceFragment() {


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        container?.removeAllViews()
        val v = inflater.inflate(R.layout.fragment_invite, container, false)

        val sendBTn = v.findViewById<Button>(R.id.sendBtn)



        sendBTn.setOnClickListener {

            val email = v.findViewById<TextView>(R.id.emailAddress)
//            val subject = v.findViewById<TextView>(R.id.subject) // use these boxes in case of custom body and subject
//            val message = v.findViewById<TextView>(R.id.message)

            val addresses = email.text.split(",".toRegex()).toTypedArray()

            val intent = Intent(Intent.ACTION_SENDTO).apply {
                data = Uri.parse("mailto:")
                putExtra(Intent.EXTRA_EMAIL, addresses)
                putExtra(Intent.EXTRA_SUBJECT, "HEY! CHECKOUT THIS AMAZING APP FOR AUSTIANS")
                putExtra(
                    Intent.EXTRA_TEXT,
                    "CURRENTLY THEY ARE ON DEVELOPMENT PHASE CHECK OUT THEIR GIT REPOS FOR THE UPDATE . THEIR LINK IS " +
                            "" +
                            "link: https://github.com/piru72/Uni_buddy"
                )
            }
            startActivity(intent)
        }




        return v
    }


}