package com.eustass.movietrailer.adapter;

import android.annotation.SuppressLint;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.eustass.movietrailer.R;
import com.eustass.movietrailer.model.MovieModel;
import com.eustass.movietrailer.retrofit.Constant;
import com.squareup.picasso.Picasso;

import java.util.List;

public class MainAdapter extends RecyclerView.Adapter<MainAdapter.ViewHolder> {

    private final List<MovieModel.Results> results;

    public MainAdapter(List<MovieModel.Results> results) {
        this.results = results;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {
        return new ViewHolder(
                LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.adapter_movie,
                        viewGroup, false)
        );
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int position) {
        MovieModel.Results result = results.get(position);

        viewHolder.text_title.setText( result.getTitle() );
        Picasso.get()
                .load(Constant.POSTER_PATH + result.getPoster_path())
                .placeholder(R.drawable.portrait)
                .error(R.drawable.portrait)
                .into(viewHolder.image_poster);

    }

    @Override
    public int getItemCount() {
        return results.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        ImageView image_poster;
        TextView text_title;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            image_poster = itemView.findViewById(R.id.image_poster);
            text_title = itemView.findViewById(R.id.text_title);

        }
    }

    @SuppressLint("NotifyDataSetChanged")
    public void setData(List<MovieModel.Results> newResult){
        results.clear();
        results.addAll(newResult);
        notifyDataSetChanged();
    }
}
