package com.codingwithjadrey.loginapp.data.repository

import com.codingwithjadrey.loginapp.data.firebase.FirebaseData

class UserRepository() {

    private val repository: FirebaseData by lazy { FirebaseData() }

    fun login(email: String, password: String) = repository.login(email, password)

    fun signup(email: String, password: String) = repository.signup(email, password)

    fun currentUser() = repository.currentUser()

    fun logout() = repository.logout()
}