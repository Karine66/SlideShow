package com.karine.slideshow.utils

import android.content.Context

class LocalPreferences (context: Context){

    val sharedPreferences = androidx.preference.PreferenceManager.getDefaultSharedPreferences(context)

    var slideShowDelay : Int
    get() {
        return sharedPreferences.getInt("slideShowDelay", 3)
    }
    set(value) {
        sharedPreferences.edit().putInt("slideShowDelay", value).apply()
    }
}