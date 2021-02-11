package com.sneyder.loginapp.ui.login

import androidx.lifecycle.LiveData
import com.google.firebase.auth.AuthCredential
import com.sneyder.loginapp.data.model.User
import com.sneyder.loginapp.data.repository.UserRepository
import com.sneyder.loginapp.utils.base.BaseViewModel
import javax.inject.Inject
import com.sneyder.loginapp.data.model.Result

class LogInViewModel
@Inject constructor(
    private val userRepository: UserRepository
) : BaseViewModel() {

    fun signInWithGoogleAuth(authCredential: AuthCredential): LiveData<Result<User>> {
        return userRepository.firebaseSignInWithGoogle(authCredential)
    }

    fun signInWithEmail(email: String, password: String): LiveData<Result<User>> {
        return userRepository.firebaseSignInWithEmail(email, password)
    }

}