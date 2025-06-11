package org.example.project

import androidx.compose.runtime.Composable
import androidx.room.RoomDatabase
import org.example.project.navgation.Navgation
import org.example.project.repostory.database.GetDatabaseBuilder
import org.example.project.repostory.database.NoteDatabase

@Composable
fun App(databaseBuilder: RoomDatabase.Builder<NoteDatabase>) {

    val dbBuilder = GetDatabaseBuilder

    dbBuilder.loadDatabasseBuilder(databaseBuilder)

    Navgation()

}