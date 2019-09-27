package com.fedorrroff.trainee2;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;

import android.app.Fragment;
import android.os.Bundle;
import android.util.Log;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        if (savedInstanceState == null) {
            initViews();
        }
    }

    private void initViews () {
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.fragment_container,Fragment1.newInstance())
                .replace(R.id.fragment2_container, Fragment2.newInstance())
                .commit();
    }

}
