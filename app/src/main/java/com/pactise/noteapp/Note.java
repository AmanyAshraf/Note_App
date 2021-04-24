package com.pactise.noteapp;


import androidx.room.Entity;
import androidx.room.PrimaryKey;
@Entity(tableName = "note_table")
public class Note {
    @PrimaryKey(autoGenerate = true)
    int id ;
    String description;
    String title ;

    public Note(String description, String title) {
        this.description = description;
        this.title = title;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public String getDescription() {
        return description;
    }

    public String getTitle() {
        return title;
    }
}
