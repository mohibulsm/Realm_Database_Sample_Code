package com.mhpeash.test_realm;

import android.app.Application;

import io.realm.Realm;
import io.realm.RealmConfiguration;

public class MyApplication extends Application {

    private RealmConfiguration config;

    public void onCreate() {
        super.onCreate();
        // initialize Realm
        Realm.init(getApplicationContext());
        // create your Realm configuration
         config = new RealmConfiguration.Builder().deleteRealmIfMigrationNeeded().build();
        Realm.setDefaultConfiguration(config);
    }

}

