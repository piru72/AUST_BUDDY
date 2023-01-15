package com.example.homepage.plaza

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import com.example.homepage.R
import com.example.homepage.storeTab.Model.Materials
import com.example.homepage.superClass.Helper
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase


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
            Topic("Official", R.drawable.ic_topic_1),
            Topic("Advertisement", R.drawable.ic_topic_1),
            Topic("Help", R.drawable.ic_baseline_access_time_24),
            Topic("Others", R.drawable.ic_baseline_access_time_24)
        )
        categorySpinner.adapter = createSpinnerAdapter(requireContext(), categoryList)


        val officialTopicList = arrayOf(
            Topic("Notice", R.drawable.ic_topic_1),
            Topic("Seminar", R.drawable.ic_topic_1),
            Topic("Job Opportunities", R.drawable.ic_baseline_access_time_24),
            Topic("Club Recruitment", R.drawable.ic_baseline_access_time_24),
            Topic("Workshop", R.drawable.ic_baseline_access_time_24)
        )
        val officialTopicListAdapter = createSpinnerAdapter(requireContext(), officialTopicList)
        topicSpinner.adapter = officialTopicListAdapter

        val advertisementTopicList = arrayOf(
            Topic("Giveaway", R.drawable.ic_topic_1),
            Topic("Sale Post", R.drawable.ic_topic_1),
            Topic("Find Roommates", R.drawable.ic_baseline_access_time_24)
        )
        val advertisementTopicListAdapter =
            createSpinnerAdapter(requireContext(), advertisementTopicList)

        val helpTopicList = arrayOf(
            Topic("Blood Donation", R.drawable.ic_topic_1),
            Topic("Lost Item", R.drawable.ic_topic_1)

        )
        val helpTopicListAdapter = createSpinnerAdapter(requireContext(), helpTopicList)



        categorySpinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View?,
                position: Int,
                id: Long
            ) {
                val selectedTopic = categorySpinner.selectedItem as Topic
                when (selectedTopic.name) {
                    "Others" -> {
                        topicSpinnerLayout.visibility = View.GONE
                    }
                    "Official" -> {
                        topicSpinner.adapter =
                            createSpinnerAdapter(requireContext(), officialTopicList)
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



        postAnnouncementButton.setOnClickListener {
            val selectedCategory = categorySpinner.selectedItem.toString()
            val selectedTopic = topicSpinner.selectedItem.toString()
            val contactNoGiven = contactNo.text.toString()
            val announcementDetailsGiven = announcementDetails.text.toString()
            val user = auth.currentUser!!.uid

            val email = userV?.email.toString()
            val help = Helper()
            help.setInformation(email)
            val sellersDetailsWrite =
                contactNoGiven + " " + help.getUserName() + " " + help.getUserEmail() + " " + help.getUserId() + " " + help.getSession() + " " + help.getDepartment()
            if (announcementDetailsGiven == "" || announcementDetailsGiven.length <= 25)
                makeToast("Please fill with valid details")
            else if (!help.validNumber(contactNoGiven))
                makeToast("Provide 11 digit valid phone number")
            else {

                addNewMaterial(
                    user,
                    "Not Available",
                    "Not Available",
                    selectedCategory,
                    "Not Available",
                    contactNoGiven,
                    sellersDetailsWrite,
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
        productName: String,
        productAuthor: String,
        productCategory: String,
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
        val newMaterial = Materials(
            userId,
            productName,
            productAuthor,
            productCategory,
            productPrice,
            sellersContactNo,
            sellersDetails,
            key,
            productDetailsWrite
        )
        val taskValues = newMaterial.toMap()
        val childUpdates = hashMapOf<String, Any>(
            "/user-posted-items/$userId/$key" to taskValues,
            "/public-posts/$key" to taskValues
        )
        database.updateChildren(childUpdates)

    }
}

fun createSpinnerAdapter(context: Context, categoryList: Array<Topic>): TopicAdapter {

    val adapter = TopicAdapter(context, categoryList)
    adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
    return adapter

}

class TopicAdapter(context: Context, topics: Array<Topic>) :
    ArrayAdapter<Topic>(context, R.layout.item_topic, topics) {

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        val view =
            convertView ?: LayoutInflater.from(context).inflate(R.layout.item_topic, parent, false)
        val topic = getItem(position)
        if (topic != null) {
            view.findViewById<TextView>(R.id.textView).text = topic.name
        }
        if (topic != null) {
            view.findViewById<ImageView>(R.id.imageView).setImageResource(topic.icon)
        }
        return view
    }

    override fun getDropDownView(position: Int, convertView: View?, parent: ViewGroup): View {
        return getView(position, convertView, parent)
    }
}

class Topic(val name: String, val icon: Int) {
    override fun toString(): String {
        return name
    }
}




