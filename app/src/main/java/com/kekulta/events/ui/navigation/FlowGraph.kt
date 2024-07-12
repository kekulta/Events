package com.kekulta.events.ui.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.kekulta.events.ui.screens.flows.LoginFlow
import com.kekulta.events.ui.screens.flows.MainFlow
import com.kekulta.events.ui.screens.flows.SplashFlow

@Composable
fun FlowGraph() {
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = SplashFlow) {
        composable<SplashFlow> {
            SplashFlow {
                navController.navigate(MainFlow) {
                    popUpTo(SplashFlow) {
                        inclusive = true
                    }
                }
            }
        }

        composable<MainFlow> {
            MainFlow {
                navController.navigate(LoginFlow) {
                    popUpTo(MainFlow) {
                        inclusive = true
                    }
                }
            }
        }

        composable<LoginFlow> {
            LoginFlow() {
                navController.navigate(MainFlow) {
                    popUpTo(LoginFlow) {
                        inclusive = true
                    }
                }
            }
        }
    }
}
