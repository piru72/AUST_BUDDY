package com.example.homepage.favouriteWebPage

import android.os.Bundle
import android.util.Log
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.fragment.app.Fragment
import com.example.homepage.R
import com.example.homepage.databinding.FragmentFavouriteWebPageBinding
import com.example.homepage.favouriteWebPage.Model.FavouriteWebpageData
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase


class FavouriteWebPageFragment : Fragment() {
   private  var _binding : FragmentFavouriteWebPageBinding? = null
   private  val binding get() = _binding!!
    private lateinit var auth: FirebaseAuth
    private lateinit var database: DatabaseReference

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        container?.removeAllViews()
        _binding = FragmentFavouriteWebPageBinding.inflate(inflater, container, false)
        auth = Firebase.auth
        database = Firebase.database.reference
        val user = auth.currentUser!!.uid
        binding.floatingAddWebsiteButton.setOnClickListener {


            val rootLayout = layoutInflater.inflate(R.layout.popup_add_webpage, null)

            val websiteNameField = rootLayout.findViewById<EditText>(R.id.websiteNamePop)
            val websiteLinkField = rootLayout.findViewById<EditText>(R.id.websiteLinkPop)
            val closeButton = rootLayout.findViewById<Button>(R.id.websiteCloseButton)
            val addButton = rootLayout.findViewById<Button>(R.id.websiteAddButton)

            val popupWindow = PopupWindow(
                rootLayout,
                LinearLayout.LayoutParams.WRAP_CONTENT,
                LinearLayout.LayoutParams.WRAP_CONTENT, true
            )

            popupWindow.update()
            popupWindow.elevation = 20.5F
            popupWindow.showAtLocation(

                binding.ToDoActivity, // Location to display popup window
                Gravity.CENTER, // Exact position of layout to display popup
                0, // X offset
                -500// Y offset
            )

            closeButton.setOnClickListener {
                popupWindow.dismiss()
            }

            addButton.setOnClickListener {

                val websiteName = websiteNameField.text.toString()
                val websiteLink = websiteLinkField.text.toString()

                if(websiteName == "" || websiteLink=="")
                    Toast.makeText(context, "Please fill up all  the information", Toast.LENGTH_SHORT).show()
                else
                   addNewWebsite(user, websiteName, websiteLink)
                popupWindow.dismiss()
            }
        }
       
        return binding.root
    }

    private fun addNewWebsite(user: String, websiteName: String, websiteLink: String) {

        val key = database.child("posts").push().key
        if (key == null) {
            Log.w("TodoActivity", "Couldn't get push key for posts")
            return
        }

        val newtask = FavouriteWebpageData(user, websiteName, websiteLink)
        val taskValues = newtask.toMap()
        val childUpdates = hashMapOf<String, Any>(
            "/user-favouriteWebsites/$user/$key" to taskValues
        )
        database.updateChildren(childUpdates)
    }


}