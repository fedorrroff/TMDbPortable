package com.fedorrroff.tmdbportable.di.main;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;

import com.fedorrroff.database.DBHelper;
import com.fedorrroff.database.Database;
import com.fedorrroff.tmdbportable.di.api.APIModule;
import com.fedorrroff.api.tmdbApi.Requester;
import com.fedorrroff.tmdbportable.di.database.DatabaseModule;
import com.fedorrroff.tmdbportable.ui.navigation.Navigator;

import dagger.Component;

@Component(modules = {
        ActivityModule.class,
        APIModule.class,
        DatabaseModule.class}
)
public interface MainComponent {

    AppCompatActivity activity();

    FragmentManager fragmentManager();

    Navigator navigator();

    Requester requester();

    DBHelper dBHelper();

    Database database();
}