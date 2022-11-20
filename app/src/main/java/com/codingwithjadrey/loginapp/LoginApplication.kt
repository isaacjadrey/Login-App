package com.codingwithjadrey.loginapp

import android.app.Application
import com.codingwithjadrey.loginapp.data.repository.UserRepository
import com.codingwithjadrey.loginapp.viewmodel.AuthViewModelFactory

class LoginApplication : Application() {
    companion object {
        private val userRepository = UserRepository()

        val authViewModelFactory = AuthViewModelFactory(userRepository)
    }
}