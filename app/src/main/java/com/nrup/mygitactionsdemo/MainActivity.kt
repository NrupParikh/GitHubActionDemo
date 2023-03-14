package com.nrup.mygitactionsdemo

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import com.nrup.mygitactionsdemo.BuildConfig.*
import com.nrup.mygitactionsdemo.databinding.ActivityMainBinding
import java.util.*

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding


    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.tvBuildInfo.text = """
           
                Build Type : $BUILD_TYPE
                
                BASE_URL
                $BASE_URL
                
                Version Code : $VERSION_CODE
                Version Name : $VERSION_NAME
                
            """.trimIndent()

        changeColorBasedOnFlavor()

        var myText = "asdf"


    }

    private fun changeColorBasedOnFlavor() {
        when (BUILD_TYPE) {
            "debug" -> {
                binding.clBg.setBackgroundColor(
                    ContextCompat.getColor(
                        this@MainActivity,
                        android.R.color.holo_blue_light
                    )
                )
            }
            else -> {
                // Release build
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