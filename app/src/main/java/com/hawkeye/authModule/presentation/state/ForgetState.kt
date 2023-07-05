package com.hawkeye.authModule.presentation.state

data class ForgetPasswordState(
    val emailInput:String = "",
    val isInputValid:Boolean = false,
    val errorMessageInput:String? = null,
    var isLoading:Boolean = false,
    val isSuccessfullySent:Boolean = false,
    val errorMessageForgetProcess:String? = null

)