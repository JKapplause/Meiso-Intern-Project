package com.info.meisodeneme.view

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.activity.OnBackPressedCallback
import androidx.navigation.fragment.findNavController
import androidx.viewpager2.widget.ViewPager2
import com.google.android.material.tabs.TabLayout
import com.info.meisodeneme.R
import com.info.meisodeneme.adapter.ViewPagerAdapter
import com.info.meisodeneme.databinding.FragmentSignInBinding
import com.info.meisodeneme.databinding.FragmentSliderBinding
import kotlinx.android.synthetic.main.fragment_sign_in.*
import kotlinx.android.synthetic.main.fragment_slider.*
import kotlin.system.exitProcess

class SliderFragment : Fragment() {

    private var backPressed = 0L
    private var _binding: FragmentSliderBinding? = null
    private val binding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentSliderBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setUpTabBar()


    }

   private fun setUpTabBar() {
    // call back press
       callBack()




        val adapter = ViewPagerAdapter(this,tab_layout.tabCount)
        view_pager2.adapter = adapter

        view_pager2.registerOnPageChangeCallback(object :ViewPager2.OnPageChangeCallback() {
            override fun onPageSelected(position: Int) {
                tab_layout.selectTab(tab_layout.getTabAt(position))
            }
        })

        tab_layout.addOnTabSelectedListener(object :TabLayout.OnTabSelectedListener{
            override fun onTabSelected(tab: TabLayout.Tab) {
                    view_pager2.currentItem = tab.position
            }
            override fun onTabUnselected(tab: TabLayout.Tab?) {
            }
            override fun onTabReselected(tab: TabLayout.Tab?) {
            }
        })
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

}

