package com.mtc.login.navigation

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import com.mtc.login.LoginRoute
import com.mtc.navigation.Route
import com.mtc.signup.SignUpRoute
import kotlinx.serialization.Serializable

fun NavController.navigateLogin(navOptions: NavOptions) {
    navigate(Login, navOptions)
}

fun NavGraphBuilder.loginNavGraph(
    padding: PaddingValues,
    modifier: Modifier = Modifier,
    navigateToHome: () -> Unit,
    navigateToSignUp: () -> Unit,
    navigateToLogin: () -> Unit,
) {
    composable<Login> {
        LoginRoute(
            padding = padding,
            modifier = modifier,
            navigateToHome = navigateToHome,
            navigateToSignUp = navigateToSignUp,
        )
    }

    composable<SignUp> {
        SignUpRoute(
            padding = padding,
            modifier = modifier,
            navigateToHome = navigateToHome,
        )
    }
}

@Serializable
data object Login : Route

@Serializable
data object SignUp : Route
