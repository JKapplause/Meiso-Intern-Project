package com.info.meisodeneme.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize
import java.io.Serializable

@Parcelize data class DataModel(var title: String, var desc: String, var image: Int, var imageB: Int): Serializable, Parcelable