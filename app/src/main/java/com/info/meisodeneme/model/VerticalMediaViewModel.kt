package com.info.meisodeneme.model

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.info.meisodeneme.R

class VerticalMediaViewModel :ViewModel(){

    val verticalLiveData = MutableLiveData<DataModel>()

    fun getDataVertical() {
        val vertic = DataModel("1001 ", "Lorem", R.drawable.vertical_1)

        verticalLiveData.value = vertic
    }
}