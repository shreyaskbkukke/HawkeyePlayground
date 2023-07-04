package com.hawkeye.authModule.presentation.state

data class LoginState(
    val emailInput:String = "",
    val passwordInput:String = "",
    val isInputValid:Boolean = false,
    val isPasswordShown:Boolean = false,
    val errorMessageInput:String? = null,
    var isLoading:Boolean = false,
    val isSuccessfullyLoggedIn:Boolean = false,
    val errorMessageLoginProcess:String? = null
)
