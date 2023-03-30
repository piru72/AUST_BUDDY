package com.example.homepage.features.homeTab.teachersPage.TeacherAdapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.annotation.GlideModule
import com.example.homepage.databinding.CardTeachersBinding
import com.example.homepage.utils.models.TeacherData
import com.example.homepage.utils.recyclerViewHolder.TeacherViewHolder

@GlideModule
class teacherAdapter(private val userType: String, private val databaseViewPath: String) :
    RecyclerView.Adapter<TeacherViewHolder>() {

    private val _itemList = ArrayList<TeacherData>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TeacherViewHolder {

        val inflater = LayoutInflater.from(parent.context)
        val binding = CardTeachersBinding.inflate(inflater, parent, false)
        return TeacherViewHolder(binding)
    }


    override fun onBindViewHolder(holder: TeacherViewHolder, position: Int) {

        val currentItem = _itemList[position]
        holder.bind(currentItem, userType, databaseViewPath)


    }

    override fun getItemCount(): Int {
        return _itemList.size
    }

    fun updateUserList(userList: List<TeacherData>) {

        this._itemList.clear()
        this._itemList.addAll(userList)
        notifyDataSetChanged()

    }


}