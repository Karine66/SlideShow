package com.karine.slideshow

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.CountDownTimer
import android.util.Log
import android.view.View
import com.karine.slideshow.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

private lateinit var mainBinding: ActivityMainBinding

val photoList = arrayOf(R.drawable.chat1, R.drawable.chat2, R.drawable.chat3, R.drawable.chien1,
R.drawable.chien2, R.drawable.chien3)
var currentPhotoIndex = 0
    var countDownTimer:CountDownTimer? = null

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

    fun previousButtonTouched(button : View) {
        stopSlideShow()
        currentPhotoIndex = currentPhotoIndex - 1
        if(currentPhotoIndex < 0) {
            currentPhotoIndex = photoList.size -1
        }
        mainBinding.imageView.setImageResource(photoList[currentPhotoIndex])
        startSlideShow()
    }

    fun nextButtonTouched(button : View) {
        stopSlideShow()
      showNextPhoto()
        startSlideShow()
    }

    fun stopSlideShow() {
        countDownTimer?.cancel()
        countDownTimer = null
    }

    private fun startSlideShow() {
        countDownTimer = object : CountDownTimer(3000,3000){
            override fun onFinish() {
               showNextPhoto()
                startSlideShow()
            }
            override fun onTick(millisUntilFinished: Long) {}
        }
        countDownTimer?.start()
    }

    fun showNextPhoto() {
        currentPhotoIndex = currentPhotoIndex + 1
        if(currentPhotoIndex >= photoList.size) {
            currentPhotoIndex = 0
        }
        mainBinding.imageView.setImageResource(photoList[currentPhotoIndex])
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putInt("currentPhotoIndex", currentPhotoIndex)
    }

    override fun onRestoreInstanceState(savedInstanceState: Bundle) {
        super.onRestoreInstanceState(savedInstanceState)
        currentPhotoIndex = savedInstanceState.getInt("currentPhotoIndex")
        mainBinding.imageView.setImageResource(photoList[currentPhotoIndex])
    }
}