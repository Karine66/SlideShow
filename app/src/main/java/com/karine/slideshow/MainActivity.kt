package com.karine.slideshow

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import com.karine.slideshow.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

private lateinit var mainBinding: ActivityMainBinding

val photoList = arrayOf(R.drawable.chat1, R.drawable.chat2, R.drawable.chat3, R.drawable.chien1,
R.drawable.chien2, R.drawable.chien3)
var currentPhotoIndex = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mainBinding = ActivityMainBinding.inflate(layoutInflater)
        val view = mainBinding.root
        setContentView(view)
    }

    fun previousButtonTouched(button : View) {
        currentPhotoIndex = currentPhotoIndex - 1
        if(currentPhotoIndex < 0) {
            currentPhotoIndex = photoList.size -1
        }
        mainBinding.imageView.setImageResource(photoList[currentPhotoIndex])
    }

    fun nextButtonTouched(button : View) {
      currentPhotoIndex = currentPhotoIndex + 1
        if(currentPhotoIndex >= photoList.size) {
            currentPhotoIndex = 0
        }
        mainBinding.imageView.setImageResource(photoList[currentPhotoIndex])
    }
}