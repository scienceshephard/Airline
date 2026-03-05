package com.airline.data

import com.google.firebase.auth.FirebaseAuth

class FirebaseManager {
    private val auth: FirebaseAuth = FirebaseAuth.getInstance()

    fun signUp(email: String, pass: String, callback: (Boolean) -> Unit) {
        auth.createUserWithEmailAndPassword(email, pass)
            .addOnCompleteListener { task ->
                callback(task.isSuccessful)
            }
    }

    fun signIn(email: String, pass: String, callback: (Boolean) -> Unit) {
        auth.signInWithEmailAndPassword(email, pass)
            .addOnCompleteListener { task ->
                callback(task.isSuccessful)
            }
    }

    fun isUserSignedIn(): Boolean {
        return auth.currentUser != null
    }

    fun signOut() {
        auth.signOut()
    }
}