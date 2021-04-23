package com.pactise.noteapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class NoteDetails extends AppCompatActivity {
    private EditText title, desc ;
    private int receivedId;
    boolean openedAsUpdate =false;
    Button buttonUpdate,buttonAdd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_note_details);
        title =findViewById(R.id.id_title);
        desc= findViewById(R.id.id_desc);
        buttonAdd=findViewById(R.id.btn_add);
        buttonUpdate=findViewById(R.id.btn_update);

        receivedId= getIntent().getIntExtra("id",-1);
        if (receivedId != -1){
            setTitle("Update Note");
            title.setText(getIntent().getStringExtra("title"));
            desc.setText(getIntent().getStringExtra("desc"));
            buttonUpdate.setVisibility(View.VISIBLE);
            buttonAdd.setVisibility(View.INVISIBLE);
            openedAsUpdate=true;
        }

        else setTitle("Add Note");


    }


    public void savingDetails(View view) {
        String writtenTitle=title.getText().toString();
        String writtenDesc=desc.getText().toString();
        if (writtenDesc.isEmpty()){
            desc.setError("Required field");
        }
        else {
            NoteData database = NoteData.getInstance(this);
            NoteDao dao = database.noteDao();
            new Thread(new Runnable() {
                @Override
                public void run() {
                    dao.insert(new Note(writtenDesc,writtenTitle));
                }
            }).start();
            finish();
        }
    }

    public void update(View view) {
        String writtenTitle=title.getText().toString();
        String writtenDesc=desc.getText().toString();
        if (writtenDesc.isEmpty()){
            desc.setError("Required field");
        }
        else {
            NoteData database = NoteData.getInstance(this);
            NoteDao dao = database.noteDao();
            new Thread(new Runnable() {
                @Override
                public void run() {
                    Note note= new Note(writtenDesc,writtenTitle);
                    dao.update(note);
                }
            }).start();
            finish();
        }
    }
}