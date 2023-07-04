package com.hawkeye.authModule.domain.repository

import android.content.ContentValues.TAG
import android.content.Context
import android.util.Log
import android.widget.Toast
import androidx.compose.ui.platform.LocalContext
import com.google.firebase.FirebaseException
import com.google.firebase.FirebaseNetworkException
import com.google.firebase.FirebaseTooManyRequestsException
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException
import com.google.firebase.auth.FirebaseAuthInvalidUserException
import com.google.firebase.auth.FirebaseAuthUserCollisionException
import kotlinx.coroutines.tasks.await
import javax.inject.Inject

interface AuthRepository {
    suspend fun login(email: String, password:String):Boolean

    suspend fun register(email:String, password: String):Boolean

    
}
class FirebaseAuthRepository @Inject constructor() : AuthRepository {

    private val firebaseAuth: FirebaseAuth by lazy {
        FirebaseAuth.getInstance()
    }

    override suspend fun login(
        email: String,
        password: String
    ): Boolean {
        return try {
            val result = firebaseAuth.signInWithEmailAndPassword(email, password).await()
            Log.d(TAG, result.user.toString())
            result.user != null
        } catch (e: FirebaseAuthInvalidCredentialsException) {
            // Handle incorrect password exception
            Log.e(TAG, "Incorrect password: ${e.message}", e)
            ErrorHolder.errorMessage = e.message ?: "Incorrect password: ${e.message}"
            false
        } catch (e: FirebaseAuthInvalidUserException) {
            // Handle invalid user exception
            Log.e(TAG, "Invalid user: ${e.message}", e)
            ErrorHolder.errorMessage = e.message ?: "Invalid user: ${e.message}"
            false
        } catch (e: FirebaseAuthUserCollisionException) {
            // Handle login failure or exception
            Log.e(TAG, "Login failed: ${e.message}", e)
            ErrorHolder.errorMessage = e.message ?: "Login failed: ${e.message}"
            false
        } /*catch (e: FirebaseTooManyRequestsException) {
        // Handle too many requests exception
        Log.e(TAG, "Too many login attempts. Please try again later.", e)
        ErrorHolder.errorMessage = e.message ?: "Too many login attempts. Please try again later."
        false
        }*/ catch (e: FirebaseNetworkException) {
            // Network connectivity issues
            Log.e(TAG, "Network error occurred: ${e.message}")
            ErrorHolder.errorMessage = e.message ?: "Network error occurred: ${e.message}"
            false
        } catch (e: FirebaseException) {
            // Handle other registration failures or exceptions
            Log.e(TAG, "Login failed: ${e.message}", e)
            ErrorHolder.errorMessage = e.message ?: "Login failed: ${e.message}"
            false
        } catch (exception: Exception) {
            Log.e(TAG, "Login failed: ${exception.message}", exception)
            ErrorHolder.errorMessage = exception.message ?:"An unexpected error occurred: ${exception.message}"
            false
        }
    }

    override suspend fun register(
        email: String,
        password: String,
    ): Boolean {
        return try {
            val result = firebaseAuth.createUserWithEmailAndPassword(email, password).await()
            Log.d(TAG, result.user.toString())
            result.user != null
        } catch (e: FirebaseAuthUserCollisionException) {
            // Email address is already associated with an existing account
            Log.e(TAG, "Email is already in use: ${e.message}")
            ErrorHolder.errorMessage = e.message ?: "Email is already in use: ${e.message}"
            false
        } catch (e: FirebaseNetworkException) {
            // Network connectivity issues
            Log.e(TAG, "Network error occurred: ${e.message}")
            ErrorHolder.errorMessage = e.message ?: "Network error occurred: ${e.message}"
            false
        }/*catch (e: FirebaseTooManyRequestsException) {
        // Too many requests from the client in a short period
        Log.e(TAG, "Too many requests from the client: ${e.message}")
        ErrorHolder.errorMessage = e.message ?: "Too many requests from the client: ${e.message}"
        false
        }*/ catch (e: FirebaseAuthInvalidUserException) {
            // User account is disabled, deleted, or does not exist
            Log.e(TAG, "Invalid user account: ${e.message}")
            ErrorHolder.errorMessage = e.message ?: "Invalid user account: ${e.message}"
            false
        } catch (e: FirebaseException) {
            // Handle other registration failures or exceptions
            Log.e(TAG, "Registration failed: ${e.message}")
            ErrorHolder.errorMessage = e.message ?: "Registration failed: ${e.message}"
            false
        } catch (e: Exception) {
            // Handle other exceptions
            Log.e(TAG, "An unexpected error occurred: ${e.message}")
            ErrorHolder.errorMessage = e.message ?: "An unexpected error occurred: ${e.message}"
            false
        }
    }
}
object ErrorHolder {
    var errorMessage: String? = null
}
