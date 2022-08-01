package com.info.meisodeneme.adapter

import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.info.meisodeneme.view.RegisterFragment
import com.info.meisodeneme.view.SignInFragment
import com.info.meisodeneme.view.SliderFragment


class ViewPagerAdapter(activity: SliderFragment, private val tabCount: Int): FragmentStateAdapter(activity){
    override fun getItemCount(): Int = tabCount


    override fun createFragment(position: Int): Fragment {

        return when(position){
            0-> RegisterFragment()
            1-> SignInFragment()
            else-> RegisterFragment()
        }
    }
}

