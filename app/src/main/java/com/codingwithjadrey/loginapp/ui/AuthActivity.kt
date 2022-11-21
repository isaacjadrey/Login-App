package com.codingwithjadrey.loginapp.ui

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.codingwithjadrey.loginapp.LoginApplication
import com.codingwithjadrey.loginapp.databinding.ActivityAuthBinding
import com.codingwithjadrey.loginapp.viewmodel.AuthViewModel

class AuthActivity : AppCompatActivity() {

    private lateinit var binding: ActivityAuthBinding
    private val authViewModel: AuthViewModel by lazy {
        ViewModelProvider(this, LoginApplication.authViewModelFactory)[AuthViewModel::class.java]
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAuthBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }

    override fun onStart() {
        super.onStart()
        authViewModel.currentUser?.let {
            startActivity(Intent(this, MainActivity::class.java))
            finish()
        }
    }
}