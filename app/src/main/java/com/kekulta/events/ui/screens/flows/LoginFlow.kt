package com.kekulta.events.ui.screens.flows

import androidx.activity.compose.BackHandler
import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.SizeTransform
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideInHorizontally
import androidx.compose.animation.slideOutHorizontally
import androidx.compose.animation.togetherWith
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import com.kekulta.events.ui.screens.login.EnterCodeScreen
import com.kekulta.events.ui.screens.login.EnterPhoneScreen

enum class LoginFlowDestination {
    ENTER_PHONE,
    ENTER_CODE,
    ENTER_PROFILE,
}

@Composable
fun LoginFlow(onFlowEnd: () -> Unit) {
    var currentDestination by remember { mutableStateOf(LoginFlowDestination.ENTER_PHONE) }
    var isGoingForward by remember { mutableStateOf(true) }

    /*
        Dirty hack! I'm sure there are better ways to do this but I'm in hurry and can't find it
        yet.
     */
    fun setDestination(destination: LoginFlowDestination) {
        isGoingForward = destination.ordinal > currentDestination.ordinal
        currentDestination = destination
    }

    BackHandler(
        enabled = currentDestination.ordinal != 0
    ) {
        setDestination(LoginFlowDestination.entries[currentDestination.ordinal - 1])
    }

    var code by remember {
        mutableStateOf("")
    }

    var number by remember {
        mutableStateOf("")
    }

    AnimatedContent(
        modifier = Modifier.fillMaxSize(), targetState = currentDestination, transitionSpec = {
            if (isGoingForward) {
                slideInHorizontally { height -> height } + fadeIn() togetherWith slideOutHorizontally { height -> -height } + fadeOut()
            } else {
                slideInHorizontally { height -> -height } + fadeIn() togetherWith slideOutHorizontally { height -> height } + fadeOut()
            }.using(
                SizeTransform(clip = false)
            )
        }, label = "TabBar Animation"
    ) { destination ->
        when (destination) {
            LoginFlowDestination.ENTER_PHONE -> EnterPhoneScreen { _code, _number ->
                code = _code
                number = _number

                setDestination(LoginFlowDestination.ENTER_CODE)
            }

            /*
                Code means both county code and verification code in the naming, but I'm in hurry
                and promise to fix that!
             */
            LoginFlowDestination.ENTER_CODE -> EnterCodeScreen(
                number = "$code$number",
                code = "1234"
            ) {
                onFlowEnd()
            }

            LoginFlowDestination.ENTER_PROFILE -> TODO()
        }
    }
}
