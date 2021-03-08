package com.example.mynotes;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link EditNoteFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class EditNoteFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_NOTE = "content";

    private String content;
    private EditText txtContent;

    public EditNoteFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *

     * @return A new instance of fragment EditNoteFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static android.app.Fragment newInstance(String content) {
        EditNoteFragment fragment = new EditNoteFragment();
        Bundle args = new Bundle();
        args.putString(ARG_NOTE, content);

        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            content = getArguments().getString(ARG_NOTE);

        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_edit_note, container, false);
    }


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        txtContent = view.findViewById(R.id.noteContent);
        if(content != null ){
            txtContent.setText(content);

        }
    }

    public String getContent() {
        return txtContent.getText().toString();
    }
}