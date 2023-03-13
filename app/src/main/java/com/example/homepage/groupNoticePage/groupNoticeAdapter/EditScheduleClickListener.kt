package com.example.homepage.groupNoticePage.groupNoticeAdapter

import android.app.DatePickerDialog
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.widget.*
import com.example.homepage.R
import com.example.homepage.groupNoticePage.GroupNoticeFragment
import com.example.homepage.groupNoticePage.groupNoticeModel.GroupNoticeData
import java.util.*

class EditScheduleClickListener(private val
                                _inflater: LayoutInflater,
                                private val currentTask: GroupNoticeData,
                                private val user: String
) :
    View.OnClickListener {
    override fun onClick(view: View?) {
        val context = view?.context
        val rootLayout = _inflater.inflate(R.layout.popup_add_notice, null)

        // Find all views
        val taskName = rootLayout.findViewById<EditText>(R.id.QuizNamePop)
        val taskDescription = rootLayout.findViewById<EditText>(R.id.TaskDescriptionPop)
        val taskDate = rootLayout.findViewById<TextView>(R.id.TaskDatePop)
        val closeButton = rootLayout.findViewById<Button>(R.id.CloseButton)
        val addButton = rootLayout.findViewById<Button>(R.id.AddButton)
        val chooseButton = rootLayout.findViewById<Button>(R.id.chooseButton)

        // Set values to views
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
            view,
            Gravity.CENTER,
            0,
            -500
        )

        chooseButton.setOnClickListener {
            val cldr: Calendar = Calendar.getInstance()
            val day: Int = cldr.get(Calendar.DAY_OF_MONTH)
            val month: Int = cldr.get(Calendar.MONTH)
            val year: Int = cldr.get(Calendar.YEAR)
            val picker = context?.let { it1 ->
                DatePickerDialog(
                    it1,
                    { _, year, monthOfYear, dayOfMonth ->
                        val dateFormat = "$dayOfMonth/${monthOfYear + 1}/$year"
                        taskDate.text = dateFormat
                    },
                    year,
                    month,
                    day
                )
            }

            if (picker != null) {
                picker.datePicker.minDate = cldr.timeInMillis
            }
            picker?.show()
        }

        closeButton.setOnClickListener {
            popupWindow.dismiss()
        }

        addButton.setOnClickListener {
            val name = taskName.text.toString()
            val description = taskDescription.text.toString()
            val date = taskDate.text.toString()

            if (name == "" || description == "") {
                Toast.makeText(
                    context,
                    "Please fill up all  the information",
                    Toast.LENGTH_SHORT
                ).show()
            } else {
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


