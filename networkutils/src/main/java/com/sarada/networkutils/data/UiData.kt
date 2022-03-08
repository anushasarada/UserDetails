package com.sarada.networkutils.data

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import kotlinx.android.parcel.Parcelize

@Parcelize
data class UiData(
    @Json(name = "uitype")var uiType: String,
    var key: String? = null,
    var value: String? = null,
    var hint: String? = null
):Parcelable
