package com.example.homepage.groupTab

import android.os.Bundle
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.LinearLayout
import android.widget.PopupWindow
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.homepage.R
import com.example.homepage.databinding.FragmentUserGroupsBinding
import com.example.homepage.groupTab.Group.Adapter.UserGroupAdapter
import com.example.homepage.groupTab.Group.Model.UserGroupsViewModel
import com.example.homepage.helperClass.ReplaceFragment
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase

class UserGroupsFragment : ReplaceFragment() {
    private var fragmentBinding: FragmentUserGroupsBinding? = null
    private val viewBinding get() = fragmentBinding!!
    private lateinit var viewModel: UserGroupsViewModel
    private lateinit var recycler: RecyclerView
    private lateinit var adapter: UserGroupAdapter
    private lateinit var database: DatabaseReference
    private lateinit var auth: FirebaseAuth
    private lateinit var _inflater: LayoutInflater

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        container?.removeAllViews()
        fragmentBinding = FragmentUserGroupsBinding.inflate(inflater, container, false)
        auth = Firebase.auth
        database = Firebase.database.reference
        _inflater = inflater
        viewBinding.floatingActionButton.setOnClickListener {


            val rootLayout = layoutInflater.inflate(R.layout.popup_create_join_class, null)

            val joinButton = rootLayout.findViewById<Button>(R.id.joinButton)
            val createButton = rootLayout.findViewById<Button>(R.id.createButton)

            val popupWindow = PopupWindow(
                rootLayout,
                LinearLayout.LayoutParams.WRAP_CONTENT,
                LinearLayout.LayoutParams.WRAP_CONTENT, true
            )

            popupWindow.update()
            popupWindow.elevation = 20.5F
            popupWindow.showAtLocation(

                viewBinding.userGroupFragment, // Location to display popup window
                Gravity.CENTER, // Exact position of layout to display popup
                0, // X offset
                -500// Y offset
            )

            joinButton.setOnClickListener {
                val action = UserGroupsFragmentDirections.actionNavigationUserGroupsToJoinGroupFragment()
                findNavController().navigate(action)
                popupWindow.dismiss()
            }
            createButton.setOnClickListener {
                val action = UserGroupsFragmentDirections.actionNavigationUserGroupsToCreateGroupFragment()
                findNavController().navigate(action)
                popupWindow.dismiss()
            }


        }
        return viewBinding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        recycler = viewBinding.UserGroupListRecycle
        recycler.layoutManager = LinearLayoutManager(context)
        recycler.setHasFixedSize(true)
        adapter = UserGroupAdapter(_inflater)
        recycler.adapter = adapter
        viewModel = ViewModelProvider(this)[UserGroupsViewModel::class.java]

        viewModel.allUserGroups.observe(viewLifecycleOwner) {
            adapter.updateUserGroupList(it)
        }

    }

}