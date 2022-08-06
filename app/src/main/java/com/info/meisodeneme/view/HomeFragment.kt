package com.info.meisodeneme.view

import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.auth.FirebaseAuth
import com.info.meisodeneme.model.DataModel
import com.info.meisodeneme.R
import com.info.meisodeneme.adapter.CustomAdapter
import com.info.meisodeneme.adapter.ImageAdapter
import com.info.meisodeneme.databinding.FragmentHomeBinding

class HomeFragment : Fragment() {
    private lateinit var auth: FirebaseAuth
    private lateinit var recyclerView: RecyclerView
    private lateinit var imageAdapter: ImageAdapter
    private var dataList = mutableListOf<DataModel>()
    private lateinit var cardList: ArrayList<DataModel>
    private lateinit var customAdapter: CustomAdapter
    private lateinit var rvList:RecyclerView
    private var _binding : FragmentHomeBinding?=null
    private val binding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        auth = FirebaseAuth.getInstance()

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    private fun addDataToList() {
        with(cardList) {
            add(DataModel("Sleep Well","A dreamy sleep",R.drawable.home_1,R.drawable.hback_1))
            add(DataModel("Deep Sleep","Restful nights",R.drawable.home_2, R.drawable.hback_2))
            add(DataModel("Bedtime Imagery","Surrender to Sleep",R.drawable.home_3r,R.drawable.hback_3))
            add(DataModel("Peaceful Sleep","Meet your inner self",R.drawable.home_4,R.drawable.hback_4))
        }

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        // Vertical Screen
        recyclerView = binding.recyclerView
        recyclerView.layoutManager = GridLayoutManager(getActivity()?.getApplicationContext(),2)
        imageAdapter = ImageAdapter(dataList){
            findNavController().navigate(HomeFragmentDirections.actionHomeFragmentToMediaDetailFragment(it))
        }
        recyclerView.adapter = imageAdapter

        with(dataList) {
            add(DataModel("Falling Leaves","Cum sociis natoque ",R.drawable.vertical_1,R.drawable.vback_1))
            add(DataModel("Cozy Campfire","Donec pede justo",R.drawable.vertical_2,R.drawable.vback_2))
            add(DataModel("Night Call","Curabitur ullamcorper",R.drawable.vertical_3,R.drawable.vback_3))
            add(DataModel("The Flower Garden ","Maecenas tempus",R.drawable.vertical_4,R.drawable.vback_4))
            add(DataModel("The Time Machine","Etiam sit amet orci",R.drawable.vertical_5,R.drawable.vback_5))
            add(DataModel("1001 Nights","Sed fringilla mauris sit",R.drawable.vertical_6,R.drawable.vback_6))

        }

        //Horizontal Screen
        rvList = binding.rvList
        rvList.setHasFixedSize(true)
        rvList.layoutManager = LinearLayoutManager(getActivity()?.getApplicationContext(),RecyclerView.HORIZONTAL,false)
        cardList = ArrayList()
        addDataToList()
        customAdapter = CustomAdapter(cardList) {
          findNavController().navigate(HomeFragmentDirections.actionHomeFragmentToMediaDetailFragment(it))
        }
        rvList.adapter = customAdapter


        val logout_btn = binding.logoutButton
        logout_btn.setOnClickListener { task->
            if(logout_btn.isClickable) {
                auth.signOut()
                val action = HomeFragmentDirections.actionHomeFragmentToSliderFragment()
                findNavController().navigate(action)
            }

        }
    }

}


