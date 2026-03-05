package com.airline.auth

import android.content.Context
import android.util.Log
import android.widget.Toast
import androidx.compose.ui.platform.LocalContext
import androidx.credentials.ClearCredentialStateRequest
import androidx.credentials.CredentialManager
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.airline.data.model.User
import com.google.android.gms.auth.api.signin.GoogleSignInAccount

import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.GoogleAuthProvider
import com.google.firebase.auth.UserProfileChangeRequest
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.ktx.Firebase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.tasks.await


class Authentication: ViewModel() {
    private val auth: FirebaseAuth= FirebaseAuth.getInstance()
    private val firestore: FirebaseFirestore = Firebase.firestore

    private val _authState = MutableStateFlow<AuthState>(AuthState.Idle)
    val authState: StateFlow<AuthState> = _authState

    private val _isUserLoggedIn = MutableStateFlow(false)
    val isUserLoggedIn: StateFlow<Boolean> = _isUserLoggedIn

    private val _currentUser = MutableStateFlow<User?>(null)
    val currentUser: StateFlow<User?> = _currentUser


    init {
        checkCurrentUser()
    }

    private fun checkCurrentUser() {
        val firebaseUser = auth.currentUser
        if(firebaseUser != null){
            _isUserLoggedIn.value = true
            _currentUser.value = User(
                uid = firebaseUser.uid,
                email = firebaseUser.email?: "",
                photoUrl = firebaseUser.photoUrl.toString(),
                username = firebaseUser.displayName?: ""
            )
            _authState.value = AuthState.Success(_currentUser.value!!)
        }else{
            _isUserLoggedIn.value = false
            _currentUser.value = null
        }
    }

    fun SignInWithEmail(email: String, password: String){
        if(email.isBlank() || password.isBlank()){
            _authState.value = AuthState.Error("Email  and password cannot be empty")
            return
        }
        viewModelScope.launch{
            try {
                _authState.value = AuthState.Loading
                val result = auth
                    .signInWithEmailAndPassword(email, password)
                    .addOnCompleteListener{ task ->
                    if(task.isSuccessful){
                        Log.d("TAG","signInWithEmail:success")
                        val user = auth.currentUser
                    }else{
                        Log.w("TAG", "signInWithEmail:failure", task.exception)
                        Toast.makeText(
                            null,
                            "Authentication failed!!!!",
                            Toast.LENGTH_SHORT
                        ).show()
                    }
                }.await()

                val firebaseUser = result.user
                if (firebaseUser != null){
                    val user = User(
                        uid = firebaseUser.uid,
                        email = firebaseUser.email?: "",
                        username = firebaseUser.displayName?: "",
                        photoUrl = firebaseUser.photoUrl.toString()
                    )
                    _currentUser.value = user
                    _isUserLoggedIn.value = true
                    _authState.value = AuthState.Success(user)
                }else{
                    _authState.value = AuthState.Error("Sign in failed!!!")
                }
            }catch (e: Exception){
                _authState.value = AuthState.Error(e.message?: "Sign in failed!!!")
            }
        }
    }

    fun registerWithEmail(email: String, password: String, displayName: String){
        if(email.isBlank() || password.isBlank() || displayName.isBlank()){
            _authState.value = AuthState.Error("All fields are required")
            return
        }
        if(password.length < 8){
            _authState.value = AuthState.Error("Password must be at least 8 characters")
            return
        }
        viewModelScope.launch {
            try {
                _authState.value = AuthState.Loading
                val result = auth
                    .createUserWithEmailAndPassword(email, password)
                    .addOnCompleteListener {task ->
                    if(task.isSuccessful){
                        Log.d("TAG","signInWithEmail:success")
                        val user = auth.currentUser
                    }else{
                        Log.w("TAG", "signInWithEmail:failure", task.exception)
                        Toast.makeText(
                            null,
                            "Authentication failed!!!!",
                            Toast.LENGTH_SHORT
                        ).show()
                    }
                }.await()
                val firebaseUser = result.user
                if(firebaseUser != null){
                    val profileUpdates = UserProfileChangeRequest.Builder()
                        .setDisplayName(displayName)
                        .build()
                    firebaseUser.updateProfile(profileUpdates).await()

                    val user = User(
                        uid = firebaseUser.uid,
                        email = email,
                        username = displayName,
                        photoUrl = ""
                    )
                    saveUserToFirestore(user)

                    _currentUser.value = user
                    _isUserLoggedIn.value = true
                    _authState.value = AuthState.Success(user)
                }else{
                    _authState.value = AuthState.Error("Registration failed!!!!")
                }
            } catch (e: Exception){
                _authState.value = AuthState.Error(e.message ?: "Registration failed!!!")
            }
        }
    }
    fun signInWithGoogle(idToken: String){
        viewModelScope.launch {
            try {
                _authState.value = AuthState.Loading
                val credential = GoogleAuthProvider.getCredential(idToken, null)
                val authResult = auth.signInWithCredential(credential).await()
                val user = authResult.user
                _currentUser.value = user?.let {
                    user.email?.let { email ->
                        User(
                            uid = it.uid,
                            username = user.displayName?: "",
                            email = email,
                            photoUrl = user.photoUrl.toString()
                        )
                    }
                }
                if(user != null){
                    Log.d("AUTH", "GoogleSignIn successfully: ${user.displayName}")
                    _authState.value = AuthState.Success(_currentUser.value!!)
                }
            }catch(e: Exception){
                Log.d("AUTH", "GoogleSignIn Failed", e)
                _authState.value = AuthState.Error(e.message?: "Authentication Failed!!!")
            }
        }
    }
    fun saveUserToFirestore(user: User){
        viewModelScope.launch {
            try {
                firestore.collection("users")
                    .document(user.uid)
                    .set(user)
                    .await()
            }catch (e: Exception){
                e.printStackTrace()
            }
        }
    }
    fun signOut(context: Context){
        viewModelScope.launch {
            try {
                auth.signOut()
                val credentialManager = CredentialManager.create(context)
                credentialManager.clearCredentialState(ClearCredentialStateRequest())

                _currentUser.value = null
                _isUserLoggedIn.value = false
                _authState.value = AuthState.Idle
                Log.d("AUTH", "User signed out successfully")
            }catch (e: Exception){
                Log.e("AUTH", "Error clearng the credential state", e)
            }
        }
    }

}