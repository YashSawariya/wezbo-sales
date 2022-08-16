package com.example.wezbosales

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.view.KeyEvent
import android.view.MenuItem
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.example.wezbosales.databinding.ActivityUserBinding

class UserActivity : AppCompatActivity() {

    lateinit var binding:ActivityUserBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding=ActivityUserBinding.inflate(layoutInflater)
        setContentView(binding.root)

        //logout button
        binding.lout.setOnClickListener {
            binding.pro2.visibility= View.VISIBLE
            Handler().postDelayed({

                //go to LoginActivity
                val intent = Intent(this,LoginActivity::class.java)
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_NEW_TASK)// not go to back
                startActivity(intent)

            },2000)

        }

        //button on id card
        binding.idcard.setOnClickListener {
            //go to id_card_activity2
            val intent=Intent(this,IdCardActivity2::class.java)
            startActivity(intent)
        }

        //show back button on action bar
        getSupportActionBar()?.setDisplayHomeAsUpEnabled(true);
        getSupportActionBar()?.setDisplayShowHomeEnabled(true);

    }

    //only action bar back button works
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if(item.itemId== android.R.id.home){
            val intent=Intent(this,MainActivity::class.java)
            startActivity(intent)
            return true
        }
        return super.onOptionsItemSelected(item)
    }

    //not to work down back bitton
    override fun onKeyDown(keyCode: Int, event: KeyEvent?): Boolean {
//        //replaces the default 'Back' button action
//        if (keyCode == KeyEvent.KEYCODE_BACK) {
//// something here
//            finish()
//        }
        return false
    }

}