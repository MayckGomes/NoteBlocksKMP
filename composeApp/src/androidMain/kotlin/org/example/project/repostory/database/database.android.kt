package org.example.project.repostory.database

import android.content.Context
import androidx.room.Room
import androidx.room.RoomDatabase

fun getDatabaseBuilder(context: Context): RoomDatabase.Builder<NoteDatabase> {
    val dbFile = context.getDatabasePath("myDatabase.db")
    return Room.databaseBuilder(
        context,
        NoteDatabase::class.java,
        dbFile.absolutePath
    )
}
