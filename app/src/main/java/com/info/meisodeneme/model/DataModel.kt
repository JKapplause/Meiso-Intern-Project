package com.info.meisodeneme.model

import android.os.Parcelable
import com.google.firebase.firestore.IgnoreExtraProperties
import kotlinx.android.parcel.Parcelize
import java.io.Serializable

@Parcelize data class DataModel(
    val id: String? =null,
    val name: String? =null,
    val subtitle: String? =null,
    val desc: String? =null,
    val image: String? =null
): Serializable, Parcelable