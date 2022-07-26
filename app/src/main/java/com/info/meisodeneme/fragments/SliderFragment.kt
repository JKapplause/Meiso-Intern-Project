package com.info.meisodeneme.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.viewpager2.widget.ViewPager2
import com.google.android.material.tabs.TabLayout
import com.info.meisodeneme.R
import com.info.meisodeneme.adapter.ViewPagerAdapter
import kotlinx.android.synthetic.main.fragment_slider.*


class SliderFragment : Fragment() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(

        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        return inflater.inflate(R.layout.fragment_slider, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setUpTabBar()


    }

    private fun setUpTabBar() {
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
}




/*val tabLayout=view.findViewById<TabLayout>(R.id.tab_layout)
    val viewPager2 = view.findViewById<ViewPager2>(R.id.view_pager2)

    val adapter = ViewPagerAdapter(getParentFragmentManager(), lifecycle)

    viewPager2.adapter = adapter


    TabLayoutMediator(tabLayout,viewPager2){tab,position->
        when(position) {
            0->{
                tab.text = "ÜYE OL"
            }
            1->{
                tab.text = "GİRİŞ YAP"
            }
        }
    }.attach()*/