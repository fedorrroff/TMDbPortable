package com.fedorrroff.tmdbportable.di;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;

import com.fedorrroff.tmdbportable.ui.main.MainPageFragment;
import com.fedorrroff.tmdbportable.ui.navigation.Navigator;

import dagger.Component;

@Component(modules = ActivityModule.class)
public interface FragmentComponent {

    FragmentActivity activity();

    FragmentManager fragmentManager();

    Navigator navigator();

    void inject(MainPageFragment mainPageFragment);

}
