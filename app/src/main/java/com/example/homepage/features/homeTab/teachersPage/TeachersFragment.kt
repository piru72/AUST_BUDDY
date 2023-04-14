package com.example.homepage.features.homeTab.teachersPage


import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.homepage.R
import com.example.homepage.databinding.FragmentTeachersBinding
import com.example.homepage.features.homeTab.teachersPage.TeacherAdapter.teacherAdapter
import com.example.homepage.features.homeTab.teachersPage.TeacherModel.teacherViewModel
import com.example.homepage.utils.helpers.ReplaceFragment
import java.util.*


class TeachersFragment : ReplaceFragment() {

    // Necessary objects and variables
    private val args: TeachersFragmentArgs by navArgs()
    private lateinit var viewModel: teacherViewModel
    private lateinit var userRecyclerView: RecyclerView
    lateinit var adapter: teacherAdapter
    private var fragmentBinding: FragmentTeachersBinding? = null
    private val viewBinding get() = fragmentBinding!!
    private var buttons: List<Button>? = null
    private lateinit var databasePath :String

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment and removing all previous views
        container?.removeAllViews()

        fragmentBinding = FragmentTeachersBinding.inflate(inflater, container, false)
        viewBinding.buttonFavouriteTeachers.setBackgroundColor(Color.parseColor("#58d28b"))


        // Creating the list of buttons
        buttons = ArrayList()
        (viewBinding.buttonFavouriteTeachers as Button?)?.let { (buttons as ArrayList<Button>).add(it) }
        (viewBinding.buttonCseTeachers as Button?)?.let { (buttons as ArrayList<Button>).add(it) }
        (viewBinding.buttonEeeTeachers as Button?)?.let { (buttons as ArrayList<Button>).add(it) }
        (viewBinding.buttonCeTeachers as Button?)?.let { (buttons as ArrayList<Button>).add(it) }
        (viewBinding.buttonMeTeachers as Button?)?.let { (buttons as ArrayList<Button>).add(it) }
        (viewBinding.buttonIpeTeachers as Button?)?.let { (buttons as ArrayList<Button>).add(it) }
        (viewBinding.buttonTeTeachers as Button?)?.let { (buttons as ArrayList<Button>).add(it) }
        (viewBinding.buttonASTeachers as Button?)?.let { (buttons as ArrayList<Button>).add(it) }
        (viewBinding.buttonArchTeachers as Button?)?.let { (buttons as ArrayList<Button>).add(it) }
        (viewBinding.buttonBbaTeachers as Button?)?.let { (buttons as ArrayList<Button>).add(it) }

        for (button in buttons as ArrayList<Button>) {
            button.setOnClickListener { changeButtonColor(button) }
        }

        databasePath = args.reference

        viewBinding.btnOther.setOnClickListener {
            val action = TeachersFragmentDirections.actionTeachersFragmentToDepartmentChooserFragment()
            findNavController().navigate(action)
        }

        return viewBinding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)



        // attaching the recycler view
        userRecyclerView = view.findViewById(R.id.teacher_list)
        userRecyclerView.layoutManager = LinearLayoutManager(context)
        userRecyclerView.setHasFixedSize(true)

        // The type of user interacting with the page
        // 1. User - Basic users
        // 2. Admin - The super admin viewing this from the dashboard
        // 3. User-favourites - The user/ admin is looking at his favourite list



        // Initializing the adapter with the department and usertype
        adapter = teacherAdapter("User","teachers")
        userRecyclerView.adapter = adapter

        // Initializing viewModel with appropriate database reference according to the databaseViewPath
        viewModel = ViewModelProvider(this)[teacherViewModel::class.java]


        if (databasePath == "user-favouriteTeachers")
            setTeachersDepartment(databasePath + "/${getCurrentUserId()}")
        else
            setTeachersDepartment(databasePath)

    }


    private fun changeButtonColor(selectedButton: Button) {

        val greenColor = Color.parseColor("#58d28b")
        val whiteColor = Color.parseColor("#FFFFFF")
        for (button in buttons!!) {
            if (button === selectedButton) {
                button.setBackgroundColor(greenColor)

                if(button.text.toString().lowercase(Locale.ROOT) == "favourite")
                    setTeachersDepartment("user-favouriteTeachers" + "/${getCurrentUserId()}")
                else
                    setTeachersDepartment("teachers-list/" + button.text.toString().lowercase(Locale.ROOT))
            } else {
                button.setBackgroundColor(whiteColor)
            }
        }
    }

    private fun setTeachersDepartment(path : String) {
        viewModel.initialize(path)
        // updating the teacher list
        viewModel.allUsers.observe(viewLifecycleOwner) {
            adapter.updateUserList(it)
        }
    }


}