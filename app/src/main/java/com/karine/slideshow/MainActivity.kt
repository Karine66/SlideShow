package com.karine.slideshow

import android.content.Intent
import android.os.Bundle
import android.os.CountDownTimer
import android.view.Menu
import android.view.MenuItem
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.karine.slideshow.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var mainBinding: ActivityMainBinding

    val photoList = arrayOf(R.drawable.chat1, R.drawable.chat2, R.drawable.chat3, R.drawable.chien1,
            R.drawable.chien2, R.drawable.chien3)
    var currentPhotoIndex = 0
    var countDownTimer: CountDownTimer? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mainBinding = ActivityMainBinding.inflate(layoutInflater)
        val view = mainBinding.root
        setContentView(view)
    }

    override fun onResume() {
        super.onResume()
        startSlideShow()
    }

    override fun onStop() {
        super.onStop()
        stopSlideShow()
    }
    // for previous buttton
    fun previousButtonTouched(button: View) {
        stopSlideShow()
        currentPhotoIndex = currentPhotoIndex - 1
        if (currentPhotoIndex < 0) {
            currentPhotoIndex = photoList.size - 1
        }
        mainBinding.imageView.setImageResource(photoList[currentPhotoIndex])
        startSlideShow()
    }
    //for next button
    fun nextButtonTouched(button: View) {
        stopSlideShow()
        showNextPhoto()
        startSlideShow()
    }
    // for stop slide show
    fun stopSlideShow() {
        countDownTimer?.cancel()
        countDownTimer = null
    }
    //for start slide show
    private fun startSlideShow() {
        val delay = (LocalPreferences(this).slideShowDelay * 1000).toLong()
        countDownTimer = object : CountDownTimer(delay, delay) {
            override fun onFinish() {
                showNextPhoto()
                startSlideShow()
            }

            override fun onTick(millisUntilFinished: Long) {}
        }
        countDownTimer?.start()
    }
    // for show photo
    fun showNextPhoto() {
        currentPhotoIndex = currentPhotoIndex + 1
        if (currentPhotoIndex >= photoList.size) {
            currentPhotoIndex = 0
        }
        mainBinding.imageView.setImageResource(photoList[currentPhotoIndex])
    }
    // for save photo
    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putInt("currentPhotoIndex", currentPhotoIndex)
    }
    //for retrieve photo
    override fun onRestoreInstanceState(savedInstanceState: Bundle) {
        super.onRestoreInstanceState(savedInstanceState)
        currentPhotoIndex = savedInstanceState.getInt("currentPhotoIndex")
        mainBinding.imageView.setImageResource(photoList[currentPhotoIndex])
    }
    //for settings menu
    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.main_menu, menu)
        return super.onCreateOptionsMenu(menu)
    }
    //for click on settings button
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if(item != null) {
            when(item.itemId) {
                R.id.setttings -> displaySettings()
            }
        }
        return super.onOptionsItemSelected(item)
    }

    private fun displaySettings() {
       val intent = Intent(this, SettingsActivity::class.java)
        startActivity(intent)
    }
}