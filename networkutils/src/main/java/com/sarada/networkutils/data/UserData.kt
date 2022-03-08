package com.sarada.networkutils.data

import android.os.Parcel
import android.os.Parcelable
import androidx.versionedparcelable.ParcelField
import com.google.gson.annotations.SerializedName
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import kotlinx.android.parcel.Parcelize

@Parcelize
data class UserData(
    @Json(name = "logo-url") val logoUrl: String,
    @Json(name = "heading-text") val headingText: String,
    @Json(name = "uidata") var uiData: List<UiData>
):Parcelable