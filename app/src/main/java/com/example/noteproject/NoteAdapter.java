package com.example.noteproject;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class NoteAdapter extends RecyclerView.Adapter<NoteAdapter.NoteHolder> {
    private List<Note> notes= new ArrayList<>();
    private OnItemClickListener listener;
    @NonNull
    @Override
    public NoteHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
//      menghubungkan tampilan
        /* LayoutInflater untuk menghubungkan MainActivity (Activity Utama)
              dengan Sub Activity, yaitu bagian kecil dari Activity*/
        View view= LayoutInflater.from(parent.getContext())
                .inflate(R.layout.note_item,parent,false);
        return new NoteHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull NoteHolder holder, int position) {
//        transfer data dari setiap java object ke noteholder
//        jadi setiap note datang dari database berupa java object
        Note currentNote =notes.get(position);
        holder.textViewTitle.setText(currentNote.getTitle());
        holder.textViewDescription.setText(currentNote.getDescription());
    }

    @Override
    public int getItemCount() {
//        the number want to show in recycle view.
        return notes.size();
    }
    public void setNotes(List<Note> notes){
//        mengamati perubahan live data
        this.notes= notes;
        notifyDataSetChanged();
    }

    public Note getNotes(int position){
        return notes.get(position);
    }

    //    untuk menghubungkan bagian backend dengan frontend
    class NoteHolder extends RecyclerView.ViewHolder{
        //        untuk memperbarui bagian frontend
        TextView textViewTitle;
        TextView textViewDescription;

        public NoteHolder(@NonNull View itemView) {
            super(itemView);
            textViewTitle= itemView.findViewById(R.id.textViewTitle);
            textViewDescription=itemView.findViewById(R.id.textViewDescription);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    int position=getAdapterPosition();
                    if (listener != null && position != RecyclerView.NO_POSITION){
                        listener.onItemClick(notes.get(position));
                    }

                }
            });
        }
    }
    public interface OnItemClickListener{
        void onItemClick(Note note);
    }
    public void setOnItemClickListener(OnItemClickListener listener){
        this.listener=listener;
    }
}
