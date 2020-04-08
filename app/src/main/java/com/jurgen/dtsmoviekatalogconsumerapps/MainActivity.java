package com.jurgen.dtsmoviekatalogconsumerapps;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import com.google.android.material.tabs.TabLayout;
import com.jurgen.dtsmoviekatalogconsumerapps.Adapter.FavoriteTabAdapter;
import com.jurgen.dtsmoviekatalogconsumerapps.Fragment.FMovieFragment;
import com.jurgen.dtsmoviekatalogconsumerapps.Fragment.FTVShowFragment;

import java.util.Objects;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.viewPager)
    ViewPager viewPager;
    @BindView(R.id.tabLayout)
    TabLayout tabLayout;

    FavoriteTabAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        adapter = new FavoriteTabAdapter(getSupportFragmentManager());
        adapter.addFragment(new FMovieFragment(), "Movie");
        adapter.addFragment(new FTVShowFragment(), "TV Show");

        viewPager.setAdapter(adapter);
        tabLayout.setupWithViewPager(viewPager);
    }
}
