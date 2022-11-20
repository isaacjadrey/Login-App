package com.codingwithjadrey.loginapp.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.codingwithjadrey.loginapp.R
import com.codingwithjadrey.loginapp.databinding.ActivityAuthBinding

class AuthActivity : AppCompatActivity() {

    private lateinit var binding: ActivityAuthBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAuthBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }
}