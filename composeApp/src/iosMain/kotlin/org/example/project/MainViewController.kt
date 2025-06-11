package org.example.project

import androidx.compose.ui.window.ComposeUIViewController
import org.example.project.repostory.database.getDatabaseBuilder

fun MainViewController() = ComposeUIViewController { App(getDatabaseBuilder()) }