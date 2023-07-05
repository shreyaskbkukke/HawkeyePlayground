package com.hawkeye.authModule.presentation.viewmodel

import android.util.Patterns
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.hawkeye.authModule.domain.model.ForgetInputValidationType
import com.hawkeye.authModule.domain.repository.ErrorHolder
import com.hawkeye.authModule.domain.repository.FirebaseAuthRepository
import com.hawkeye.authModule.presentation.state.ForgetPasswordState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ForgetPasswordViewModel @Inject constructor(
    private val firebaseAuthRepository: FirebaseAuthRepository
) : ViewModel() {

    var forgetPasswordState by mutableStateOf(ForgetPasswordState())
        private set

    fun onEmailInputChange(newValue: String) {
        forgetPasswordState = forgetPasswordState.copy(emailInput = newValue)
        checkInputValidation()
    }


    fun onResetPasswordClick() {
        forgetPasswordState = forgetPasswordState.copy(isLoading = true)
        viewModelScope.launch {
            try {
                val isPasswordReset =
                    firebaseAuthRepository.forgetPassword(forgetPasswordState.emailInput)
                forgetPasswordState = forgetPasswordState.copy(isSuccessfullySent = isPasswordReset)
            } catch (e: Exception) {
                forgetPasswordState = forgetPasswordState.copy(
                    errorMessageForgetProcess = "Could not reset password",
                    isLoading = false
                )
                ErrorHolder.errorMessage = e.message ?: "Unknown error occurred"
            } finally {
                forgetPasswordState = forgetPasswordState.copy(isLoading = false)
            }
        }
    }

    fun checkInputValidation() {
        val email = forgetPasswordState.emailInput

        val validationType = when {
            email.isEmpty() -> ForgetInputValidationType.EmptyField
            !Patterns.EMAIL_ADDRESS.matcher(email).matches() -> ForgetInputValidationType.NoEmail
            else -> ForgetInputValidationType.Valid
        }

        processInputValidationType(validationType)
    }

    private fun processInputValidationType(type: ForgetInputValidationType) {
        forgetPasswordState = when (type) {
            ForgetInputValidationType.EmptyField -> {
                forgetPasswordState.copy(
                    errorMessageInput = "Empty field left",
                    isInputValid = false
                )
            }
            ForgetInputValidationType.NoEmail -> {
                forgetPasswordState.copy(
                    errorMessageInput = "Not a valid email",
                    isInputValid = false
                )
            }
            ForgetInputValidationType.Valid -> {
                forgetPasswordState.copy(errorMessageInput = null, isInputValid = true)
            }
        }
    }

}