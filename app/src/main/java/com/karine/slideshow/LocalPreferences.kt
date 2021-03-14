package com.karine.slideshow

import android.content.Context
import android.preference.PreferenceManager

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