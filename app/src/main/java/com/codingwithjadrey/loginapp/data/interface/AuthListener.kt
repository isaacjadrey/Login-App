package com.codingwithjadrey.loginapp.data.`interface`

interface AuthListener {
    fun onStarted()
    fun onSuccess()
    fun onFailure(message: String)
}