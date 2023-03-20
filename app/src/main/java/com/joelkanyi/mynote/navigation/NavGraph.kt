package com.joelkanyi.mynote.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.joelkanyi.mynote.screen.*
import com.joelkanyi.mynote.screens.AddNotesScreen

@Composable
fun NavGraph(
    navController: NavHostController,
) {
    NavHost(navController = navController, startDestination = Screen.Splash.route) {
        composable(route = Screen.Splash.route) {
            AnimatedLaunchScreen(navController = navController)
        }

        composable(
            route = Screen.AddNote.route,
            arguments = listOf(
                navArgument(name = TITLE) {
                    defaultValue = ""
                    type = NavType.StringType
                },
                navArgument(name = CONTENT) {
                    defaultValue = ""
                    type = NavType.StringType
                },
                navArgument(name = ID) {
                    defaultValue = -1
                    type = NavType.IntType
                }
            )
        ) { navBackStackEntry ->
            AddNotesScreen(
                navController = navController,
                title = navBackStackEntry.arguments?.getString(TITLE) ?: "",
                content = navBackStackEntry.arguments?.getString(CONTENT) ?: "",
                id = navBackStackEntry.arguments?.getInt(ID)
            )
        }

        composable(route = Screen.AddNoteScreen.route){
            Screen.AddNoteScreen(navController = navController)

        }
    }

}