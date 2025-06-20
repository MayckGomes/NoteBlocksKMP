package org.example.project

import androidx.compose.runtime.Composable
import org.example.project.di.getKoinConfig
import org.example.project.navgation.Navgation
import org.koin.compose.KoinMultiplatformApplication
import org.koin.core.annotation.KoinExperimentalAPI

@OptIn(KoinExperimentalAPI::class)
@Composable
fun App() {

    KoinMultiplatformApplication(
        config = getKoinConfig()
    ){
        Navgation()
    }

}