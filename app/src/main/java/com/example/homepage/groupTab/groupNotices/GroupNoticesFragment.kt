package com.example.homepage.groupTab.groupNotices

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.homepage.databinding.FragmentGroupNoticesBinding
import com.example.homepage.groupTab.groupNotices.Adapter.NoticeAdapter
import com.example.homepage.groupTab.groupNotices.Model.NoticeViewModel



class GroupNoticesFragment : Fragment() {
    private var _binding: FragmentGroupNoticesBinding? = null
    private val binding get() = _binding!!
    private lateinit var recycler: RecyclerView
    private  var adapter: NoticeAdapter?= null
    private lateinit var viewModel: NoticeViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        container?.removeAllViews()
        _binding = FragmentGroupNoticesBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        recycler = binding.groupNoticeListRecycler
        recycler.layoutManager = LinearLayoutManager(context)
        recycler.setHasFixedSize(true)
        adapter = NoticeAdapter()
        recycler.adapter = adapter
        viewModel = ViewModelProvider(this)[NoticeViewModel::class.java]

        viewModel.allNotices.observe(viewLifecycleOwner) {
            adapter!!.updateNoticeList(it)
        }

    }


}