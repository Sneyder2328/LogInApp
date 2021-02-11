package com.sneyder.loginapp.ui.login

import android.app.ProgressDialog
import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import androidx.lifecycle.LiveData
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.common.api.ApiException
import com.google.firebase.auth.GoogleAuthProvider
import com.sneyder.loginapp.R
import com.sneyder.loginapp.data.model.Result
import com.sneyder.loginapp.data.model.User
import com.sneyder.loginapp.ui.main.MainActivity
import com.sneyder.loginapp.utils.FieldsValidator
import com.sneyder.loginapp.utils.InputValidator
import com.sneyder.loginapp.utils.base.DaggerActivity
import com.sneyder.loginapp.utils.debug
import com.sneyder.loginapp.utils.logError
import kotlinx.android.synthetic.main.activity_log_in.*


class LogInActivity : DaggerActivity() {

    companion object {
        const val REQUEST_SIGN_IN = 101
    }

    private lateinit var googleSignInClient: GoogleSignInClient
    private val viewModel: LogInViewModel by viewModels { viewModelFactory }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_log_in)
        setUpGoogleLogin()
        logInButton.setOnClickListener { logInWithEmail() }
    }


    private var progressDialog: ProgressDialog? = null


    private fun observeUser(userLiveData: LiveData<Result<User>>){
        debug("observeUser")
        userLiveData.observe(this) {
            debug("observeUserLoggedIn $it")
            when {
                it.isLoading -> {
                    progressDialog = ProgressDialog(this)
                    progressDialog?.setCancelable(false)
                    progressDialog?.setMessage("Iniciando sesión...")
                    progressDialog?.show()
                }
                it.success != null -> {
                    if (progressDialog?.isShowing == true) progressDialog?.dismiss()
                    startActivity(MainActivity.starterIntent(this))
                    finish()
                }
                it.error != null -> {
                    if (progressDialog?.isShowing == true) progressDialog?.dismiss()
                    Toast.makeText(this, "Hubo un error iniciando sesión", Toast.LENGTH_LONG).show()
                    it.error.printStackTrace()
                }
            }
        }
    }

    private fun logInWithEmail() {
        val fieldsValidator = FieldsValidator()
        val email = InputValidator.Builder(emailInput)
            .maxLength(255)
            .required(message = "Por favor ingresar su correo electronico")
            .build(fieldsValidator)
        val password = InputValidator.Builder(passwordInput)
            .required(message = "Por favor ingresar su contraseña")
            .build(fieldsValidator)
        if (!fieldsValidator.allInputsValid()) return
        val signInWithEmail = viewModel.signInWithEmail(email, password)
        observeUser(signInWithEmail)
    }

    private fun setUpGoogleLogin() {
        val gso = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
            .requestIdToken(getString(R.string.server_client_id))
            .requestEmail()
            .build()

        googleSignInClient = GoogleSignIn.getClient(this, gso)
        logInGoogleButton?.setOnClickListener {
            startActivityForResult(googleSignInClient.signInIntent, REQUEST_SIGN_IN)
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == REQUEST_SIGN_IN) {
            val task = GoogleSignIn.getSignedInAccountFromIntent(data)
            try {
                val googleSignInAccount = task.getResult(ApiException::class.java)
                googleSignInAccount?.let { getGoogleAuthCredential(it) }
            } catch (e: ApiException) {
                e.printStackTrace()
                logError(e)
            }
        }
    }

    private fun getGoogleAuthCredential(googleSignInAccount: GoogleSignInAccount) {
        val googleTokenId = googleSignInAccount.idToken
        val googleAuthCredential = GoogleAuthProvider.getCredential(googleTokenId, null)
        val userLiveData = viewModel.signInWithGoogleAuth(googleAuthCredential)
        observeUser(userLiveData)
    }

}