package com.nrup.mygitactionsdemo

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.viewbinding.BuildConfig
import com.nrup.mygitactionsdemo.BuildConfig.BASE_URL
import com.nrup.mygitactionsdemo.BuildConfig.BUILD_TYPE
import com.nrup.mygitactionsdemo.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.tvBuildInfo.text = """
                Build Type $BUILD_TYPE
                BASE_URL $BASE_URL
                
            """.trimIndent()

        changeColorBasedOnFlavor()

    }

    private fun changeColorBasedOnFlavor() {
        when (BuildConfig.BUILD_TYPE) {
            "debug" -> {
                binding.clBg.setBackgroundColor(
                    ContextCompat.getColor(
                        this@MainActivity,
                        android.R.color.holo_orange_light
                    )
                )
            }
            "qa" -> {
                binding.clBg.setBackgroundColor(
                    ContextCompat.getColor(
                        this@MainActivity,
                        android.R.color.holo_blue_light
                    )
                )
            }
            else -> {
                binding.clBg.setBackgroundColor(
                    ContextCompat.getColor(
                        this@MainActivity,
                        android.R.color.holo_green_light
                    )
                )
            }
        }
    }
}