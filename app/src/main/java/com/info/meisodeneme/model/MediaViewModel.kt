package com.info.meisodeneme.model

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.info.meisodeneme.R

class MediaViewModel : ViewModel() {

    val horizontalLiveData = MutableLiveData<DataObject>()


    fun getData() {
        val horiz = DataObject("1001 ", "Lorem", R.drawable.home_1)

        horizontalLiveData.value = horiz
    }


}