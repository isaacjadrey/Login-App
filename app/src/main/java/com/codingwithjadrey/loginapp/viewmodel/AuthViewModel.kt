package com.codingwithjadrey.loginapp.viewmodel

import androidx.lifecycle.ViewModel
import com.codingwithjadrey.loginapp.data.`interface`.AuthListener
import com.codingwithjadrey.loginapp.data.repository.UserRepository
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.schedulers.Schedulers

class AuthViewModel(private val repository: UserRepository) : ViewModel() {

    /** email and password for the input */
    var email: String? = null
    var password: String? = null

    /** auth listener to listen to event changes */
    var authListener: AuthListener? = null

    private val disposables = CompositeDisposable()

    /** perform the login **/
    fun login() {
        /** validate the input fields */
        if (email.isNullOrEmpty() || password.isNullOrEmpty()) {
            authListener?.onFailure("Invalid Email address or password, please retry")
            return
        }
        /** authentication started */
        authListener?.onStarted()

        /** calling login from repository to perform the actual authentication */
        val disposable = repository.login(email!!, password!!)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                /** sending a success callback */
                authListener?.onSuccess()
            }, {
                /** sending a failure callback */
                authListener?.onFailure(it.message!!)
            })
        disposables.add(disposable)
    }

    /** perform the signup **/
    fun signup() {
        if (email.isNullOrEmpty() || password.isNullOrEmpty()) {
            authListener?.onFailure("All fields are required")
            return
        }
        /** authentication started */
        authListener?.onStarted()
        /** calling signup from repository to perform the actual authentication */
        val disposable = repository.signup(email!!, password!!)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                /** sending a success callback */
                authListener?.onSuccess()
            }, {
                /** sending a failure callback */
                authListener?.onFailure(it.message!!)
            })
        disposables.add(disposable)
    }

    /**
     * Logout the current user
     */
    fun logout() {
        repository.logout()
    }

    /**
     * Disposing the disposables
     */
    override fun onCleared() {
        super.onCleared()
        disposables.dispose()
    }
}