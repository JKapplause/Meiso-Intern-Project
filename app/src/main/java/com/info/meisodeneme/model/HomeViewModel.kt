package com.info.meisodeneme.model

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class HomeViewModel : ViewModel() {
    val LiveData = MutableLiveData<DataModel>()
    //val Loading = MutableLiveData<Boolean>()

    fun getDatas() {
        val horiz = DataModel("1", "night", "call", "night call", "ww.goog.com")
        LiveData.value = horiz

        //Loading.value = true
    }

}
