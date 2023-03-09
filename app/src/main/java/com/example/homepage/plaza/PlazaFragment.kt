package com.example.homepage.plaza

import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.homepage.databinding.FragmentPlazaBinding
import com.example.homepage.plaza.Adapter.PlazaAdapter
import com.example.homepage.plaza.Model.PlazaViewModel
import com.example.homepage.helperClass.ReplaceFragment


class PlazaFragment : ReplaceFragment() {


    private var fragmentBinding: FragmentPlazaBinding? = null
    private val viewBinding get() = fragmentBinding!!

    private lateinit var viewModel: PlazaViewModel
    private lateinit var recycler: RecyclerView
    private var adapter: PlazaAdapter? = null
    var buttons: List<Button>? = null


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        fragmentBinding = FragmentPlazaBinding.inflate(inflater, container, false)


        // Moving to the personal dashboard upon clicking this button
        viewBinding.btnPlazaDashboard.setOnClickListener {
            val action = PlazaFragmentDirections.actionPlazaFragmentToPlazaDashBoardFragment()
            findNavController().navigate(action)
        }

        // Creating the list of buttons
        buttons = ArrayList()
        (viewBinding.categoryAll as Button?)?.let { (buttons as ArrayList<Button>).add(it) }
        (viewBinding.categoryOfficial as Button?)?.let { (buttons as ArrayList<Button>).add(it) }
        (viewBinding.categoryAdvertisement as Button?)?.let { (buttons as ArrayList<Button>).add(it) }
        (viewBinding.categoryHelp as Button?)?.let { (buttons as ArrayList<Button>).add(it) }
        (viewBinding.categoryOthers as Button?)?.let { (buttons as ArrayList<Button>).add(it) }
        for (button in buttons as ArrayList<Button>) {
            button.setOnClickListener { changeButtonColor(button) }
        }

        // Opening a dialog to add an announcement
        viewBinding.floatingPostItemButton.setOnClickListener {

            val addAnnouncementBottomSheetFragment = DialogAddAnnouncement()
            addAnnouncementBottomSheetFragment.show(parentFragmentManager, addAnnouncementBottomSheetFragment.tag)
        }
        return viewBinding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        // attaching the recycler view
        recycler = viewBinding.announcementList
        recycler.layoutManager = LinearLayoutManager(context)
        recycler.setHasFixedSize(true)

        // Setting the adapter for the recycler
        adapter = PlazaAdapter()
        recycler.adapter = adapter

        // Setting the view model provider with the database path
        viewModel = ViewModelProvider(this)[PlazaViewModel::class.java]
        setAnnouncementCategory("all")

    }

    private fun setAnnouncementCategory(category: String) {
        viewModel.initialize("public-announcements/$category")
        viewModel.allAnnouncement.observe(viewLifecycleOwner) {
            adapter!!.updateAnnouncementList(it)
        }
    }

    private fun changeButtonColor(selectedButton: Button) {

        val greenColor = Color.parseColor("#58d28b")
        val whiteColor = Color.parseColor("#FFFFFF")
        for (button in buttons!!) {
            if (button === selectedButton) {
                button.setBackgroundColor(greenColor)
                setAnnouncementCategory(button.text.toString())
            } else {
                button.setBackgroundColor(whiteColor)
            }
        }
    }

}