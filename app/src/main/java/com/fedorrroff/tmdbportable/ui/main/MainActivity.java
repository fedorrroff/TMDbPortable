package com.fedorrroff.tmdbportable.ui.main;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;

import com.fedorrroff.tmdbportable.R;

public class MainActivity extends AppCompatActivity {

    public static final int FRAGMENT_CONTAINER = R.id.fl_toReplace;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d("M_mainActivity", "onCreate");
        setContentView(R.layout.activity_main);
        if (savedInstanceState == null) {
            initViews();
        }
    }

    private void initViews() {
        getSupportFragmentManager()
                .beginTransaction()
                .replace(FRAGMENT_CONTAINER, MainPageFragment.newInstance())
                .commit();
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.d("M_mainActivity", "onStart");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.d("M_mainActivity", "onResume");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.d("M_mainActivity", "onStop");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.d("M_mainActivity", "onDestroy");
    }
}
