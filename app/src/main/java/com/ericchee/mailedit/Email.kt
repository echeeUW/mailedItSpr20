package com.ericchee.mailedit

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Email(
    val from: String,
    val content: String
): Parcelable