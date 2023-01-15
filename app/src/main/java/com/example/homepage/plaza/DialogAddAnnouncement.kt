package com.example.homepage.plaza

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import com.example.homepage.R
import com.google.android.material.bottomsheet.BottomSheetDialogFragment


class DialogAddAnnouncement : BottomSheetDialogFragment() {

    private var _binding : DialogAddAnnouncement? = null
    private val binding get() = _binding!!
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.bottom_sheet_layout, container, false)

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
        val officialTopicListAdapter  = createSpinnerAdapter(requireContext(), officialTopicList)
        topicSpinner.adapter = officialTopicListAdapter

        val advertisementTopicList = arrayOf(
            Topic("Giveaway", R.drawable.ic_topic_1),
            Topic("Sale Post", R.drawable.ic_topic_1),
            Topic("Find Roommates", R.drawable.ic_baseline_access_time_24)
        )
        val advertisementTopicListAdapter = createSpinnerAdapter(requireContext(), advertisementTopicList)

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
                        topicSpinner.adapter = createSpinnerAdapter(requireContext(), officialTopicList)
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
            // get the selected topic from the spinner
            val selectedTopic = categorySpinner.selectedItem.toString()
            // show a toast message with the selected topic name
            val selectedTo = topicSpinner.selectedItem.toString()
            Toast.makeText(requireContext(), "Selected topic: $selectedTopic and sub is  $selectedTo", Toast.LENGTH_SHORT).show()


            val contactNoPush = contactNo.text.toString()
            val  announcementDetailsPush = announcementDetails.text.toString()
            Toast.makeText(requireContext(), "Selected topic: $announcementDetailsPush and sub is  $contactNoPush ", Toast.LENGTH_SHORT).show()
        }






        return view
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




