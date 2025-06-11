package org.example.project.navgation

import androidx.compose.animation.AnimatedContentTransitionScope
import androidx.compose.animation.core.tween
import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.toRoute
import androidx.room.RoomDatabase
import kotlinx.serialization.Serializable
import org.example.project.Screens.editOrAdd.EditOrAddScreen
import org.example.project.Screens.home.HomeScren
import org.example.project.repostory.database.NoteDatabase

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
                AnimatedContentTransitionScope.SlideDirection.Right,
                animationSpec = tween(300)
            )
        },
        popExitTransition = {
            slideOutOfContainer(
                AnimatedContentTransitionScope.SlideDirection.Right,
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