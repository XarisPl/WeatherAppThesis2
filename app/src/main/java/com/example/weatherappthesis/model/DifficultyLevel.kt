package com.example.weatherappthesis.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
enum class DifficultyLevel(val title: String) : Parcelable {
    EASY("Easy"), MODERATE("Moderate"), ADVANCED("Advanced"), EXTREME("Extreme")
}