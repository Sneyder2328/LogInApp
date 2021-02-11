package com.sneyder.loginapp.data.repository

import androidx.lifecycle.MutableLiveData
import com.google.firebase.auth.AuthCredential
import com.sneyder.loginapp.data.model.User
import com.sneyder.loginapp.data.model.Result

abstract class UserRepository {

    abstract fun firebaseSignInWithGoogle(authCredential: AuthCredential): MutableLiveData<Result<User>>

    abstract fun firebaseSignInWithEmail(email: String, password: String): MutableLiveData<Result<User>>
//    abstract fun getCurrentUserProfile(): UserProfile?
//
//    abstract suspend fun addModerator(email: String): Result<Boolean>
//    abstract suspend fun fetchModerators(): Result<List<UserProfile>>
//    abstract suspend fun fetchUserProfile(userId: String): Result<UserProfile>
//    abstract suspend fun signUp(request: SignUpRequest): Result<UserProfile>
//    abstract suspend fun logIn(request: LogInRequest): Result<UserProfile>
//    abstract suspend fun logOut(): Result<LogOutResponse>

}