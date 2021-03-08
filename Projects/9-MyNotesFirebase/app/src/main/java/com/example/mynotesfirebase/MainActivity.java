package com.example.mynotesfirebase;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import com.google.firebase.firestore.FirebaseFirestore;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements NoteFragment.OnNoteListInteractionListener {
boolean displayingEditor = false;
ArrayList<Note> notes = new ArrayList<>();
Note editingNote;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        if (!displayingEditor){
            FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
            ft.add(R.id.container,NoteFragment.newInstance(),"list_note");
            ft.commit();
        }else{
            FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
            ft.replace(R.id.container,EditNoteFragment.newInstance(editingNote.getContent()));
            ft.addToBackStack(null);
            ft.commit();


    }}


    public boolean onPrepareOptionsMenu(Menu menu){
        Log.d("onPrepareOptionsMenu new visible", menu.findItem(R.id.action_new).isVisible() + "");

        menu.findItem(R.id.action_new).setVisible(!displayingEditor);
        menu.findItem(R.id.action_close).setVisible(displayingEditor);
        return super.onPrepareOptionsMenu(menu);
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        Log.d("onOptionsItemSelected", item.getTitle().toString());
        displayingEditor = !displayingEditor;
        invalidateOptionsMenu();
        switch (item.getItemId()) {
            case R.id.action_new:
                editingNote = createNote();
                FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
                ft.replace(R.id.container,EditNoteFragment.newInstance(""),"edit_note");
                ft.addToBackStack(null);
                ft.commit();
                return true;
            case R.id.action_close:
                onBackPressed();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    private Note createNote() {

        FirebaseFirestore db = FirebaseFirestore.getInstance();
        Note note = new Note();
        String id = db.collection("notes").document().getId();
        Log.d("Document Id",id);
        note.setId(id);
        return note;

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater influter  =getMenuInflater();
        influter.inflate(R.menu.menu,menu);
        return super.onCreateOptionsMenu(menu);
    }


    @Override
    public void onNoteSelected(Note note) {
        editingNote =note;
        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();

        ft.replace(R.id.container,EditNoteFragment.newInstance(editingNote.getContent()),"edit_note");

        ft.addToBackStack(null);
        ft.commit();
        displayingEditor = !displayingEditor;
        invalidateOptionsMenu();
    }
}