package com.example.notetakingapp.database

import android.provider.ContactsContract
import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.notetakingapp.model.Note


@Dao
interface NoteDAO {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertNote(note: Note)

    @Update
    suspend fun updateNote(note: Note)

    @Delete
    suspend fun deleteNote(note: Note)

    @Query("SELECT * FROM NOTES ORDER BY id DESC")
    fun getAllNotes() : LiveData<List<Note>>

    @Query("SELECT * FROM NOTES WHERE noteTitle LIKE:query OR noteBody LIKE:query")
    fun searchNote(query:String?) : LiveData<List<Note>>
}