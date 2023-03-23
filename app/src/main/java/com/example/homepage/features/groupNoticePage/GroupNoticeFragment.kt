package com.example.homepage.features.groupNoticePage

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.homepage.databinding.FragmentGroupNoticeBinding
import com.example.homepage.features.groupNoticePage.groupNoticeAdapter.EditScheduleClickListener
import com.example.homepage.features.groupNoticePage.groupNoticeAdapter.GroupNoticeAdapter
import com.example.homepage.utils.models.GroupNoticeData
import com.example.homepage.features.groupNoticePage.groupNoticeModel.GroupNoticeViewModel
import com.example.homepage.database.FirebaseUtils
import com.example.homepage.utils.helpers.ReplaceFragment


class GroupNoticeFragment : ReplaceFragment() {
    private var fragmentBinding: FragmentGroupNoticeBinding? = null
    private val viewBinding get() = fragmentBinding!!

    private lateinit var viewModel: GroupNoticeViewModel
    private lateinit var recycler: RecyclerView
    private var adapter: GroupNoticeAdapter? = null

    private lateinit var _inflater: LayoutInflater
    private val args: GroupNoticeFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        container?.removeAllViews()
        fragmentBinding = FragmentGroupNoticeBinding.inflate(inflater, container, false)
        _inflater = inflater

        setupButton()

        return viewBinding.root
    }

    private fun setupButton() {
        val argGroupId = args.reference
        val user = FirebaseUtils.getUserId()

        val noticeData = GroupNoticeData(user, "", "", "Choose Date", "make-key", argGroupId)
        val editScheduleClickListener = EditScheduleClickListener(_inflater, noticeData)
        viewBinding.floatingActionButton.setOnClickListener(editScheduleClickListener)
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