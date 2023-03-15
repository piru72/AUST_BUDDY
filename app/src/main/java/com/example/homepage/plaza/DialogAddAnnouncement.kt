package com.example.homepage.plaza

import android.icu.text.SimpleDateFormat
import android.os.Bundle
import android.os.Looper
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import com.example.homepage.R
import com.example.homepage.Model.Announcements
import com.example.homepage.helperClass.Helper
import com.example.homepage.helperClass.spinner.SpinnerItem
import com.google.android.gms.tasks.OnCompleteListener
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase
import com.google.firebase.messaging.FirebaseMessaging
import com.google.firebase.messaging.RemoteMessage
import java.util.*


class DialogAddAnnouncement : BottomSheetDialogFragment() {

    private lateinit var database: DatabaseReference
    private lateinit var auth: FirebaseAuth
    private val userV = FirebaseAuth.getInstance().currentUser
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.bottom_sheet_layout, container, false)
        auth = Firebase.auth
        database = Firebase.database.reference

        val categorySpinner = view.findViewById<Spinner>(R.id.topic_spinner_category)
        val topicSpinner = view.findViewById<Spinner>(R.id.topic_spinner_sub_category)
        val postAnnouncementButton = view.findViewById<Button>(R.id.AddButton)
        val contactNo = view.findViewById<EditText>(R.id.postersContactNo)
        val announcementDetails = view.findViewById<EditText>(R.id.announcementDetails)

        val topicSpinnerLayout = view.findViewById<LinearLayout>(R.id.spinner_topic_layout)

        val categoryList = arrayOf(
            SpinnerItem("Official", R.drawable.cate_official),
            SpinnerItem("Advertisement", R.drawable.cate_advertise),
            SpinnerItem("Help", R.drawable.cate_help),
            SpinnerItem("Others", R.drawable.cate_others)
        )
        val helper = Helper()
        categorySpinner.adapter = helper.createSpinnerAdapter(requireContext(), categoryList)


        val officialTopicList = arrayOf(
            SpinnerItem("Notice", R.drawable.cate_notice),
            SpinnerItem("Seminar", R.drawable.seminar),
            SpinnerItem("Job Opportunities", R.drawable.opportunity),
            SpinnerItem("Club Recruitment", R.drawable.club_recru),
            SpinnerItem("Workshop", R.drawable.workshop)
        )
        val officialTopicListAdapter = helper.createSpinnerAdapter(requireContext(), officialTopicList)
        topicSpinner.adapter = officialTopicListAdapter

        val advertisementTopicList = arrayOf(
            SpinnerItem("Giveaway", R.drawable.giveway),
            SpinnerItem("Sale Post", R.drawable.sale),
            SpinnerItem("Find Roommates", R.drawable.roommate)
        )
        val advertisementTopicListAdapter =
            helper.createSpinnerAdapter(requireContext(), advertisementTopicList)

        val helpTopicList = arrayOf(
            SpinnerItem("Blood Donation", R.drawable.blood_dona),
            SpinnerItem("Lost Item", R.drawable.lostitem)

        )
        val helpTopicListAdapter = helper.createSpinnerAdapter(requireContext(), helpTopicList)



        categorySpinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View?,
                position: Int,
                id: Long
            ) {
                val selectedTopic = categorySpinner.selectedItem as SpinnerItem
                when (selectedTopic.name) {
                    "Others" -> {
                        topicSpinnerLayout.visibility = View.GONE
                    }
                    "Official" -> {
                        topicSpinner.adapter =
                            helper.createSpinnerAdapter(requireContext(), officialTopicList)
                        topicSpinnerLayout.visibility = View.VISIBLE
                    }
                    "Advertisement" -> {
                        topicSpinner.adapter = advertisementTopicListAdapter
                        topicSpinnerLayout.visibility = View.VISIBLE
                    }
                    "Help" -> {
                        topicSpinner.adapter = helpTopicListAdapter
                        topicSpinnerLayout.visibility = View.VISIBLE
                    }
                    else -> {
                        topicSpinnerLayout.visibility = View.VISIBLE
                    }
                }
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {
            }
        }

        FirebaseMessaging.getInstance().token.addOnCompleteListener(OnCompleteListener { task ->
            if (!task.isSuccessful) {
                Log.w("TOKEN", "Fetching FCM registration token failed", task.exception)
                return@OnCompleteListener
            }

            // Get new FCM registration token
            val token = task.result

            // Log and toast
            //val msg = getString(R.string.msg_token_fmt, token)
            Log.d("DEBUG", token)
            //Toast.makeText(requireContext(), token, Toast.LENGTH_SHORT).show()
        })



        postAnnouncementButton.setOnClickListener {
            val selectedCategory = categorySpinner.selectedItem.toString()
            val selectedTopic = topicSpinner.selectedItem.toString()
            val contactNoGiven = contactNo.text.toString()
            val announcementDetailsGiven = announcementDetails.text.toString()
            val user = auth.currentUser!!.uid
            Log.d("DEBUG", "cute message")
            //f6NNBuN2R5i7wQcUYnBQ_B:APA91bEx5PsSyCpMi93SzUaKShFntW8Og0mF22mRGO54aa8Z4Bm11h0lgxPJQLwuLutkSzTHotOa2QFMp6sHQQKvamRlfBQd_q2OzB6W1yGG4N6t-UGNyzWqeHl4ourPwt5FNzk0LYPE

            val email = userV?.email.toString()
            val help = Helper()
            help.setInformation(email)
            val announcersDetailsWrite =
                contactNoGiven + " " + help.getUserName() + " " + help.getUserEmail() + " " + help.getUserId() + " " + help.getSession() + " " + help.getDepartment()
            if (announcementDetailsGiven == "" || announcementDetailsGiven.length <= 2)
                makeToast("Please fill with valid details")
            else if (!help.validNumber(contactNoGiven))
                makeToast("Provide 11 digit valid phone number")
            else {

                val formatter = SimpleDateFormat("yyyy-MM-dd")
                val date = Date()
                val currentDate = formatter.format(date)

                val messaging = FirebaseMessaging.getInstance()
                val message = RemoteMessage.Builder("1082440138378")
                    .setMessageId("messageId")
                    .addData("key", "value")
                    .build()
                val messageData = message.data
                messaging.send(message)

                if(Looper.myLooper() == Looper.getMainLooper()) {
                    Log.i("MainActivity Looper", "Sending message with data: $messageData")
                }


                Log.i("MainActivity", "Sending message with data: $messageData")

                addNewMaterial(
                    user,
                    currentDate,
                    "Not Available",
                    selectedCategory,
                    "Not Available",
                    contactNoGiven,
                    announcersDetailsWrite,
                    announcementDetailsGiven,
                    selectedTopic
                )
                this.dismiss()

            }


        }






        return view
    }

    fun makeToast(text: String) {
        Toast.makeText(requireContext(), text, Toast.LENGTH_SHORT).show()
    }

    private fun addNewMaterial(
        userId: String,
        currentDate: String,
        productAuthor: String,
        selectedCategory: String,
        productPrice: String,
        sellersContactNo: String,
        sellersDetails: String,
        productDetailsWrite: String,
        selectedTopic: String
    ) {
        val key = database.child("posts").push().key

        if (key == null) {
            Log.w("TodoActivity", "Couldn't get push key for posts")
            return
        }
        val newMaterial = Announcements(
            userId,
            currentDate,
            selectedTopic,
            selectedCategory,
            productPrice,
            sellersContactNo,
            sellersDetails,
            key,
            productDetailsWrite
        )
        val taskValues = newMaterial.toMap()
        val childUpdates = hashMapOf<String, Any>(
            "/user-posted-items/$userId/$key" to taskValues,
            "/public-announcements/all/$key" to taskValues,
            "/public-announcements/$selectedCategory/$key" to taskValues
        )
        database.updateChildren(childUpdates)

    }
}





