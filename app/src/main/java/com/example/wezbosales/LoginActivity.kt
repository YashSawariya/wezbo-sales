package com.example.wezbosales

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.view.View
import android.view.WindowManager
import com.example.wezbosales.databinding.ActivityLoginBinding

class LoginActivity : AppCompatActivity() {

    lateinit var binding:ActivityLoginBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding=ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        //full screen
        supportActionBar?.hide()
        window.setFlags(
            WindowManager.LayoutParams.FLAG_FULLSCREEN,
            WindowManager.LayoutParams.FLAG_FULLSCREEN
        )

        //login button
        binding.b1.setOnClickListener {

            binding.progress.visibility=View.VISIBLE
            binding.b1.visibility=View.GONE

            Handler().postDelayed({

                binding.progress.visibility=View.GONE
                binding.b1.visibility=View.VISIBLE

                var intent= Intent(this,MainActivity::class.java)
                startActivity(intent)
                finish()
            },3000)
        }
    }
}