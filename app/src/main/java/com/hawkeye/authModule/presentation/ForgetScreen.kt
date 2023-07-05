package com.hawkeye.authModule.presentation

import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.hawkeye.authModule.domain.repository.ErrorHolder
import com.hawkeye.authModule.presentation.components.AuthButton
import com.hawkeye.authModule.presentation.components.BubbleAnimation
import com.hawkeye.authModule.presentation.components.HeaderBackground
import com.hawkeye.authModule.presentation.components.NavDestinationHelper
import com.hawkeye.authModule.presentation.components.TextEntryModule
import com.hawkeye.authModule.presentation.viewmodel.ForgetPasswordViewModel
import com.hawkeye.ui.theme.gray
import com.hawkeye.ui.theme.orange
import com.hawkeye.ui.theme.white
import com.hawkeye.ui.theme.whiteGray
import com.hawkeye.ui.theme.whiteGrayOrange

@Composable
fun ForgetScreen(
    onForgetSuccessNavigation:() -> Unit,
    onNavigateToLoginScreen: () -> Unit,
    forgetViewModel: ForgetPasswordViewModel = hiltViewModel()
){
    NavDestinationHelper(
        shouldNavigate = {
            forgetViewModel.forgetPasswordState.isSuccessfullySent
        },
        destination = {
            onForgetSuccessNavigation()
        }
    )
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(white)
    ){
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(120.dp),
            contentAlignment = Alignment.Center
        ){
            HeaderBackground(
                leftColor = orange,
                rightColor = whiteGrayOrange,
                modifier = Modifier
                    .fillMaxSize()
            )
            Text(
                text = "Forget Password",
                style = MaterialTheme.typography.h4,
                color = white,
                fontWeight = FontWeight.SemiBold
            )
        }
        ForgetContainer(
            emailValue = {
                forgetViewModel.forgetPasswordState.emailInput
            },
            buttonEnabled = {
                forgetViewModel.forgetPasswordState.isInputValid
            },
            onEmailChanged = forgetViewModel::onEmailInputChange,
            onForgetPasswordClick = forgetViewModel::onResetPasswordClick,
            errorHint = {
                forgetViewModel.forgetPasswordState.errorMessageInput
            },
            isLoading = {
                forgetViewModel.forgetPasswordState.isLoading
            },

            modifier = Modifier
                .padding(top = 200.dp)
                .fillMaxWidth(0.9f)
                .shadow(5.dp, RoundedCornerShape(15.dp))
                .background(whiteGray, RoundedCornerShape(15.dp))
                .padding(10.dp, 15.dp, 10.dp, 5.dp)
                .align(Alignment.TopCenter)
        )
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .offset(y = (370).dp)
                .wrapContentHeight()
                .padding(20.dp, 15.dp, 20.dp, 5.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ){
            Text(
                "Back to login?",
                style = MaterialTheme.typography.body2
            )
            AuthButton(
                text = "Login",
                backgroundColor = orange,
                contentColor = white,
                enabled = true,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(45.dp)
                    .shadow(5.dp, RoundedCornerShape(25.dp)),
                isLoading = false,
                onButtonClick = {
                    onNavigateToLoginScreen()
                }
            )
        }
        BubbleAnimation(
            bubbleColor1 = whiteGrayOrange,
            bubbleColor2 = orange,
            modifier = Modifier
                .fillMaxWidth()
                .height(250.dp)
                .align(Alignment.BottomCenter)
        )
    }
}

@Composable
fun ForgetContainer(
    emailValue:()-> String,
    buttonEnabled:()-> Boolean,
    onEmailChanged:(String)-> Unit,
    errorHint:()->String?,
    isLoading:()->Boolean,
    modifier: Modifier = Modifier,
    onForgetPasswordClick: ()-> Unit
){
    Column(
        modifier = modifier.fillMaxWidth(),
        verticalArrangement = Arrangement.spacedBy(15.dp)
    ){
        val errorMessage = ErrorHolder.errorMessage
        if (errorMessage != null) {
            Toast.makeText(LocalContext.current, errorMessage, Toast.LENGTH_SHORT).show()
            ErrorHolder.errorMessage = null // Reset the error message after displaying
        }
        TextEntryModule(
            modifier = Modifier
                .fillMaxWidth(),
            description = "Email address",
            hint = "username@gmail.com",
            textValue = emailValue(),
            textColor = gray,
            cursorColor = orange,
            onValueChanged = onEmailChanged,
            trailingIcon = null,
            onTrailingIconClick = null,
            leadingIcon = Icons.Default.Email
        )
        Column(
            modifier = Modifier
                .fillMaxWidth(),
            verticalArrangement = Arrangement.spacedBy(5.dp)
        ){
            AuthButton(
                text = "Send Mail",
                backgroundColor = orange,
                contentColor = white,
                enabled = buttonEnabled(),
                modifier = Modifier
                    .fillMaxWidth()
                    .height(45.dp)
                    .shadow(5.dp, RoundedCornerShape(25.dp)),
                isLoading = isLoading(),
                onButtonClick = onForgetPasswordClick
            )
            Text(
                errorHint() ?: "",
                style = MaterialTheme.typography.caption,
                color = Color.Red
            )
        }
    }
}
