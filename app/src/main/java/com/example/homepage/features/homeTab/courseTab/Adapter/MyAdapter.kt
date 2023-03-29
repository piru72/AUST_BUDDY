package com.example.homepage.features.homeTab.courseTab.Adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.homepage.utils.models.CourseData
import com.example.homepage.databinding.CardCoursesBinding
import com.example.homepage.utils.recyclerViewHolder.CourseViewHolder


class MyAdapter(private var userType: String) : RecyclerView.Adapter<CourseViewHolder>() {

    private val itemList = ArrayList<CourseData>()


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CourseViewHolder {
        val binding =
            CardCoursesBinding.inflate(LayoutInflater.from(parent.context), parent, false)

        return CourseViewHolder(binding)
    }

    override fun onBindViewHolder(holder: CourseViewHolder, position: Int) {
        holder.bind(itemList[position], userType)
    }

    override fun getItemCount(): Int {
        return itemList.size
    }


    fun updateUserList(userList: List<CourseData>) {

        this.itemList.clear()
        this.itemList.addAll(userList)
        notifyDataSetChanged()

    }


}