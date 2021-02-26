package com.example.kobers.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.kobers.R
import com.example.kobers.activities.stock.InputStockActivity
import kotlinx.android.synthetic.main.activity_home.*

class HomeActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        linearLayout2.setOnClickListener {
            val intent = Intent(this@HomeActivity, InputStockActivity::class.java)
            startActivity(intent)
        }

    }

}