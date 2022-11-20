package com.codingwithjadrey.loginapp.data.interf

interface AuthListener {
    fun onStarted()
    fun onSuccess()
    fun onFailure(message: String)
}