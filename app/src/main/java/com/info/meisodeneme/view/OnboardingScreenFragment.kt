package com.info.meisodeneme.view


import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.Navigation
import androidx.navigation.fragment.findNavController
import com.google.firebase.auth.FirebaseAuth
import com.info.meisodeneme.model.OnboardingItem
import com.info.meisodeneme.R
import com.info.meisodeneme.adapter.OnboardingItemAdapter
import com.info.meisodeneme.databinding.FragmentOnboardingScreenBinding
import kotlinx.android.synthetic.main.fragment_onboarding_screen.*

class OnboardingScreenFragment : Fragment() {
    private lateinit var onboardingItemAdapter: OnboardingItemAdapter
    private var _binding : FragmentOnboardingScreenBinding? = null
    private val binding get() = _binding!!
    lateinit var preference : SharedPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
       }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentOnboardingScreenBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setOnboardingItems()


        var indicator3 = binding.indicator
        indicator3.setViewPager(onboardingViewPager)


        preference = this.requireActivity().getSharedPreferences("OnboardingScreen",Context.MODE_PRIVATE)
        val firstStart : Boolean = preference.getBoolean("firstStart", false)
        if(firstStart) {
            val action =OnboardingScreenFragmentDirections.actionOnboardingScreenFragmentToSliderFragment()
            findNavController().navigate(action)
        }

        val btn = binding.buttonStart
        btn.setOnClickListener {
        go()
        }
    }

    private fun setOnboardingItems() {
        onboardingItemAdapter = OnboardingItemAdapter(
            listOf(
                OnboardingItem(
                    R.drawable.ic_first,
                    title = "Congratulations!",
                    description = "You are starting a diet program with no calorie limitation and meal details to keep in mind.\n"
                ),
                OnboardingItem(
                    R.drawable.ic_second,
                    title = "Natural",
                    description = "The method we will implement is one of the most effective methods since the existence of humanity.\n"
                ),
                OnboardingItem(
                    R.drawable.ic_third,
                    title = "Efffective and Healthy",
                    description = "You will feel your body is burning fat with ketosis mode and you will not lose your body resistance.\n",

                    ),
                OnboardingItem(
                    R.drawable.ic_fourth,
                    title = "Applicable At All Levels",
                    description = "Our plan has been tried by millions and successful results have been achieved."
                )
            )
        )
        val onboardingViewPager = binding.onboardingViewPager
        onboardingViewPager.adapter = onboardingItemAdapter
    }

    fun go() {
        val action =OnboardingScreenFragmentDirections.actionOnboardingScreenFragmentToSliderFragment()
        findNavController().navigate(action)
        preference = this.requireActivity().getSharedPreferences("OnboardingScreen",Context.MODE_PRIVATE)
        val editor = preference.edit()
        editor.putBoolean("firstStart",true)
        editor.apply()
    }
}