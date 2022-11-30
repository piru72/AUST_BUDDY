package com.example.homepage.groupNoticePage.groupNoticeAdapter

import android.app.DatePickerDialog
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.recyclerview.widget.RecyclerView
import com.example.homepage.R
import com.example.homepage.groupNoticePage.GroupNoticeFragment
import com.example.homepage.groupNoticePage.groupNoticeModel.GroupNoticeData
import com.google.firebase.auth.ktx.auth
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.ktx.Firebase
import java.util.*


class GroupNoticeAdapter(inflater: LayoutInflater) :
    RecyclerView.Adapter<GroupNoticeAdapter.ScheduleViewViewHolder>() {
    private val tasks = ArrayList<GroupNoticeData>()
    private val taskIds = ArrayList<String>()
    private var _inflater: LayoutInflater = inflater
    var picker: DatePickerDialog? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ScheduleViewViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(
            R.layout.card_quiz_schedule,
            parent, false
        )
        return ScheduleViewViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: ScheduleViewViewHolder, position: Int) {

        val auth = Firebase.auth
        val user = auth.currentUser!!.uid
        val context = holder.itemView.context

        val currentTask = tasks[position]

        holder.taskName.text = currentTask.taskname
        holder.taskDescription.text = currentTask.taskdescription
        holder.taskDate.text = currentTask.taskdate

        if (user != currentTask.uid) {
            holder.deleteButton.visibility = View.INVISIBLE
            holder.editButton.visibility = View.INVISIBLE
        }

        holder.deleteButton.setOnClickListener {
            val noticeReference =
                FirebaseDatabase.getInstance().getReference("group-notice")
                    .child(currentTask.groupId.toString())
            noticeReference.child(currentTask.path.toString()).removeValue()
        }
        holder.editButton.setOnClickListener {
            val rootLayout = _inflater.inflate(R.layout.popup_add_notice, null)

            val taskName = rootLayout.findViewById<EditText>(R.id.QuizNamePop)
            val taskDescription = rootLayout.findViewById<EditText>(R.id.TaskDescriptionPop)
            val taskDate = rootLayout.findViewById<TextView>(R.id.TaskDatePop)
            val closeButton = rootLayout.findViewById<Button>(R.id.CloseButton)
            val addButton = rootLayout.findViewById<Button>(R.id.AddButton)
            val chooseButton = rootLayout.findViewById<Button>(R.id.chooseButton)


            taskName.setText(currentTask.taskname)
            taskDescription.setText(currentTask.taskdescription)
            taskDate.text = currentTask.taskdate

            val popupWindow = PopupWindow(
                rootLayout,
                LinearLayout.LayoutParams.WRAP_CONTENT,
                LinearLayout.LayoutParams.WRAP_CONTENT, true
            )

            popupWindow.update()
            popupWindow.elevation = 20.5F
            popupWindow.showAtLocation(

                holder.editButton,
                Gravity.CENTER,
                0,
                -500
            )
            chooseButton.setOnClickListener {
                val cldr: Calendar = Calendar.getInstance()
                val day: Int = cldr.get(Calendar.DAY_OF_MONTH)
                val month: Int = cldr.get(Calendar.MONTH)
                val year: Int = cldr.get(Calendar.YEAR)
                picker = context?.let { it1 ->
                    DatePickerDialog(
                        it1,
                        { _, year, monthOfYear, dayOfMonth ->
                            taskDate.text =
                                dayOfMonth.toString() + "/" + (monthOfYear + 1) + "/" + year
                        },
                        year,
                        month,
                        day
                    )
                }
                picker?.datePicker?.minDate = cldr.timeInMillis
                picker!!.show()
            }

            closeButton.setOnClickListener {
                popupWindow.dismiss()
            }

            addButton.setOnClickListener {

                val name = taskName.text.toString()
                val description = taskDescription.text.toString()
                val date = taskDate.text.toString()

                if (name == "" || description == "")
                    Toast.makeText(
                        context,
                        "Please fill up all  the information",
                        Toast.LENGTH_SHORT
                    ).show()
                else {
                    val groupNoticeFragment = GroupNoticeFragment()
                    groupNoticeFragment.writeNewTask(
                        user,
                        name,
                        description,
                        date,
                        currentTask.path.toString(),
                        currentTask.groupId.toString()
                    )
                }

                popupWindow.dismiss()
            }
        }
    }

    override fun getItemCount(): Int {
        return tasks.size
    }

    fun updateUserList(tasks: List<GroupNoticeData>) {

        this.tasks.clear()
        this.tasks.addAll(tasks)
        notifyDataSetChanged()

    }

    class ScheduleViewViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        val taskName: TextView = itemView.findViewById(R.id.taskNameCard)
        val taskDescription: TextView = itemView.findViewById(R.id.taskDescriptionCard)
        val taskDate: TextView = itemView.findViewById(R.id.taskDateCard)
        val deleteButton: Button = itemView.findViewById(R.id.btnDeleteSchedule)
        val editButton: Button = itemView.findViewById(R.id.btnEditSchedule)

    }

}