package com.example.architecturecomponents;

import android.app.Application;
import android.os.AsyncTask;
import android.provider.ContactsContract;

import java.util.List;

import androidx.lifecycle.LiveData;

import static android.os.Build.VERSION_CODES.P;

public class NoteRepository {

    private NoteDao noteDao;

    private LiveData<List<Note>> allNotes;

    public NoteRepository(Application application) {
        NoteDatabase database = NoteDatabase.getInstance(application);
        noteDao = database.noteDao();
        allNotes = noteDao.getAllNotes();
    }


    public void insert(Note note) {
        insertNote(note);
    }

    public void delete(Note note) {
        deleteNote(note);
    }

    public void update(Note note) {
        updateNote(note);
    }

    public void deleteAllNotes() {

        deleteAll();
    }

    public LiveData<List<Note>> getAllNotes() {
        return allNotes;
    }


    public void insertNote(final Note note) {
        new AsyncTask<Note, Void, Void>() {
            @Override
            protected Void doInBackground(Note... notes) {
                noteDao.insert(notes[0]);
                return null;
            }
        }.execute(note);
    }

    public void updateNote(final Note note) {
        new AsyncTask<Note,Void,Void>() {
            @Override
            protected Void doInBackground(Note... notes) {
                noteDao.update(notes[0]);
                return null;
            }
        }.execute(note);
    }


    public void deleteNote(final Note note) {
        new AsyncTask<Note, Void, Void>() {
            @Override
            protected Void doInBackground(Note... notes) {
                noteDao.delete(note);
                return null;
            }
        }.execute(note);
    }

    public void deleteAll() {
        new AsyncTask<Void, Void, Void>() {
            @Override
            protected Void doInBackground(Void... voids) {
                noteDao.deleteAllNote();
                return null;
            }
        }.execute();
    }
}
