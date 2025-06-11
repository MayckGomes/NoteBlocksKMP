package org.example.project.repostory.database

import androidx.room.RoomDatabase

object GetDatabaseBuilder {

    var databaseBuilder: RoomDatabase.Builder<NoteDatabase>? = null

    fun loadDatabasseBuilder(db: RoomDatabase.Builder<NoteDatabase>){
       databaseBuilder = db
    }

    fun getDB() = databaseBuilder

}