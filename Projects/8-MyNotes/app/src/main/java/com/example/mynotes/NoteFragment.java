package com.example.mynotes;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

/**
 * A fragment representing a list of Items.
 */
public class NoteFragment extends Fragment {

    // TODO: Customize parameter argument names
    private static final String ARG_NOTES = "notes";

    private OnNoteListInteractionListener mListener;

    private ArrayList<Note> notes;



    /**
     * Mandatory empty constructor for the fragment manager to instantiate the
     * fragment (e.g. upon screen orientation changes).
     */
    public NoteFragment() {
    }

    // TODO: Customize parameter initialization
    @SuppressWarnings("unused")
    public static android.app.Fragment newInstance(ArrayList<Note> notes) {
        NoteFragment fragment = new NoteFragment();
        Bundle args = new Bundle();
        args.putSerializable(ARG_NOTES, notes);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (getArguments() != null) {
            notes = (ArrayList<Note> ) getArguments().getSerializable(ARG_NOTES);
        }
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        if(context instanceof OnNoteListInteractionListener){
            mListener = (OnNoteListInteractionListener) context;

        }else{
            throw new RuntimeException(context.toString() + "should implements ");

        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_item_list, container, false);

        // Set the adapter
        if (view instanceof RecyclerView) {
            Context context = view.getContext();
            RecyclerView recyclerView = (RecyclerView) view;

            recyclerView.setLayoutManager(new LinearLayoutManager(context));

            recyclerView.setAdapter(new MyNoteRecyclerViewAdapter(notes,mListener));
        }
        return view;
    }


    public interface OnNoteListInteractionListener{
        void onNoteSelected(Note note);
    }


}