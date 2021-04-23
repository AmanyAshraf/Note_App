package com.pactise.noteapp;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface NoteDao {
    @Insert
    void insert(Note noteTable);
    @Delete
    void delete(Note noteTable);
    @Update
    void update(Note noteTable);
    @Query("SELECT * FROM note_table")
    List<Note> viewAllItems();
}

