package com.eustass.movietrailer.activity;

import android.os.Bundle;

import com.eustass.movietrailer.R;
import com.eustass.movietrailer.adapter.MainAdapter;
import com.eustass.movietrailer.model.MovieModel;
import com.eustass.movietrailer.retrofit.Constant;
import com.eustass.movietrailer.retrofit.MovieService;
import com.eustass.movietrailer.retrofit.RetrofitInstance;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.View;

import android.view.Menu;
import android.view.MenuItem;
import android.widget.ProgressBar;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    private final String TAG ="MainActivity";

    private final MovieService service = RetrofitInstance.getUrl().create(MovieService.class);
    private RecyclerView recyclerView;
    private ProgressBar progressBar;

    private MainAdapter adapter;
    private List<MovieModel.Results> movies = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        setupView();
        setupRecyclerView();
    }

    @Override
    protected void onStart() {
        super.onStart();
        getMoviePopular();
    }

    private void setupView(){
        recyclerView = findViewById(R.id.list_movie);
        progressBar = findViewById(R.id.progress_loading);
    }

    private void setupRecyclerView(){
        adapter = new MainAdapter(movies);

        RecyclerView.LayoutManager layoutManager = new GridLayoutManager(this, 2);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter( adapter );

    }

    private void getMoviePopular() {

        showLoading(true);

        Call<MovieModel> call = service.getPopular(Constant.API_KEY, Constant.LANGUAGE, "1");
        call.enqueue(new Callback<MovieModel>() {
            @Override
            public void onResponse(@NonNull Call<MovieModel> call, @NonNull Response<MovieModel> response) {

                showLoading(false);

                if (response.isSuccessful()){
                    MovieModel movie = response.body();
                    assert movie != null;
                    showMovie(movie);
                }
            }

            @Override
            public void onFailure(@NonNull Call<MovieModel> call, @NonNull Throwable t) {
                showLoading(false);
                Log.d(TAG, t.toString());
            }
        });
    }

    private void showLoading(Boolean loading){
        if (loading){
            progressBar.setVisibility(View.VISIBLE);
        } else {
            progressBar.setVisibility(View.GONE);
        }
    }

    private void showMovie(MovieModel movie){
        movies = movie.getResults();
        adapter.setData(movies);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

}