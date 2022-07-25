package com.example.noteproject;

import android.app.Application;

import androidx.lifecycle.LiveData;

import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Repository {

    private NoteDao noteDao;
    private LiveData<List<Note>> notes;
    ExecutorService executors = Executors.newSingleThreadExecutor();

    public Repository (Application application){
        NoteDatabase database = NoteDatabase.getInstance(application);
        noteDao= database.noteDao();
        notes= noteDao.getAllNotes();
    }

    public void insert (Note note){
        executors.execute(() -> noteDao.insert(note));
//        new InsertNoteAsyncTask(noteDao).execute(note);

    }
    public void update (Note note){
        executors.execute(() -> noteDao.update(note));
//        new UpdateNoteAsyncTask(noteDao).execute(note);
    }
    public void delete (Note note){
        executors.execute(() -> noteDao.delete(note));
//        new DeleteNoteAsyncTask(noteDao).execute(note);
    }
    public LiveData<List<Note>> getAllNotes(){
        return notes;
    }
//       1. parameter for doinbackground method
//       2.Parameter for onProgress method
//       3.Parameter for return type background method
/*
    private static class InsertNoteAsyncTask extends AsyncTask<Note,Void,Void>{
        private NoteDao noteDao;

        private InsertNoteAsyncTask (NoteDao noteDao){

            this.noteDao=noteDao;
        }
        @Override
        protected Void doInBackground(Note... notes) {
            noteDao.insert(notes[0]);
            return null;
        }


    }
//    Update
    private static class UpdateNoteAsyncTask extends AsyncTask<Note,Void,Void>{
    private NoteDao noteDao;

    private UpdateNoteAsyncTask (NoteDao noteDao){

        this.noteDao=noteDao;
    }
    @Override
    protected Void doInBackground(Note... notes) {
        noteDao.update(notes[0]);
        return null;
    }


}
//  Delete
    private static class DeleteNoteAsyncTask extends AsyncTask<Note,Void,Void>{
    private NoteDao noteDao;

    private DeleteNoteAsyncTask (NoteDao noteDao){

        this.noteDao=noteDao;
    }
    @Override
    protected Void doInBackground(Note... notes) {
        noteDao.delete(notes[0]);
        return null;
    }


}

 */
}
