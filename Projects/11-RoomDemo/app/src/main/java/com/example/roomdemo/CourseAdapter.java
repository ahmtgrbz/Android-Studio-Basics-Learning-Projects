package com.example.roomdemo;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class CourseAdapter extends RecyclerView.Adapter<CourseAdapter.MyViewHolder> {
    private List<Course> courses;
    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView code, name;
        public MyViewHolder(View view) {
            super(view);
            code = view.findViewById(R.id.code);
            name = view.findViewById(R.id.name);
        }
    }
    public CourseAdapter(List<Course> courses) {
        this.courses = courses;
    }
    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.course_row, parent, false);
        return new MyViewHolder(itemView);
    }
    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        Course course = courses.get(position);
        holder.code.setText(course.code);
        holder.name.setText(course.name);
    }
    @Override
    public int getItemCount() {
        return courses.size();
    }
}