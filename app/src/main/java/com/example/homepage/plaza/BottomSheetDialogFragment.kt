package com.example.homepage.plaza
import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.ImageView
import android.widget.Spinner
import android.widget.TextView
import com.example.homepage.R
import com.google.android.material.bottomsheet.BottomSheetDialogFragment


class BottomSheetFragment : BottomSheetDialogFragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.bottom_sheet_layout, container, false)


        val categorySpinner = view.findViewById<Spinner>(R.id.topic_spinner_category)
        val categoryList = arrayOf(
            Topic("Official", R.drawable.ic_topic_1),
            Topic("Advertisement", R.drawable.ic_topic_1),
            Topic("Help", R.drawable.ic_baseline_access_time_24),
            Topic("Others", R.drawable.ic_baseline_access_time_24)
        )
        val categoryAdapter = TopicAdapter(requireContext(), categoryList)
        categoryAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        categorySpinner.adapter = categoryAdapter

        val topicSpinner = view.findViewById<Spinner>(R.id.topic_spinner_sub_category)

        val officialTopicList = arrayOf(
            Topic("Notice", R.drawable.ic_topic_1),
            Topic("Seminar", R.drawable.ic_topic_1),
            Topic("Job Opportunities", R.drawable.ic_baseline_access_time_24),
            Topic("Club Recruitment", R.drawable.ic_baseline_access_time_24),
            Topic("Workshop", R.drawable.ic_baseline_access_time_24),
        )

        val officialTopicAdapter = TopicAdapter(requireContext(), officialTopicList)

        officialTopicAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        topicSpinner.adapter = officialTopicAdapter

        return view
    }
}
class TopicAdapter(context: Context, private val topics: Array<Topic>) : ArrayAdapter<Topic>(context, R.layout.item_topic, topics) {

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        val view = convertView ?: LayoutInflater.from(context).inflate(R.layout.item_topic, parent, false)
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
class Topic(val name: String, val icon: Int)
{
    override fun toString(): String {
        return name
    }
}




