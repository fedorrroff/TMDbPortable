package com.fedorrroff.tmdbportable.ui.main;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.fedorrroff.tmdbportable.R;

public class MainActivity extends AppCompatActivity {

    public static final int FRAGMENT_CONTAINER = R.id.fl_toReplace;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initViews();
    }

    private void initViews() {
        getSupportFragmentManager()
                .beginTransaction()
                .add(FRAGMENT_CONTAINER, new MainPageFragment())
                .addToBackStack(null)
                .commit();
    }
}
