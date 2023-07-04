package com.hawkeye.authModule.presentation.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.hawkeye.authModule.domain.model.LoginInputValidationType
import com.hawkeye.authModule.domain.repository.ErrorHolder
import com.hawkeye.authModule.domain.repository.FirebaseAuthRepository
import com.hawkeye.authModule.domain.use_case.ValidateLoginInputUseCase
import com.hawkeye.authModule.presentation.state.LoginState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject
import kotlin.math.log

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val validateLoginInputUseCase: ValidateLoginInputUseCase,
    private val FirebaseAuthRepository: FirebaseAuthRepository
): ViewModel() {

    var loginState by mutableStateOf(LoginState())
        private set

    fun onEmailInputChange(newValue: String) {
        loginState = loginState.copy(emailInput = newValue)
        checkInputValidation()
    }

    fun onPasswordInputChange(newValue: String) {
        loginState = loginState.copy(passwordInput = newValue)
        checkInputValidation()
    }

    fun onToggleVisualTransformation() {
        loginState = loginState.copy(isPasswordShown = !loginState.isPasswordShown)
    }

    fun onLoginClick() {
        loginState = loginState.copy(isLoading = true)
        viewModelScope.launch {
            try {
                val loginResult = FirebaseAuthRepository.login(
                    email = loginState.emailInput,
                    password = loginState.passwordInput
                )
                loginState = loginState.copy(isSuccessfullyLoggedIn = loginResult)
            } catch (e: Exception) {
                loginState = loginState.copy(
                    errorMessageLoginProcess = "Could not login",
                    isLoading = false
                )
                ErrorHolder.errorMessage = e.message ?: "Unknown error occurred"
            } finally {
                loginState = loginState.copy(isLoading = false)
            }
        }
    }

    private fun checkInputValidation(){
        val validationResult = validateLoginInputUseCase(
            loginState.emailInput,
            loginState.passwordInput
        )
        processInputValidationType(validationResult)
    }

    private fun processInputValidationType(type: LoginInputValidationType){
        loginState = when(type){
            LoginInputValidationType.EmptyField -> {
                loginState.copy(errorMessageInput = "Empty fields left", isInputValid = false)
            }
            LoginInputValidationType.NoEmail -> {
                loginState.copy(errorMessageInput = "Not valid email", isInputValid = false)
            }
            LoginInputValidationType.Valid -> {
                loginState.copy(errorMessageInput = null, isInputValid = true)
            }
        }
    }

}