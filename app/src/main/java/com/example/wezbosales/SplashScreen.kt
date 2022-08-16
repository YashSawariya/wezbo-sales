package com.example.wezbosales

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.view.WindowManager
import com.example.wezbosales.databinding.ActivitySplashScreenBinding

class SplashScreen : AppCompatActivity() {

    lateinit var binding:ActivitySplashScreenBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding=ActivitySplashScreenBinding.inflate(layoutInflater)
        setContentView(binding.root)

        //full screen
        supportActionBar?.hide()
        window.setFlags(
            WindowManager.LayoutParams.FLAG_FULLSCREEN,
            WindowManager.LayoutParams.FLAG_FULLSCREEN
        )

        Handler().postDelayed({
            var intent= Intent(this,LoginActivity::class.java)
            startActivity(intent)
            finish()
        },3000)

    }
}