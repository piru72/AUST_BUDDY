package com.example.homepage.features.groupTab.groupNoticePage.adapter

import android.app.DatePickerDialog
import android.content.DialogInterface
import android.graphics.Color
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.widget.*
import com.example.homepage.R
import com.example.homepage.utils.models.GroupNoticeData
import com.example.homepage.database.ChildUpdaterHelper
import java.util.*

class EditScheduleClickListener(private val
                                _inflater: LayoutInflater,
                                private val currentTask: GroupNoticeData
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
            picker?.datePicker?.minDate = cldr.timeInMillis
            val myColor = Color.parseColor("#FF000000")
            picker!!.show()
            picker.getButton(DialogInterface.BUTTON_POSITIVE)?.setTextColor(myColor)
            picker.getButton(DialogInterface.BUTTON_NEGATIVE)?.setTextColor(myColor)

            picker.datePicker.minDate = cldr.timeInMillis
            picker.show()
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
                ChildUpdaterHelper().writeNewTask(
                    currentTask.uid.toString(),
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


