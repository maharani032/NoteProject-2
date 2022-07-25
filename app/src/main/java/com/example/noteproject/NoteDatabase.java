package com.example.noteproject;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;


@Database(entities = {Note.class},version=1)

public abstract class NoteDatabase extends RoomDatabase {
    private static NoteDatabase instance;

    public abstract NoteDao noteDao();
    public static synchronized NoteDatabase getInstance(Context context)
    {
        if (instance == null){
            instance = Room.databaseBuilder(context.getApplicationContext()
                            ,NoteDatabase.class,"note_database")
                    .fallbackToDestructiveMigration()
                    .addCallback(roomCallback)
                    .build();
        }
        return instance;
    }
    private static RoomDatabase.Callback roomCallback= new RoomDatabase.Callback(){
        @Override
        public void onCreate(@NonNull SupportSQLiteDatabase db) {
            super.onCreate(db);
//            new PopulateDBAsyncTask(instance).execute();
            NoteDao noteDao = instance.noteDao();
            ExecutorService executorService= Executors.newSingleThreadExecutor();

            executorService.execute(new Runnable() {
                @Override
                public void run() {
                    noteDao.insert(new Note("title 1","Description 1"));
                    noteDao.insert(new Note("title 2","Description 2"));
                    noteDao.insert(new Note("title 3","Description 3"));
                    noteDao.insert(new Note("title 4","Description 4"));
                    noteDao.insert(new Note("title 5","Description 5"));
                }
            });
        }
    };

    /*private static class PopulateDBAsyncTask extends AsyncTask<Void,Void,Void>{
        private NoteDao noteDao;
        private PopulateDBAsyncTask(NoteDatabase database){
            noteDao=database.noteDao();
        }
        @Override
        protected Void doInBackground(Void... voids) {
            noteDao.insert(new Note("title 1","Description 1"));
            noteDao.insert(new Note("title 2","Description 2"));
            noteDao.insert(new Note("title 3","Description 3"));
            noteDao.insert(new Note("title 4","Description 4"));
            noteDao.insert(new Note("title 5","Description 5"));

            return null;
        }
    }*/
}