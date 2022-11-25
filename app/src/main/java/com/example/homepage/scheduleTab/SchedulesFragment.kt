package com.example.homepage.scheduleTab

import android.os.Bundle
import android.util.Log
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.homepage.R
import com.example.homepage.databinding.FragmentSchedulesBinding
import com.example.homepage.scheduleTab.scheduleAdapter.ScheduleAdapter
import com.example.homepage.scheduleTab.scheduleModel.ScheduleData
import com.example.homepage.scheduleTab.scheduleModel.ScheduleViewModel
import com.example.homepage.superClass.ReplaceFragment
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase


class SchedulesFragment(private var groupId: String = "") : ReplaceFragment() {
    private var _binding: FragmentSchedulesBinding? = null
    private val binding get() = _binding!!
    private lateinit var viewModel: ScheduleViewModel
    private lateinit var recycler: RecyclerView
    private var adapter: ScheduleAdapter? = null
    private lateinit var database: DatabaseReference
    private lateinit var auth: FirebaseAuth

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        container?.removeAllViews()
        _binding = FragmentSchedulesBinding.inflate(inflater, container, false)
        makeToast(groupId)
        auth = Firebase.auth
        database = Firebase.database.reference
        val user = auth.currentUser!!.uid
        binding.floatingActionButton.setOnClickListener {

            binding.informativeText.text=""
            val rootLayout = layoutInflater.inflate(R.layout.custom_popup, null)

            val taskName = rootLayout.findViewById<EditText>(R.id.TaskNamePop)
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

                binding.ToDoActivity, // Location to display popup window
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
        // Create new post at /user-posts/$userid/$postid and at
        // /posts/$postid simultaneously
        val key = database.child("posts").push().key
        if (key == null) {
            Log.w("TodoActivity", "Couldn't get push key for posts")
            return
        }

        val newtask = ScheduleData(userId, taskName, taskDescription, taskDate,key,groupId)
        val taskValues = newtask.toMap()
        val childUpdates = hashMapOf<String, Any>(
            //*   "/tasks/$key" to taskValues,
            "/user-tasks/$userId/$key" to taskValues,
            "/group-notice/$groupId/$key" to taskValues
        )

        database.updateChildren(childUpdates)

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        recycler = binding.taskList
        recycler.layoutManager = LinearLayoutManager(context)
        recycler.setHasFixedSize(true)
        adapter = ScheduleAdapter()
        recycler.adapter = adapter
        viewModel = ViewModelProvider(this)[ScheduleViewModel::class.java]

        viewModel.initialize(groupId)

        viewModel.allSchedules.observe(viewLifecycleOwner) {
            adapter!!.updateUserList(it)
        }

    }

}