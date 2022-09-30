package com.example.homepage

import com.example.homepage.R
import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.homepage.Quiz.Tasks
import com.example.homepage.databinding.FragmentScheduleBinding
import com.example.homepage.databinding.FragmentTodoBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.database.*
import com.google.firebase.database.ktx.database
import com.google.firebase.database.ktx.getValue
import com.google.firebase.ktx.Firebase


class ScheduleFragment : Fragment() {

    private var _binding: FragmentScheduleBinding? = null
    private val binding get() = _binding!!


    private lateinit var database: DatabaseReference
    private lateinit var taskReference: DatabaseReference
    private lateinit var auth: FirebaseAuth
    private lateinit var recycler: RecyclerView
    private var adapter: TaskAdapter? = null


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentScheduleBinding.inflate(inflater, container, false)

        auth = Firebase.auth
        database = Firebase.database.reference
        val user = auth.currentUser!!.uid
        taskReference = FirebaseDatabase.getInstance().getReference("user-tasks").child(user)
        recycler = binding.taskList

        recycler.layoutManager = LinearLayoutManager(context)

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

        val newtask = Tasks(userId, taskName, taskDescription, taskDate)
        val taskValues = newtask.toMap()
        val childUpdates = hashMapOf<String, Any>(
            //*   "/tasks/$key" to taskValues,
            "/user-tasks/$userId/$key" to taskValues
        )

        database.updateChildren(childUpdates)

    }

    override fun onStart() {
        super.onStart()
        adapter = context?.let { TaskAdapter(it, taskReference) }
        recycler.adapter = adapter
    }

    // /-----------------------------Adapter Class-----------------------------------------------
    private class TaskAdapter(
        private val context: Context,
        databaseReference: DatabaseReference
    ) : RecyclerView.Adapter<TaskAdapter.TaskViewHolder>() {

        private val childEventListener: ChildEventListener?
        private val taskIds = ArrayList<String>()
        private val tasks = ArrayList<Tasks>()

        init {

            // Create child event listener
            // [START child_event_listener_recycler]
            val childEventListener = object : ChildEventListener {
                override fun onChildAdded(dataSnapshot: DataSnapshot, previousChildName: String?) {
                    Log.d("GetData", "onChildAdded:" + dataSnapshot.key!!)

                    // A new comment has been added, add it to the displayed list
                    val taskreceived = dataSnapshot.getValue<Tasks>()

                    // [START_EXCLUDE]
                    // Update RecyclerView
                    taskIds.add(dataSnapshot.key!!)
                    tasks.add(taskreceived!!)
                    notifyItemInserted(tasks.size - 1)
                    // [END_EXCLUDE]
                }

                override fun onChildChanged(
                    dataSnapshot: DataSnapshot,
                    previousChildName: String?
                ) {
                    Log.d("GetData", "onChildChanged: ${dataSnapshot.key}")


                }

                override fun onChildRemoved(dataSnapshot: DataSnapshot) {
                    Log.d("GetData", "onChildRemoved:" + dataSnapshot.key!!)

                    // A comment has changed, use the key to determine if we are displaying this
                    // comment and if so remove it.
                    val commentKey = dataSnapshot.key

                    // [START_EXCLUDE]
                    val commentIndex = taskIds.indexOf(commentKey)
                    if (commentIndex > -1) {
                        // Remove data from the list
                        taskIds.removeAt(commentIndex)
                        tasks.removeAt(commentIndex)

                        // Update the RecyclerView
                        notifyItemRemoved(commentIndex)
                    } else {
                        Log.w("GetData", "onChildRemoved:unknown_child:" + commentKey!!)
                    }
                    // [END_EXCLUDE]
                }

                override fun onChildMoved(dataSnapshot: DataSnapshot, previousChildName: String?) {
                    Log.d("GetData", "onChildMoved:" + dataSnapshot.key!!)

                    // A comment has changed position, use the key to determine if we are
                    // displaying this comment and if so move it.
                    val movedComment = dataSnapshot.getValue<Tasks>()
                    val commentKey = dataSnapshot.key

                    //notifyItemMoved(movedComment,)


                }

                override fun onCancelled(databaseError: DatabaseError) {
                    Log.w("GetData", "postComments:onCancelled", databaseError.toException())
                    Toast.makeText(
                        context, "Database Error occurred here",
                        Toast.LENGTH_SHORT
                    ).show()
                }

            }
            databaseReference.addChildEventListener(childEventListener)
            // [END child_event_listener_recycler]
            // Store reference to listener so it can be removed on app stop
            this.childEventListener = childEventListener

        } //end of Init()


        class TaskViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
            val taskName: TextView = itemView.findViewById(R.id.taskNameCard)
            val taskDescription: TextView = itemView.findViewById(R.id.taskDescriptionCard)
            val taskdate: TextView = itemView.findViewById(R.id.taskDateCard)
            val deleteButton: Button = itemView.findViewById(R.id.deleteButton)
        }

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TaskViewHolder {
            val inflater = LayoutInflater.from(context)
            val view = inflater.inflate(R.layout.list_item_view, parent, false)
            return TaskViewHolder(view)
        }

        override fun onBindViewHolder(holder: TaskViewHolder, position: Int) {

            val auth = Firebase.auth
            val user = auth.currentUser!!.uid
            val taskReference =
                FirebaseDatabase.getInstance().getReference("user-tasks").child(user)

            val currentTask = tasks[position]

            holder.taskName.text = currentTask.taskname
            holder.taskDescription.text = currentTask.taskdescription
            holder.taskdate.text = currentTask.taskdate

            holder.deleteButton.setOnClickListener {
                val value = taskReference.child(taskIds[position])
                value.removeValue()
            }


        }

        override fun getItemCount(): Int = tasks.size


    } // end of adapter class


}