package com.example.android.daysofwellness.model

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes

data class Day(
    @StringRes val dayName: Int,
    @StringRes val dayMainDescription: Int,
    @StringRes val daySubDescription: Int,
    @DrawableRes val dayImageResource: Int
)
