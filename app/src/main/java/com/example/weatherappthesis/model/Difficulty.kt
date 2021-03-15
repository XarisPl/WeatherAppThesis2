package com.example.weatherappthesis.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Difficulty(
    var level: DifficultyLevel? = null,
    var messageId: Int? = null,
    var colourId: Int? = null
) : Parcelable