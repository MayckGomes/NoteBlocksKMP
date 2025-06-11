package org.example.project.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

private val backgroundDark = Color(0xFF0E0E0E)
private val backgroundLight = Color(0xFFF0F0F0)

private val primaryColor = Color(0xFF0081ff)
private val secunderyColor = Color(0xff005db8)

@Composable
fun MyAppTheme(
    content : @Composable () -> Unit
) {

    val colorScheme = if (isSystemInDarkTheme()){

        darkColorScheme(
            background = Color(0xFF0E0E0E),
            onBackground = Color(0xFFF0F0F0),
            primary = Color(0xFF00a2ff),
            secondary = Color(0xff005db8)
        )

    } else {

        lightColorScheme(
            background = Color(0xFFF0F0F0),
            onBackground = Color(0xFF0E0E0E),
            primary = Color(0xFF00a2ff),
            secondary = Color(0xff005db8)
        )

    }

    MaterialTheme(
        colorScheme = colorScheme,
        content = content
    )

}