package com.pactise.noteapp;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

@Database(entities = Note.class ,version = 1)
public abstract class  NoteData extends RoomDatabase {



    public static NoteData noteData;
    public abstract NoteDao noteDao();

    public static synchronized NoteData getInstance(Context context){
        if (noteData == null){
            noteData= Room.databaseBuilder(context.getApplicationContext()
                    ,NoteData.class
                    ,"note_database")
                    .allowMainThreadQueries()
                    .build();
        }
        return noteData;
    }
}
