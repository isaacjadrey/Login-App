package com.codingwithjadrey.loginapp.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.codingwithjadrey.loginapp.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }
}