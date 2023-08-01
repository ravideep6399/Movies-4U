package com.example.finalone;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.widget.Toast;

import com.example.finalone.databinding.ActivityMainBinding;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainActivity extends AppCompatActivity implements BottomNavigationView.OnNavigationItemSelectedListener {
    private FragmentManager fragmentManager;
    private Fragment fragment = null;
    public static final String FRAG_TAG_POPULAR = "frag-popular";
    public static final String FRAG_TAG_TOP_RATED = "frag-top-rated";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivityMainBinding binding = DataBindingUtil.setContentView(this,R.layout.activity_main);
        binding.bottomNavigation.setOnNavigationItemSelectedListener(this);

        fragmentManager = getSupportFragmentManager();

        loadPopularMoviesFragment();

    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        if(item.getItemId() == R.id.action_popular){
            loadPopularMoviesFragment();
            return true;
        } else if (item.getItemId() == R.id.action_top_rated) {
            loadTopRatedMoviesFragment();
            return true;
        }else {
            Toast.makeText(this, "Settings", Toast.LENGTH_SHORT).show();
        }
        return false;
    }

    private void loadPopularMoviesFragment() {

        fragment = fragmentManager.findFragmentByTag(FRAG_TAG_POPULAR);
        if(fragment!=null){
            fragmentManager.beginTransaction().show(fragment).commit();
        }else {
            fragmentManager.beginTransaction().replace(R.id.fragment_container,new MovieFragment(),FRAG_TAG_POPULAR).commit();
        }
        hideFragments(FRAG_TAG_TOP_RATED);
    }
    private void loadTopRatedMoviesFragment() {
        fragment = fragmentManager.findFragmentByTag(FRAG_TAG_TOP_RATED);
        if(fragment!=null){
            fragmentManager.beginTransaction().show(fragment).commit();
        }else {
            fragmentManager.beginTransaction().replace(R.id.fragment_container,new MovieFragment(),FRAG_TAG_TOP_RATED).commit();
        }
        hideFragments(FRAG_TAG_POPULAR);
    }
    private void hideFragments(String... tags) {
        for(String tag:tags){
            fragment = fragmentManager.findFragmentByTag(tag);
            if(fragment!=null){
                fragmentManager.beginTransaction().hide(fragment).commit();
            }
        }
    }
}