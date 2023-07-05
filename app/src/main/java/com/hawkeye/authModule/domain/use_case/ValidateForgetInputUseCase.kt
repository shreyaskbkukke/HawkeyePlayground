package com.hawkeye.authModule.domain.use_case

import com.hawkeye.authModule.domain.model.ForgetInputValidationType

class ValidateForgetInputUseCase() {

    operator fun invoke(email: String):ForgetInputValidationType{
        if(email.isEmpty()){
            return ForgetInputValidationType.EmptyField
        }
        if("@" !in email){
            return ForgetInputValidationType.NoEmail
        }
        return ForgetInputValidationType.Valid
    }
}