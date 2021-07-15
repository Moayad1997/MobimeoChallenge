package com.apps.mobimeochallenge.ui.details

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.apps.mobimeochallenge.databinding.ActivityGifDetailsBinding


class GifDetailsActivity : AppCompatActivity() {


    private lateinit var gifDetailsBinding: ActivityGifDetailsBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        if (::gifDetailsBinding.isInitialized.not()) {
            gifDetailsBinding = ActivityGifDetailsBinding.inflate(layoutInflater)
        }

        setContentView(gifDetailsBinding.root)

        gifDetailsBinding.gifUrl = intent.getStringExtra("URL")!!

        gifDetailsBinding.closeIcon.setOnClickListener {
            onBackPressed()
        }

        gifDetailsBinding.executePendingBindings()

    }

}