package com.example.textview

import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.example.textview.databinding.ActivityMainBinding


class MainActivity : AppCompatActivity() {

    // Using binding as an easier way to work with views
    private lateinit var bindingMain: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        bindingMain = ActivityMainBinding.inflate(layoutInflater)
        setContentView(bindingMain.root)

        // View declarations
        val imageView = bindingMain.imageView
        val btChangeImg = bindingMain.btChangeImg
        val progressBar = bindingMain.progressBar
        val tvMain = bindingMain.tvMain

        val imageIDs = listOf(
            R.drawable.cat1,
            R.drawable.cat2,
            R.drawable.cat3,
            R.drawable.cat4,
            R.drawable.cat5,
            R.drawable.cat6,
            R.drawable.cat7
        )
        // Required declarations
        var currentCatImgID = R.drawable.cat1
        val shownPictures = mutableSetOf(R.drawable.cat1)

        // Button click listener
        btChangeImg.setOnClickListener {

            val randomColor = "#" + getRandomString()

            // getting random picture except current
            currentCatImgID = imageIDs.filter {it != currentCatImgID}.random()
            imageView.setImageResource(currentCatImgID)

            // Changing colors
            btChangeImg.setBackgroundColor(Color.parseColor(randomColor))
            progressBar.setBackgroundColor(Color.parseColor(randomColor))

            // Progress bar stuff
            if (currentCatImgID !in shownPictures) {
                progressBar.progress += 15
            }
            shownPictures.add(currentCatImgID)
            if (progressBar.progress >= 100) {
                tvMain.text = "You`ve found all of them!"
            }
        }
    }

    // A function returning a random Hex
    private fun getRandomString() : String {
        val allowedChars = ('A'..'F') + ('0'..'9')
        return (1..6)
            .map { allowedChars.random() }
            .joinToString("")
    }
}
