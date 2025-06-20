package org.example.project.navgation

import androidx.compose.animation.AnimatedContentTransitionScope
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.toRoute
import kotlinx.serialization.Serializable
import org.example.project.features.editOrAdd.EditOrAddScreen
import org.example.project.features.home.HomeScren

@Serializable
object HomeRoute

@Serializable
data class EditOrAddRoute(
    val id: Int?
)

@Composable
fun Navgation() {

    val navController = rememberNavController()


    NavHost(
        navController,
        startDestination = HomeRoute,

        enterTransition = {
            slideIntoContainer(
                AnimatedContentTransitionScope.SlideDirection.Left, // entra da direita para a esquerda
                animationSpec = tween(300)
            )
        },
        exitTransition = {
            slideOutOfContainer(
                AnimatedContentTransitionScope.SlideDirection.Left, // sai para a esquerda
                animationSpec = tween(300)
            )
        },
        popEnterTransition = {
            slideIntoContainer(
                AnimatedContentTransitionScope.SlideDirection.Right, // volta da esquerda para a direita
                animationSpec = tween(300)
            )
        },
        popExitTransition = {
            slideOutOfContainer(
                AnimatedContentTransitionScope.SlideDirection.Right, // sai para a direita
                animationSpec = tween(300)
            )
        }

    ){

        composable<HomeRoute> {
            HomeScren(navController)
        }

        composable<EditOrAddRoute> {
            val arg = it.toRoute<EditOrAddRoute>()
            EditOrAddScreen(navController,id = arg.id)
        }

    }

}