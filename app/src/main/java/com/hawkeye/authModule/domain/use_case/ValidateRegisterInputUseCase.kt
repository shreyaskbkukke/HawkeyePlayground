package com.hawkeye.authModule.domain.use_case

import com.hawkeye.authModule.domain.model.RegisterInputValidationType
import com.hawkeye.authModule.util.containsLowerCase
import com.hawkeye.authModule.util.containsNumber
import com.hawkeye.authModule.util.containsSpecialChar
import com.hawkeye.authModule.util.containsUpperCase

class ValidateRegisterInputUseCase {
    operator fun invoke(
        email: String,
        password: String,
        passwordRepeated: String
    ): RegisterInputValidationType {
        if(email.isEmpty() || password.isEmpty() || passwordRepeated.isEmpty()){
            return RegisterInputValidationType.EmptyField
        }
        if("@" !in email){
            return RegisterInputValidationType.NoEmail
        }
        if(password!= passwordRepeated){
            return RegisterInputValidationType.PasswordsDoNotMatch
        }
        if(password.count() < 8){
            return RegisterInputValidationType.PasswordTooShort
        }
        if(!password.containsNumber()){
            return RegisterInputValidationType.PasswordNumberMissing
        }
        if(!password.containsUpperCase()){
            return RegisterInputValidationType.PasswordUpperCaseMissing
        }
        if(!password.containsSpecialChar()){
            return RegisterInputValidationType.PasswordSpecialCharMissing
        }
        if(!password.containsLowerCase()){
            return RegisterInputValidationType.PasswordLowerCaseMissing
        }
        return RegisterInputValidationType.Valid
    }
}