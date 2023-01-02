package com.example.cfttestapp.screens

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.example.cfttestapp.Constants
import com.example.cfttestapp.R
import com.example.cfttestapp.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnCheck.setOnClickListener {
            if (binding.etCardNum.text.isEmpty()) {
                Toast.makeText(this, "Enter card number!", Toast.LENGTH_SHORT).show()
            } else {
                val intent = Intent(this@MainActivity, ResultActivity::class.java)
                intent.putExtra(Constants.ET_CARD_NUMBER_KEY, binding.etCardNum.text.toString())
                startActivity(intent)
            }
        }
    }
}