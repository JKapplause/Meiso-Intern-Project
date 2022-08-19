package com.info.meisodeneme.view


import android.annotation.SuppressLint
import android.os.Bundle
import android.os.Handler
import android.util.Log
import android.view.*
import android.widget.TextView
import android.widget.Toast
import androidx.activity.OnBackPressedCallback
import androidx.annotation.NonNull
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager.TAG
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.*
import com.google.firebase.firestore.auth.User
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import com.info.meisodeneme.model.DataModel
import com.info.meisodeneme.adapter.CustomAdapter
import com.info.meisodeneme.adapter.ImageAdapter
import com.info.meisodeneme.databinding.FragmentHomeBinding

import kotlin.system.exitProcess

class HomeFragment : Fragment() {
    private lateinit var auth: FirebaseAuth

    private lateinit var recyclerView: RecyclerView
    private lateinit var imageAdapter: ImageAdapter
    private var dataList :ArrayList<DataModel> = ArrayList()
    private var cardArrayList: ArrayList<DataModel> = ArrayList()
    private lateinit var customAdapter: CustomAdapter
    private lateinit var recyclerViewH: RecyclerView
    private lateinit var _binding: FragmentHomeBinding
    private val binding get() = _binding
    private lateinit var db: FirebaseFirestore
    private var backPressed = 0L


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        //Progressbar
        //Loading()
        // back press iyileştirilebilir
        callBack()

        val logout_btn = binding.logoutButton
        logout_btn.setOnClickListener {
            if (logout_btn.isClickable) {
                auth.signOut()
                val action = HomeFragmentDirections.actionHomeFragmentToSliderFragment()
                findNavController().navigate(action)
            }
        }
        auth = Firebase.auth
        db = Firebase.firestore

        getDataAll()
        horizontal()

        getDataAllV()
        vertical()

    }

    fun vertical() {
        recyclerView = binding.recyclerView
        recyclerView.setHasFixedSize(true)
        recyclerView.layoutManager = GridLayoutManager(getActivity()?.getApplicationContext(), 2)
        dataList = ArrayList()
        imageAdapter = ImageAdapter(dataList) {
            findNavController().navigate(HomeFragmentDirections.actionHomeFragmentToMediaDetailFragment(it))

        }
        recyclerView.adapter = imageAdapter
        imageAdapter.stateRestorationPolicy = RecyclerView.Adapter.StateRestorationPolicy.PREVENT_WHEN_EMPTY
    }

    fun horizontal() {
        recyclerViewH = binding.recyclerViewH
        recyclerViewH.setHasFixedSize(true)
        recyclerViewH.layoutManager = LinearLayoutManager(getActivity()?.getApplicationContext(),RecyclerView.HORIZONTAL,false)
        cardArrayList = ArrayList()
        customAdapter = CustomAdapter(cardArrayList){
            findNavController().navigate(HomeFragmentDirections.actionHomeFragmentToMediaDetailFragment(it))
        }
        recyclerViewH.adapter = customAdapter
        customAdapter.stateRestorationPolicy = RecyclerView.Adapter.StateRestorationPolicy.PREVENT_WHEN_EMPTY
    }

    @SuppressLint("RestrictedApi", "NotifyDataSetChanged")
    private fun getDataAllV() {
        db = FirebaseFirestore.getInstance()
        db.collection("MeditationsVert")
            .get()
            .addOnSuccessListener { result ->
                for (document in result) {
                    Log.d(TAG, "${document.id} => ${document.data}")
                }
                dataList.clear()
                val documents = result.documents
                for (document in documents) {
                    val id = document.getString("id")
                    val name = document.getString("name")
                    val subtitle = document.getString("subtitle")
                    val desc = document.getString("desc")
                    val image = document.getString("image")


                    val data = DataModel(id, name, subtitle,desc,image)
                    dataList.add(data)

                }
                imageAdapter.notifyDataSetChanged()

            }
            .addOnFailureListener { exception ->
                Log.d(TAG, "Error getting documents: ", exception)
            }

    }

    @SuppressLint("RestrictedApi", "NotifyDataSetChanged")
    private fun getDataAll() {
        db = FirebaseFirestore.getInstance()
        db.collection("Meditations")
            .get()
            .addOnSuccessListener { result ->
                for (document in result) {
                    Log.d(TAG, "${document.id} => ${document.data}")
                }
                cardArrayList.clear()

                val documents = result.documents
                for (document in documents) {
                    val id = document.getString("id")
                    val name = document.getString("name")
                    val subtitle = document.getString("subtitle")
                    val desc = document.getString("desc")
                    val image = document.getString("image")

                    val data = DataModel(id, name, subtitle,desc,image)
                    cardArrayList.add(data)
                }
                customAdapter.notifyDataSetChanged()
            }
            .addOnFailureListener { exception ->
                Log.d(TAG, "Error getting documents: ", exception)
            }



    }

    fun callBack() {
        val callback = object : OnBackPressedCallback(true) {
            override fun handleOnBackPressed() {
                if(backPressed + 2000 > System.currentTimeMillis()) {
                    exitProcess(-1)
                }else {
                    Toast.makeText(getActivity()?.getApplicationContext(), "Press back again to exit", Toast.LENGTH_SHORT).show()
                }
                backPressed = System.currentTimeMillis()
            }
        }
        requireActivity().onBackPressedDispatcher.addCallback(viewLifecycleOwner,callback)

    }





    /*  fun Loading() {
          val loading = LoadingDialog(requireActivity())
          loading.startLoading()
          val handler = Handler()
          handler.postDelayed(object :Runnable{
              override fun run() {
                  loading.isDismiss()
              }

          },900)
      }*/
}

