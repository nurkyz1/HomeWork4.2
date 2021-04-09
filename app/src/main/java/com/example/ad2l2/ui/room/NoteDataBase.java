package com.example.ad2l2.ui.room;

import androidx.room.Database;
import androidx.room.RoomDatabase;

import com.example.ad2l2.ui.home.HomeViewModel;

@Database(entities = {HomeViewModel.class},version = 1)
public abstract class  NoteDataBase extends RoomDatabase {
    public abstract NoteDao noteDao();
}
