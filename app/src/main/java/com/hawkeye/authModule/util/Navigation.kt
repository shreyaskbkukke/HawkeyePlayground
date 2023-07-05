package com.hawkeye.authModule.util

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.hawkeye.authModule.domain.repository.ErrorHolder
import com.hawkeye.authModule.presentation.FinalDestination
import com.hawkeye.authModule.presentation.ForgetScreen
import com.hawkeye.authModule.presentation.LoginScreen
import com.hawkeye.authModule.presentation.RegisterScreen

@Composable
fun Navigation() {
    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = ScreenRoutes.LoginScreen.route
    ){
        composable(ScreenRoutes.LoginScreen.route){
            LoginScreen(
                onLoginSuccessNavigation = {
                    navController.navigate(ScreenRoutes.FinalDestination.route) {
                        ErrorHolder.errorMessage = "Login Successful"
                        popUpTo(0)
                    }
                },
                onNavigateToRegisterScreen = {
                    navController.navigate(ScreenRoutes.RegisterScreen.route) {
                        popUpTo(0)
                    }
                },
                onNavigateToForgetScreen = {
                    navController.navigate(ScreenRoutes.ForgetScreen.route) {
                        popUpTo(0)
                    }
                }
            )
        }
        composable(ScreenRoutes.RegisterScreen.route){
            RegisterScreen(
                onRegisterSuccessNavigation = {
                      navController.navigate(ScreenRoutes.FinalDestination.route){
                          ErrorHolder.errorMessage = "Registration Successful"
                          popUpTo(0)
                      }
                },
                onNavigateToLoginScreen = {
                    navController.navigate(ScreenRoutes.LoginScreen.route){
                        popUpTo(0)
                    }
                }
            )
        }
        //
        composable(ScreenRoutes.ForgetScreen.route){
            ForgetScreen(
                onForgetSuccessNavigation = {
                    navController.navigate(ScreenRoutes.LoginScreen.route) {
                        ErrorHolder.errorMessage = "Email Sent Successfully"
                        popUpTo(0)
                    }
                },
                onNavigateToLoginScreen = {
                    navController.navigate(ScreenRoutes.LoginScreen.route) {
                        popUpTo(0)
                    }
                }
            )
        }
        //
        composable(ScreenRoutes.FinalDestination.route){
            FinalDestination()
        }
    }

}

sealed class ScreenRoutes(val route:String){
    object LoginScreen:ScreenRoutes("login_screen")
    object RegisterScreen:ScreenRoutes("register_screen")
    object ForgetScreen:ScreenRoutes("forget_screen")
    object FinalDestination:ScreenRoutes("final_destination")
}