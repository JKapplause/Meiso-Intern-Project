package com.info.meisodeneme


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.FragmentTransaction
import androidx.navigation.Navigation
import androidx.viewpager2.widget.ViewPager2
import com.info.meisodeneme.adapter.OnboardingItemAdapter
import com.info.meisodeneme.fragments.RegisterFragment
import kotlinx.android.synthetic.main.fragment_onboarding_screen.*
import me.relex.circleindicator.CircleIndicator3


class OnboardingScreenFragment : Fragment() {
    private lateinit var onboardingItemAdapter: OnboardingItemAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)



       }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        return inflater.inflate(R.layout.fragment_onboarding_screen, container, false)


    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setOnboardingItems()

        var indicator3 = view?.findViewById<CircleIndicator3>(R.id.indicator)
        indicator3?.setViewPager(onboardingViewPager)

        val btn = view?.findViewById<Button>(R.id.button_start)
        btn?.setOnClickListener {
            val action =OnboardingScreenFragmentDirections.actionOnboardingScreenFragmentToSliderFragment()
            Navigation.findNavController(it).navigate(action)
        }
    }


    private fun setOnboardingItems() {
        onboardingItemAdapter = OnboardingItemAdapter(
            listOf(
                OnboardingItem(
                    R.drawable.first,
                    title = "Congratulations!",
                    description = "You are starting a diet program with no calorie limitation and meal details to keep in mind.\n"
                ),
                OnboardingItem(
                    R.drawable.second,
                    title = "Natural",
                    description = "The method we will implement is one of the most effective methods since the existence of humanity.\n"
                ),
                OnboardingItem(
                    R.drawable.third,
                    title = "Efffective and Healthy",
                    description = "You will feel your body is burning fat with ketosis mode and you will not lose your body resistance.\n",

                    ),
                OnboardingItem(
                    R.drawable.fourth,
                    title = "Applicable At All Levels",
                    description = "Our plan has been tried by millions and successful results have been achieved."
                )
            )
        )

        val onboardingViewPager = view?.findViewById<ViewPager2>(R.id.onboardingViewPager)
        onboardingViewPager?.adapter = onboardingItemAdapter

    }


}