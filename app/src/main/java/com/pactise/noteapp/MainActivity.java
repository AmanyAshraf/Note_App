package com.pactise.noteapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import java.util.List;

public class MainActivity extends AppCompatActivity implements RecyclerAdapter.OnUserClicked {
    List<Note> notes;
    private RecyclerAdapter adapter;
    private RecyclerView recyclerView;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getNotes();
        NoteData database = NoteData.getInstance(this);
        NoteDao dao = database.noteDao();
        adapter=new RecyclerAdapter(notes,this);
        recyclerView=findViewById(R.id.id_rv);
        new ItemTouchHelper(new ItemTouchHelper.SimpleCallback(0,ItemTouchHelper.RIGHT | ItemTouchHelper.LEFT) {
            @Override
            public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {
                return false;
            }

            @Override
            public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {
                Note note= notes.get(viewHolder.getAdapterPosition());
                dao.delete(note);
                notes.remove(notes.get(viewHolder.getAdapterPosition()));
                adapter.notifyDataSetChanged();
            }
        }).attachToRecyclerView(recyclerView);
    }
    public void AddNewNote(View view) {
        Intent intent=new Intent(MainActivity.this,NoteDetails.class);
        startActivity(intent);
        getNotes();
    }

    public void getNotes(){
        NoteData database = NoteData.getInstance(this);
        NoteDao dao = database.noteDao();
        View view =findViewById(R.id.id_no_notes);
        new Thread(new Runnable() {
            @Override
            public void run() {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        notes=dao.viewAllItems();
                        if (notes.size()==0){
                            view.setVisibility(View.VISIBLE);
                        }
                        else{
                            view.setVisibility(View.INVISIBLE);
                            adapter=new RecyclerAdapter(notes,MainActivity.this::onUserSingleClicked);
                            recyclerView=findViewById(R.id.id_rv);
                            recyclerView.setAdapter(adapter);
                        }
                    }
                });
            }
        }).start();
    }

    @Override
    public void onUserSingleClicked(Note note) {
        Intent intent=new Intent(MainActivity.this,NoteDetails.class);
        intent.putExtra("id",note.getId());
        intent.putExtra("title",note.getTitle());
        intent.putExtra("desc",note.getDescription());
        startActivity(intent);
    }
}