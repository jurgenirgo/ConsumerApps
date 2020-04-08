package com.jurgen.dtsmoviekatalogconsumerapps;

import android.os.Bundle;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.bumptech.glide.Glide;
import com.jurgen.dtsmoviekatalogconsumerapps.R;

import butterknife.BindView;
import butterknife.ButterKnife;

import static com.jurgen.dtsmoviekatalogconsumerapps.Util.Const.IMAGE_URL;

public class Details extends AppCompatActivity {

    @BindView(R.id.poster)
    ImageView poster;
    @BindView(R.id.backdropImage)
    ImageView backdropImage;
    @BindView(R.id.title)
    TextView titleView;
    @BindView(R.id.release_date)
    TextView release_dateView;
    @BindView(R.id.vote_average)
    TextView vote_averageView;
    @BindView(R.id.overview_text)
    TextView overview_textView;
    @BindView(R.id.toolbardetail)
    Toolbar toolbar;

    boolean isFavorited = false;
    Integer mKategori, Ids;
    String poster_url, backdrop_url, title, release_date, overview_text, movie_id;
    Double vote_average;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);
        ButterKnife.bind(this);

        setSupportActionBar(toolbar);
        // add back arrow to toolbar
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayShowHomeEnabled(true);
            getSupportActionBar().setDisplayShowTitleEnabled(false);
        }

        mKategori = getIntent().getIntExtra("KATEGORI", 0);
        movie_id = getIntent().getStringExtra("MOVIE_ID");
        poster_url = getIntent().getStringExtra("POSTER_URL");
        backdrop_url = getIntent().getStringExtra("BACKDROP_URL");
        title = getIntent().getStringExtra("TITLE");
        release_date = getIntent().getStringExtra("RELEASE_DATE");
        vote_average = getIntent().getDoubleExtra("VOTE_AVERAGE", 0);
        overview_text = getIntent().getStringExtra("OVERVIEW");

        Glide.with(this).load(IMAGE_URL + poster_url).into(poster);
        Glide.with(this).load(IMAGE_URL + backdrop_url).into(backdropImage);

        titleView.setText(title);
        release_dateView.setText(release_date);
        vote_averageView.setText(String.valueOf(vote_average));
        overview_textView.setText(overview_text);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        //handle arrow click here
        if (item.getItemId() == android.R.id.home) {
            finish(); // close this activity and return to preview activity (if there is any)
        }
        return super.onOptionsItemSelected(item);
    }
}
