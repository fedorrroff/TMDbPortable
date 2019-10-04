package com.fedorrroff.tmdbportable.ui.main;

import android.os.Bundle;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;

import com.fedorrroff.tmdbportable.R;
import com.fedorrroff.tmdbportable.di.main.MainComponent;
import com.fedorrroff.tmdbportable.di.main.MainComponentHolder;
import com.fedorrroff.tmdbportable.ui.navigation.Navigator;

import javax.inject.Inject;

public class MainActivity extends AppCompatActivity {

    @Inject
    Navigator navigator;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d("M_mainActivity", "onCreate");
        setContentView(R.layout.activity_main);

        MainComponentHolder mainComponentHolder = MainComponentHolder.getInstance();
        mainComponentHolder.initDaggerComponent(this);
        mainComponentHolder.getMainComponent().inject(this);

        if (savedInstanceState == null) {
            navigator.showSplashScreen();
        }
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
