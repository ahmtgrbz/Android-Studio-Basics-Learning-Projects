package com.example.roomdemo;

import androidx.recyclerview.widget.RecyclerView;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;



import java.util.List;

public class MyDepartmentRecyclerViewAdapter extends
        RecyclerView.Adapter<MyDepartmentRecyclerViewAdapter.ViewHolder> {
    private final List<Department> mValues;
    private final DepartmentFragment.OnDepartmentListInteractionListener mListener;
    public MyDepartmentRecyclerViewAdapter(List<Department> notes,
                                           DepartmentFragment.OnDepartmentListInteractionListener listener) {
        mValues = notes;
        mListener = listener;
    }
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.fragment_department, parent, false);
        return new ViewHolder(view);
    }
    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        holder.mItem = mValues.get(position);
        holder.mNameView.setText(mValues.get(position).name);
        holder.mView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (null != mListener) {
                    mListener.onDepartmentSelected(holder.mItem);
                }
            }
        });
        if(position %2 == 1)
        {
            holder.itemView.setBackgroundColor(Color.YELLOW);
        }
        else
        {
            holder.itemView.setBackgroundColor(Color.WHITE);
        }
    }
    @Override
    public int getItemCount() {
        return mValues.size();
    }
    public class ViewHolder extends RecyclerView.ViewHolder {
        public final View mView;
        public final TextView mNameView;
        public Department mItem;
        public ViewHolder(View view) {
            super(view);
            mView = view;
            mNameView = view.findViewById(R.id.name);
        }
        @Override
        public String toString() {
            return super.toString() + " '" + mNameView.getText() + "'";
        }
    }
}