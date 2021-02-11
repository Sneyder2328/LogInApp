package com.sneyder.loginapp.data.repository

import androidx.lifecycle.MutableLiveData
import com.google.firebase.auth.AuthCredential
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.sneyder.loginapp.data.model.User
import com.sneyder.loginapp.utils.logError
import javax.inject.Inject
import javax.inject.Singleton
import com.sneyder.loginapp.data.model.Result


@Singleton
class AppUserRepository
@Inject constructor(
    private val firebaseAuth: FirebaseAuth
) : UserRepository() {

    override fun firebaseSignInWithGoogle(authCredential: AuthCredential): MutableLiveData<Result<User>> {
        val authenticatedUserMutableLiveData: MutableLiveData<Result<User>> = MutableLiveData()
        authenticatedUserMutableLiveData.value = Result()
        firebaseAuth.signInWithCredential(authCredential).addOnCompleteListener { authTask ->
            if (authTask.isSuccessful) {
                authenticatedUserMutableLiveData.value = Result(getFirebaseCurrentUser())
            } else {
                authenticatedUserMutableLiveData.value = Result(error = authTask.exception)
                logError(authTask.exception)
            }
        }
        return authenticatedUserMutableLiveData
    }

    private fun getFirebaseCurrentUser(): User? {
        val firebaseUser: FirebaseUser? = firebaseAuth.currentUser
        if (firebaseUser != null) {
            val uid = firebaseUser.uid
            val name = firebaseUser.displayName ?: ""
            val email = firebaseUser.email ?: ""
            return User(uid, name, email)
        }
        return null
    }

    override fun firebaseSignInWithEmail(
        email: String,
        password: String
    ): MutableLiveData<Result<User>> {
        val authenticatedUserMutableLiveData: MutableLiveData<Result<User>> = MutableLiveData()
        authenticatedUserMutableLiveData.value = Result()
        firebaseAuth.signInWithEmailAndPassword(email, password)
            .addOnCompleteListener { authTask ->
                if (authTask.isSuccessful) {
                    authenticatedUserMutableLiveData.value = Result(getFirebaseCurrentUser())
                } else {
                    authenticatedUserMutableLiveData.value = Result(error = authTask.exception)
                    logError(authTask.exception)
                }
            }
        return authenticatedUserMutableLiveData
    }

}