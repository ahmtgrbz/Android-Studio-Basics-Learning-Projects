package com.example.mymoviebrowser;

import androidx.recyclerview.widget.RecyclerView;

import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.mymoviebrowser.dummy.DummyContent.DummyItem;

import java.util.List;

/**
 * {@link RecyclerView.Adapter} that can display a {@link DummyItem}.
 * TODO: Replace the implementation with code for your data type.
 */
public class MyMovieRecyclerViewAdapter extends RecyclerView.Adapter<MyMovieRecyclerViewAdapter.ViewHolder> {

    private final List<Movie> mValues;
    private MovieFragment.OnMovieSelected listener;
    int selected;

    public MyMovieRecyclerViewAdapter(List<Movie> items,MovieFragment.OnMovieSelected listener) {
        this.listener = listener;
        mValues = items;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.fragment_movie, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        holder.mItem = mValues.get(position);
        holder.mIdView.setText(position +"");
        holder.mContentView.setText(mValues.get(position).getName());
        holder.mView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(listener != null){
                    listener.movieSelected(holder.mItem);
                    notifyItemChanged(selected);
                    selected = holder.getLayoutPosition();
                    notifyItemChanged(selected);
                }
            }
        });
        holder.itemView.setBackgroundColor(selected == position ? Color.GREEN : Color.TRANSPARENT);
    }

    @Override
    public int getItemCount() {
        return mValues.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public final View mView;
        public final TextView mIdView;
        public final TextView mContentView;
        public Movie mItem;

        public ViewHolder(View view) {
            super(view);
            mView = view;
            mIdView = (TextView) view.findViewById(R.id.item_number);
            mContentView = (TextView) view.findViewById(R.id.content);
        }

        @Override
        public String toString() {
            return super.toString() + " '" + mContentView.getText() + "'";
        }
    }
}