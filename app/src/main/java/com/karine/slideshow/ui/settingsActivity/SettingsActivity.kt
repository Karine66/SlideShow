package com.karine.slideshow.ui.settingsActivity

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.SeekBar
import com.karine.slideshow.utils.LocalPreferences
import com.karine.slideshow.databinding.ActivitySettingsBinding

class SettingsActivity : AppCompatActivity(), SeekBar.OnSeekBarChangeListener {

    private lateinit var settingsBinding: ActivitySettingsBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        settingsBinding = ActivitySettingsBinding.inflate(layoutInflater)
        val view = settingsBinding.root
        setContentView(view)
        settingsBinding.delaySeekBar.setOnSeekBarChangeListener(this)
    }

    override fun onStart() {
        super.onStart()
        settingsBinding.delaySeekBar.progress = LocalPreferences(this).slideShowDelay
    }

    @SuppressLint("SetTextI18n")
    override fun onProgressChanged(seekBar: SeekBar?, progress: Int, fromUser: Boolean) {
        settingsBinding.delayText.text = "$progress s"
        LocalPreferences(this).slideShowDelay = progress
    }

    override fun onStartTrackingTouch(seekBar: SeekBar?) {

    }

    override fun onStopTrackingTouch(seekBar: SeekBar?) {

    }
}