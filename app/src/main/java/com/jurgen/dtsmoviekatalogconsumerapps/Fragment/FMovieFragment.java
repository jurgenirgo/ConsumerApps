package com.jurgen.dtsmoviekatalogconsumerapps.Fragment;


import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.jurgen.dtsmoviekatalogconsumerapps.Adapter.MovieListAdapter;
import com.jurgen.dtsmoviekatalogconsumerapps.Model.MovieModel;
import com.jurgen.dtsmoviekatalogconsumerapps.R;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

import static com.jurgen.dtsmoviekatalogconsumerapps.Model.MovieModel.COL_BACKDROP_PATH;
import static com.jurgen.dtsmoviekatalogconsumerapps.Model.MovieModel.COL_KATEGORI;
import static com.jurgen.dtsmoviekatalogconsumerapps.Model.MovieModel.COL_MOVIE_ID;
import static com.jurgen.dtsmoviekatalogconsumerapps.Model.MovieModel.COL_OVERVIEW;
import static com.jurgen.dtsmoviekatalogconsumerapps.Model.MovieModel.COL_POSTER_PATH;
import static com.jurgen.dtsmoviekatalogconsumerapps.Model.MovieModel.COL_RELEASE_DATE;
import static com.jurgen.dtsmoviekatalogconsumerapps.Model.MovieModel.COL_TITLE;
import static com.jurgen.dtsmoviekatalogconsumerapps.Model.MovieModel.COL_VOTE_AVERAGE;
import static com.jurgen.dtsmoviekatalogconsumerapps.Model.MovieModel.CONTENT_URI;

/**
 * A simple {@link Fragment} subclass.
 */
public class FMovieFragment extends Fragment {
    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;

    public FMovieFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_fmovie, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ButterKnife.bind(this, view);

        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setHasFixedSize(true);

        recyclerView.setAdapter(new MovieListAdapter(getContext(), dataList(), 0));
    }

    private ArrayList<MovieModel> dataList() {
        ArrayList<MovieModel> data = new ArrayList<>();
        Uri uri = Uri.parse(CONTENT_URI + "");
        Cursor cursor = getActivity().getContentResolver().query(uri, null, null, null, null);

        if (cursor.moveToFirst()) {
            do {
                if (cursor.getInt(cursor.getColumnIndex(COL_KATEGORI)) == 0) {
                    MovieModel model = new MovieModel();
                    model.setMovie_id(cursor.getString(cursor.getColumnIndex(COL_MOVIE_ID)));
                    model.setPosterPath(cursor.getString(cursor.getColumnIndex(COL_POSTER_PATH)));
                    model.setBackdropPath(cursor.getString(cursor.getColumnIndex(COL_BACKDROP_PATH)));
                    model.setOverview(cursor.getString(cursor.getColumnIndex(COL_OVERVIEW)));
                    model.setTitle(cursor.getString(cursor.getColumnIndex(COL_TITLE)));
                    model.setReleaseDate(cursor.getString(cursor.getColumnIndex(COL_RELEASE_DATE)));
                    model.setVoteAverage(cursor.getDouble(cursor.getColumnIndex(COL_VOTE_AVERAGE)));

                    data.add(model);
                }
            } while (cursor.moveToNext());
        }

        return data;
    }
}