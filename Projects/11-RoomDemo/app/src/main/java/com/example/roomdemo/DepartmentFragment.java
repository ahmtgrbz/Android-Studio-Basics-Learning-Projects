package com.example.roomdemo;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;


import java.util.ArrayList;

public class DepartmentFragment extends Fragment {
    private static final String ARG_DEPARTMENTS = "departments";
    private OnDepartmentListInteractionListener mListener;
    private ArrayList<Department> departments;
    MyDepartmentRecyclerViewAdapter mAdapter;
    /**
     * Mandatory empty constructor for the fragment manager to instantiate the
     * fragment (e.g. upon screen orientation changes).
     */
    public DepartmentFragment() {
    }
    public static DepartmentFragment newInstance(ArrayList<Department> notes) {
        DepartmentFragment fragment = new DepartmentFragment();
        Bundle args = new Bundle();
        args.putSerializable(ARG_DEPARTMENTS, notes);
        fragment.setArguments(args);
        return fragment;
    }
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            departments =
                    (ArrayList<Department>)getArguments().getSerializable(ARG_DEPARTMENTS);
        }
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_department_list, container, false);
        // Set the adapter
        if (view instanceof RecyclerView) {
            Context context = view.getContext();
            RecyclerView recyclerView = (RecyclerView) view;
            recyclerView.setLayoutManager(new LinearLayoutManager(context));
            mAdapter = new MyDepartmentRecyclerViewAdapter(departments, mListener);
            recyclerView.setAdapter(mAdapter);
        }
        return view;
    }
    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnDepartmentListInteractionListener) {
            mListener = (OnDepartmentListInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnNoteListInteractionListener");
        }
    }
    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }
    public void setDepartments(ArrayList<Department> departments) {
        this.departments.clear();
        this.departments.addAll(departments);
        mAdapter.notifyDataSetChanged();
    }
    /**
     * Interface for listing note operations in the list
     */
    public interface OnDepartmentListInteractionListener {
        void onDepartmentSelected(Department item);
    }
}