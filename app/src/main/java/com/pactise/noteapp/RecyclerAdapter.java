package com.pactise.noteapp;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.RececlerViewHolder> {
    private List<Note> noteTables;
    private OnUserClicked onUserClicked;

    public interface OnUserClicked {
        void onUserSingleClicked(Note note);

    }
    public void registerView(OnUserClicked onUserClicked) {
        this.onUserClicked = onUserClicked;
    }
    public RecyclerAdapter(List<Note> noteTables,OnUserClicked onUserClicked) {
        this.noteTables = noteTables;
        this.onUserClicked=onUserClicked;
    }


    @NonNull
    @Override
    public RececlerViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View myView = inflater.inflate(R.layout.rv_item, parent, false);
        return new RececlerViewHolder(myView,onUserClicked);
    }

    @Override
    public void onBindViewHolder(@NonNull RececlerViewHolder holder, int position) {
        Note note=noteTables.get(position);
       holder.bindData(note);

    }

    @Override
    public int getItemCount() {
        return noteTables.size();
    }

    public class RececlerViewHolder extends RecyclerView.ViewHolder {

        private final TextView title;
        private final TextView desc;
        private final CardView cardView;
        private RecyclerAdapter.OnUserClicked onUserClicked;
        public RececlerViewHolder(@NonNull View itemView ,RecyclerAdapter.OnUserClicked onUserClicked) {
            super(itemView);
            title=itemView.findViewById(R.id.id_t);
            desc=itemView.findViewById(R.id.id_d);
            cardView=itemView.findViewById(R.id.id_card);
            this.onUserClicked=onUserClicked;
        }
        void bindData (Note note){
            title.setText(note.getTitle());
            desc.setText(note.getDescription());
            cardView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    onUserClicked.onUserSingleClicked(note);
                }
            });
        }
    }
}
