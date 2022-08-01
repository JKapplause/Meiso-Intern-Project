package com.info.meisodeneme.view

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
    //private lateinit var googleApiClient: GoogleApiClient

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

     /*   googleApiClient = GoogleApiClient.Builder(requireContext())
            .addApi(SafetyNet.API)
            .addConnectionCallbacks(this)
            .build()

        val signup_recaptcha = view?.findViewById<CheckBox>(R.id.signup_recaptcha)

        googleApiClient.connect()
        signup_recaptcha?.setOnClickListener {
            if (signup_recaptcha.isChecked) {
                SafetyNet.SafetyNetApi.verifyWithRecaptcha(googleApiClient,getString(R.string.SITE_KEY))
                    .setResultCallback { object :
                        ResultCallbacks<SafetyNetApi.RecaptchaTokenResult>(){
                        override fun onSuccess(status: SafetyNetApi.RecaptchaTokenResult) {
                            val status: Status = status.status
                            if (status.isSuccess) {
                                Toast.makeText(getActivity()?.getApplicationContext(), "verify", Toast.LENGTH_LONG).show()
                                signup_recaptcha.setTextColor(Color.GREEN)
                            }
                            else{
                                signup_recaptcha.setTextColor(Color.BLACK)


                            }
                        }

                        override fun onFailure(p0: Status) {
                            Toast.makeText(getActivity()?.getApplicationContext(), p0.statusMessage, Toast.LENGTH_LONG).show()

                        }
                    } }
            }
        }*/


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
   /* override fun onConnected(p0: Bundle?) {
        Toast.makeText(getActivity()?.getApplicationContext(), p0.toString(), Toast.LENGTH_LONG).show()
    }

    override fun onConnectionSuspended(p0: Int) {
        Toast.makeText(getActivity()?.getApplicationContext(), p0, Toast.LENGTH_LONG).show()
    }*/
}

