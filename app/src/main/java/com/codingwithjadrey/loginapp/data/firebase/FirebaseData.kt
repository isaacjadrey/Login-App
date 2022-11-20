package com.codingwithjadrey.loginapp.data.firebase

import androidx.lifecycle.MutableLiveData
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.firestore.FirebaseFirestore
import io.reactivex.rxjava3.core.Completable

class FirebaseData {

    private val firebaseUserLD: MutableLiveData<FirebaseUser> by lazy { MutableLiveData() }
    private val firebaseAuth: FirebaseAuth by lazy { FirebaseAuth.getInstance() }
    private val fStore: FirebaseFirestore by lazy { FirebaseFirestore.getInstance() }

    fun login(email: String, password: String): Completable = Completable.create { emitter ->
        firebaseAuth.signInWithEmailAndPassword(email, password).addOnCompleteListener {
            if (!emitter.isDisposed) {
                if (it.isSuccessful) {
                    firebaseUserLD.postValue(firebaseAuth.currentUser)
                    emitter.onComplete()
                } else emitter.onError(it.exception!!)
            }
        }
    }

    fun signup(email: String, password: String): Completable =
        Completable.create { emitter ->
            firebaseAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener {
                    if (!emitter.isDisposed) {
                        if (it.isSuccessful) {
                            firebaseUserLD.postValue(firebaseAuth.currentUser)
                            emitter.onComplete()
                        } else emitter.onError(it.exception!!)
                    }
                }
        }

    fun currentUser() = firebaseAuth.currentUser

    fun logout() {
        firebaseAuth.signOut()
        firebaseUserLD.postValue(null)
    }
}