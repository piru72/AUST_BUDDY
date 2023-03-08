package com.example.homepage.groupNoticePage

import android.app.DatePickerDialog
import android.content.DialogInterface
import android.graphics.Color
import android.os.Bundle
import android.util.Log
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.homepage.R
import com.example.homepage.databinding.FragmentGroupNoticeBinding
import com.example.homepage.groupNoticePage.groupNoticeAdapter.GroupNoticeAdapter
import com.example.homepage.groupNoticePage.groupNoticeModel.GroupNoticeData
import com.example.homepage.groupNoticePage.groupNoticeModel.GroupNoticeViewModel
import com.example.homepage.superClass.ReplaceFragment
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase
import java.util.*


class GroupNoticeFragment : ReplaceFragment() {
    private var fragmentBinding: FragmentGroupNoticeBinding? = null
    private val viewBinding get() = fragmentBinding!!
    private lateinit var viewModel: GroupNoticeViewModel
    private lateinit var recycler: RecyclerView
    private var adapter: GroupNoticeAdapter? = null
    private lateinit var database: DatabaseReference
    private lateinit var auth: FirebaseAuth
    private lateinit var _inflater: LayoutInflater
    var picker: DatePickerDialog? = null
    private val args: GroupNoticeFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        container?.removeAllViews()
        fragmentBinding = FragmentGroupNoticeBinding.inflate(inflater, container, false)
        _inflater = inflater
        auth = Firebase.auth
        val argGroupId = args.reference

        val user = auth.currentUser!!.uid
        viewBinding.floatingActionButton.setOnClickListener {

            viewBinding.informativeText.text = ""
            val rootLayout = layoutInflater.inflate(R.layout.popup_add_notice, null)

            val taskName = rootLayout.findViewById<EditText>(R.id.QuizNamePop)
            val taskDescription = rootLayout.findViewById<EditText>(R.id.TaskDescriptionPop)
            val taskDate = rootLayout.findViewById<TextView>(R.id.TaskDatePop)
            val chooseButton = rootLayout.findViewById<Button>(R.id.chooseButton)
            val closeButton = rootLayout.findViewById<Button>(R.id.CloseButton)
            val addButton = rootLayout.findViewById<Button>(R.id.AddButton)

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
                val myColor = Color.parseColor("#FF000000")
                picker!!.show()
                picker?.getButton(DialogInterface.BUTTON_POSITIVE)?.setTextColor(myColor)
                picker?.getButton(DialogInterface.BUTTON_NEGATIVE)?.setTextColor(myColor)
            }


            val popupWindow = PopupWindow(
                rootLayout,
                LinearLayout.LayoutParams.WRAP_CONTENT,
                LinearLayout.LayoutParams.WRAP_CONTENT, true
            )

            popupWindow.update()
            popupWindow.elevation = 20.5F
            popupWindow.showAtLocation(

                viewBinding.ToDoActivity, // Location to display popup window
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

                if (name == "" || description == "")
                    Toast.makeText(
                        context,
                        "Please fill up all  the information",
                        Toast.LENGTH_SHORT
                    ).show()
                else
                    writeNewTask(user, name, description, date, "make-key", argGroupId)



                popupWindow.dismiss()
            }

        }
        return viewBinding.root
    }

    fun writeNewTask(
        userId: String,
        taskName: String,
        taskDescription: String,
        taskDate: String,
        oldKey: String,
        groupId: String
    ) {
        database = Firebase.database.reference
        val newKey = database.child("posts").push().key
        if (newKey == null) {
            Log.w("TodoActivity", "Couldn't get push key for posts")
            return
        }
        val newGroupNotice: Any
        val pushingPath: String

        if (oldKey == "make-key") {
            newGroupNotice =
                GroupNoticeData(userId, taskName, taskDescription, taskDate, newKey, groupId)
            pushingPath = "/group-notice/${args.reference}/$newKey"

        } else {
            newGroupNotice =
                GroupNoticeData(userId, taskName, taskDescription, taskDate, oldKey, groupId)
            pushingPath = "/group-notice/$groupId/$oldKey"
        }
        val noticeValues = newGroupNotice.toMap()

        val childUpdates = hashMapOf<String, Any>(
            pushingPath to noticeValues
        )

        database.updateChildren(childUpdates)

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        recycler = viewBinding.taskList
        recycler.layoutManager = LinearLayoutManager(context)
        recycler.setHasFixedSize(true)
        adapter = GroupNoticeAdapter(_inflater)
        recycler.adapter = adapter
        viewModel = ViewModelProvider(this)[GroupNoticeViewModel::class.java]

        viewModel.initialize(args.reference)

        viewModel.allSchedules.observe(viewLifecycleOwner) {
            adapter!!.updateGroupNoticeList(it)
        }

    }

}