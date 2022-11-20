package com.codingwithjadrey.loginapp.ui

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.codingwithjadrey.loginapp.LoginApplication
import com.codingwithjadrey.loginapp.databinding.ActivityMainBinding
import com.codingwithjadrey.loginapp.viewmodel.AuthViewModel

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private val authViewModel: AuthViewModel by lazy {
        ViewModelProvider(this, LoginApplication.authViewModelFactory)[AuthViewModel::class.java]
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.logoutBtn.setOnClickListener {
            authViewModel.logout()
            startActivity(Intent(this, AuthActivity::class.java))
            finish()
        }
    }

}