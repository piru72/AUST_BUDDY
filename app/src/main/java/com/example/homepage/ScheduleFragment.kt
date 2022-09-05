package com.example.homepage

import android.os.Bundle
import android.view.*
import android.widget.*
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.homepage.databinding.FragmentTodoBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.database.*
import com.google.firebase.database.R
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase


class ScheduleFragment : Fragment() {

    private var _binding: FragmentTodoBinding? = null
    private val binding get() = _binding!!


    private lateinit var database: DatabaseReference
    private lateinit var taskReference: DatabaseReference
    private lateinit var auth: FirebaseAuth
    private lateinit var recycler: RecyclerView
    //private var adapter: TaskAdapter? = null


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentTodoBinding.inflate(inflater, container, false)


       auth = Firebase.auth
       database = Firebase.database.reference
        val user = auth.currentUser!!.uid
        taskReference = FirebaseDatabase.getInstance().getReference("user-tasks").child(user)
        recycler = binding.taskList

        recycler.layoutManager = LinearLayoutManager(context)

        binding.floatingActionButton.setOnClickListener {
//
            Toast.makeText(context,"this is toast message", Toast.LENGTH_SHORT).show()

//            val rootLayout = layoutInflater.inflate(R.layout.custompopup, null)
//
//            val taskName = rootLayout.findViewById<EditText>(R.id.TaskNamePop)
//            val taskDescription = rootLayout.findViewById<EditText>(R.id.TaskDescriptionPop)
//            val closeButton = rootLayout.findViewById<Button>(R.id.CloseButton)
//            val addButton = rootLayout.findViewById<Button>(R.id.AddButton)
//
//            //Pop window object
//            val popupWindow = PopupWindow(
//                rootLayout,
//                LinearLayout.LayoutParams.WRAP_CONTENT,
//                LinearLayout.LayoutParams.WRAP_CONTENT, true
//            )
//
//            popupWindow.update()
//            popupWindow.elevation = 10.5F
//            popupWindow.showAtLocation(
//
//                binding.ToDoActivity, // Location to display popup window
//                Gravity.CENTER, // Exact position of layout to display popup
//                0, // X offset
//                0 // Y offset
//            )
//
//            closeButton.setOnClickListener {
//                popupWindow.dismiss()
//            }
//
//            addButton.setOnClickListener {
//
//                val name = taskName.text.toString()
//                val description = taskDescription.text.toString()
//                //writeNewTask(user, name, description)
//
//                popupWindow.dismiss()
//            }

        }// end of floating action

        return binding.root
    }



}