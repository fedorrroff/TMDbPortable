package com.fedorrroff.trainee2;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;

public class Fragment2 extends Fragment {

    private TextView tv;

    private BroadcastReceiver broadcastReceiver;

    public static Fragment2 newInstance () {

        Fragment2 myFragment = new Fragment2();

        return myFragment;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_2, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        tv = view.findViewById(R.id.tv_fragment_2);

        Log.d("M_", "onViewCreated: NULL");
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public void onResume() {
        super.onResume();

        attachReceiver();
    }

    @Override
    public void onPause() {
        super.onPause();
        detachReceiver();
    }

    private void attachReceiver() {

        IntentFilter customFilter = new IntentFilter("POHER");

        BroadcastReceiver broadcastReceiver = new BroadcastReceiver() {
            @Override
            public void onReceive(Context context, Intent intent) {
                String msg = intent.getStringExtra("STR");

                tv.setText(msg);
            }
        };

        getContext().registerReceiver(broadcastReceiver, customFilter);
    }

    private void detachReceiver() {
        LocalBroadcastManager.getInstance(getContext()).unregisterReceiver(broadcastReceiver);
    }
}
