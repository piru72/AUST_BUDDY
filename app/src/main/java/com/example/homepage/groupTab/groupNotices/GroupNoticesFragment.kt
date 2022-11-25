package com.example.homepage.groupTab.groupNotices

import android.os.Bundle
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.recyclerview.widget.RecyclerView
import com.example.homepage.R
import com.example.homepage.databinding.FragmentGroupNoticesBinding
import com.example.homepage.groupTab.groupNotices.Adapter.GroupNoticeAdapter
import com.example.homepage.groupTab.groupNotices.Model.GroupNoticeData
import com.example.homepage.groupTab.groupNotices.Model.GroupNoticeViewModel
import com.example.homepage.superClass.ReplaceFragment
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase


class GroupNoticesFragment(private var groupId: String ="") : ReplaceFragment() {
    private var _binding: FragmentGroupNoticesBinding? = null
    private val binding get() = _binding!!
    private lateinit var recycler: RecyclerView
    private lateinit var adapter: GroupNoticeAdapter
    private lateinit var viewModel: GroupNoticeViewModel
    private lateinit var database: DatabaseReference
    private lateinit var auth: FirebaseAuth
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        container?.removeAllViews()
        _binding = FragmentGroupNoticesBinding.inflate(inflater, container, false)
        makeToast(groupId)
        auth = Firebase.auth
        database = Firebase.database.reference
        val user = auth.currentUser!!.uid
        binding.floatingActionButton.setOnClickListener {


            val rootLayout = layoutInflater.inflate(R.layout.popup_add_notice, null)

            val taskName = rootLayout.findViewById<EditText>(R.id.QuizNamePop)
            val taskDescription = rootLayout.findViewById<EditText>(R.id.TaskDescriptionPop)
            val taskDate = rootLayout.findViewById<EditText>(R.id.TaskDatePop)
            val closeButton = rootLayout.findViewById<Button>(R.id.CloseButton)
            val addButton = rootLayout.findViewById<Button>(R.id.AddButton)

            val popupWindow = PopupWindow(
                rootLayout,
                LinearLayout.LayoutParams.WRAP_CONTENT,
                LinearLayout.LayoutParams.WRAP_CONTENT, true
            )

            popupWindow.update()
            popupWindow.elevation = 20.5F
            popupWindow.showAtLocation(

                binding.GroupNoticeFragment, // Location to display popup window
                Gravity.CENTER, // Exact position of layout to display popup
                0, // X offset
                -500// Y offset
            )

            closeButton.setOnClickListener {
                popupWindow.dismiss()
            }

            addButton.setOnClickListener {

                val name = taskName.text.toString()
                val description = taskDescription.text.toString()
                val date = taskDate.text.toString()

                if(name == "" || description=="")
                    Toast.makeText(context, "Please fill up all  the information", Toast.LENGTH_SHORT).show()
                else
                    writeNewTask(user, name, description, date)



                popupWindow.dismiss()
            }
        }

        return binding.root
    }

    private fun writeNewTask(
        userId: String,
        taskName: String,
        taskDescription: String,
        taskDate: String
    ) {

        val key = database.child("posts").push().key


        val newtask = GroupNoticeData(userId, taskName, taskDescription, taskDate,key,groupId)
        val taskValues = newtask.toMap()
        val childUpdates = hashMapOf<String, Any>(
            "groupNotice/$userId/$key" to taskValues
        )

        database.updateChildren(childUpdates)

    }


}