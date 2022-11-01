package com.example.homepage.store

import android.os.Bundle
import android.util.Log
import android.view.Gravity
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.homepage.Quiz.Tasks
import com.example.homepage.R
import com.example.homepage.databinding.FragmentStoreBinding
import com.example.homepage.store.Model.Materials
import com.example.homepage.superClass.ReplaceFragment
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase


class StoreFragment : ReplaceFragment() {
    private var _binding: FragmentStoreBinding? = null
    private val binding get() = _binding!!

    private lateinit var database: DatabaseReference
    private lateinit var materialReference: DatabaseReference
    private lateinit var auth: FirebaseAuth
    private lateinit var recycler: RecyclerView
//    private var adapter: materialAdapter? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentStoreBinding.inflate(inflater, container, false)

        auth = Firebase.auth
        database = Firebase.database.reference
        val user = auth.currentUser!!.uid
        materialReference =
            FirebaseDatabase.getInstance().getReference("user-materials").child(user)
        recycler = binding.materialList

        recycler.layoutManager = LinearLayoutManager(context)


        binding.floatingPostMaterialButton.setOnClickListener {
            val rootLayout = layoutInflater.inflate(R.layout.add_item_for_sell_popup, null)

            val bookName = rootLayout.findViewById<EditText>(R.id.BookNamePop)
            val bookSemester = rootLayout.findViewById<EditText>(R.id.BookSemester)
            val bookYear = rootLayout.findViewById<EditText>(R.id.BookYear)
            val addButton = rootLayout.findViewById<Button>(R.id.AddButton)
            val closeButton = rootLayout.findViewById<Button>(R.id.CloseButton)

            val popupWindow = PopupWindow(
                rootLayout,
                LinearLayout.LayoutParams.WRAP_CONTENT,
                LinearLayout.LayoutParams.WRAP_CONTENT, true
            )

            popupWindow.update()
            popupWindow.elevation = 20.5F
            popupWindow.showAtLocation(

                binding.materialListActivity, // Location to display popup window
                Gravity.CENTER, // Exact position of layout to display popup
                0, // X offset
                -500// Y offset
            )

            closeButton.setOnClickListener {
                popupWindow.dismiss()
            }

            addButton.setOnClickListener {

                val name = bookName.text.toString()
                val semester = bookSemester.text.toString()
                val year = bookYear.text.toString()

                if (name == "" || year == "" || semester == "")
                    Toast.makeText(
                        context,
                        "Please fill up all  the information",
                        Toast.LENGTH_SHORT
                    ).show()
                else
                    addNewMaterial(user, name, year, semester)
                popupWindow.dismiss()


            }


        }
        return binding.root
    }

    private fun addNewMaterial(
        userId: String,
        bookName: String,
        bookYear: String,
        bookSemester: String
    ) {
        // Create new post at /user-posts/$userid/$postid and at
        // /posts/$postid simultaneously
        val key = database.child("posts").push().key

        if (key == null) {
            Log.w("TodoActivity", "Couldn't get push key for posts")
            return
        }
        val newMaterial= Materials(userId, bookName, bookYear, bookSemester)
        val taskValues = newMaterial.toMap()
        val childUpdates = hashMapOf<String, Any>(
            //*   "/tasks/$key" to taskValues,
//            "/user-tasks/$userId/$key" to taskValues
            "/user-materials/$key" to taskValues
        )
        database.updateChildren(childUpdates)

    }

}