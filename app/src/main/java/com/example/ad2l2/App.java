package com.example.ad2l2;

import android.app.Application;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.example.ad2l2.ui.room.NoteDataBase;
import com.example.ad2l2.utils.PrefsHelper;

public class App extends Application {
public  static  App instance;
public  static PrefsHelper prefsHelper;
public  static NoteDataBase database;

    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;
        prefsHelper = new PrefsHelper(this);
        database = Room.databaseBuilder(this,NoteDataBase.class,"DataBase").allowMainThreadQueries().build();
    }
}
