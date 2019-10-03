package com.fedorrroff.tmdbportable.ui.main;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;

import com.fedorrroff.tmdbportable.R;
import com.fedorrroff.tmdbportable.di.ActivityModule;
import com.fedorrroff.tmdbportable.di.DaggerFragmentComponent;
import com.fedorrroff.tmdbportable.di.FragmentComponent;
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

        FragmentComponent fragmentComponent = DaggerFragmentComponent.
                builder().activityModule(new ActivityModule(this)).build();
        fragmentComponent.inject(this);

        if (savedInstanceState == null) {
            navigator.showMainPage();
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
