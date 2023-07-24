package com.example.notetakingapp.database

import android.content.Context
import android.provider.CalendarContract.Instances
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.notetakingapp.model.Note


@Database(entities = [Note::class], version = 1)
abstract class NoteDatabase:RoomDatabase() {

    abstract fun getNoteDAO() :NoteDAO

    companion object{
        @Volatile
        private var instance :NoteDatabase?=null
        private val LOCK =Any()

        operator fun invoke(context: Context) = instance?:
        synchronized(LOCK){
            instance?:
            createDatabase(context).also{
                instance=it
            }
        }

        private fun createDatabase(context: Context)=
            Room.databaseBuilder(
                context.applicationContext,
                NoteDatabase::class.java,
                "note_db"
            ).build()
    }


}