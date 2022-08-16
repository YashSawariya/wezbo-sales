package com.example.wezbosales

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.wezbosales.databinding.ActivityIdCard2Binding

class IdCardActivity2 : AppCompatActivity() {

    lateinit var binding:ActivityIdCard2Binding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding=ActivityIdCard2Binding.inflate(layoutInflater)
        setContentView(binding.root)

        //set title
        supportActionBar?.setTitle("ID Card")

        //show back button on action bar
        getSupportActionBar()?.setDisplayHomeAsUpEnabled(true);
        getSupportActionBar()?.setDisplayShowHomeEnabled(true);
    }

    //give functionality to back button on action bar
    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }

}