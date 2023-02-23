package com.nrup.mygitactionsdemo

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.nrup.mygitactionsdemo.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.tvBuildInfo.text = """
                Build Type ${BuildConfig.BUILD_TYPE}
                BASE_URL ${BuildConfig.BASE_URL}
                
            """.trimIndent()

    }
}