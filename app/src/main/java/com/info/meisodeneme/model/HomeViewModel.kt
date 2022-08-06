package com.info.meisodeneme.model

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.info.meisodeneme.R
import com.info.meisodeneme.view.MeditationDetailFragment
import com.info.meisodeneme.view.MeditationDetailFragmentArgs

class HomeViewModel : ViewModel() {
    val LiveData = MutableLiveData<DataModel>()


    fun getData() {

        val horiz = DataModel("hi","sel",R.drawable.home_1,R.drawable.hback_1)

        LiveData.value = horiz
    }

}