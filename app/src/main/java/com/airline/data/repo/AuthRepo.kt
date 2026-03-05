package com.airline.data.repo

import com.airline.data.FirebaseManager

class AuthRepo {
    private val firebaseManager = FirebaseManager()

    fun signUp(email: String, pass: String, callback: (Boolean) -> Unit) {
        firebaseManager.signUp(email, pass, callback)
    }

    fun signIn(email: String, pass: String, callback: (Boolean) -> Unit) {
        firebaseManager.signIn(email, pass, callback)
    }

    fun isUserSignedIn(): Boolean {
        return firebaseManager.isUserSignedIn()
    }

    fun signOut() {
        firebaseManager.signOut()
    }
}