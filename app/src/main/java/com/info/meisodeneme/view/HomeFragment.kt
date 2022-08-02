package com.info.meisodeneme.view

import android.os.Bundle
import android.view.*
import android.widget.Button
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
import com.info.meisodeneme.model.DataObject


class HomeFragment : Fragment() {
    private lateinit var auth: FirebaseAuth

    private lateinit var recyclerView: RecyclerView
    private lateinit var imageAdapter: ImageAdapter
    private var dataList = mutableListOf<DataModel>()
    private lateinit var cardList: ArrayList<DataObject>
    private lateinit var customAdapter: CustomAdapter
    private lateinit var rvList:RecyclerView



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        auth = FirebaseAuth.getInstance()


    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_home, container, false)
    }





    private fun addDataToList() {
        cardList.add(DataObject("Sleep Well","A dreamy sleep",R.drawable.home_1))
        cardList.add(DataObject("Deep Sleep","Restful nights",R.drawable.home_2))
        cardList.add(DataObject("Bedtime Imagery","Surrender to Sleep",R.drawable.home_3r))
        cardList.add(DataObject("Peaceful Sleep","Meet your inner self",R.drawable.home_4))
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)





        // Vertical Screen
        recyclerView = view.findViewById(R.id.recyclerView)
        recyclerView.layoutManager = GridLayoutManager(getActivity()?.getApplicationContext(),2)
        imageAdapter = ImageAdapter(requireActivity().getApplicationContext())
        recyclerView.adapter = imageAdapter

        dataList.add(DataModel("Falling Leaves","Cum sociis natoque ",R.drawable.vertical_1))
        dataList.add(DataModel("Cozy Campfire","Donec pede justo",R.drawable.vertical_2))
        dataList.add(DataModel("Night Call","Curabitur ullamcorper",R.drawable.vertical_3))
        dataList.add(DataModel("The Flower Garden ","Maecenas tempus",R.drawable.vertical_4))
        dataList.add(DataModel("The Time Machine","Etiam sit amet orci",R.drawable.vertical_5))
        dataList.add(DataModel("1001 Nights","Sed fringilla mauris sit",R.drawable.vertical_6))

        imageAdapter.setDataList(dataList)

        //Horizontal Screen
        rvList = view.findViewById(R.id.rvList)
        rvList.setHasFixedSize(true)
        rvList.layoutManager = LinearLayoutManager(getActivity()?.getApplicationContext(),RecyclerView.HORIZONTAL,false)
        cardList = ArrayList()
        addDataToList()
        customAdapter = CustomAdapter(cardList)
        rvList.adapter = customAdapter


        val logout_btn =view.findViewById<Button>(R.id.logout_button)
        logout_btn.setOnClickListener { task->
            if(logout_btn.isClickable) {
                auth.signOut()
                val action = HomeFragmentDirections.actionHomeFragmentToSliderFragment()
                findNavController().navigate(action)
            }

        }
    }






}


