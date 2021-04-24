package com.example.ad2l2.ui.room;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.example.ad2l2.ui.home.HomeViewModel;

import java.util.List;

@Dao
public  interface  NoteDao {
    @Query("SELECT * FROM homeviewmodel ")
    LiveData<List<HomeViewModel>> getNotes();
    @Insert
    void  insert(HomeViewModel model);

    @Delete
    void delete(HomeViewModel model);
    @Update
    void  update (HomeViewModel model);


    @Query("SELECT * FROM homeviewmodel ORDER by description ASC")
    List<HomeViewModel> getAllBySort();

    @Query("SELECT * FROM homeviewmodel ORDER by description DESC")
    List<HomeViewModel> getAllBySortRes();

    @Query("DELETE FROM homeviewmodel")
    void deleteAll();

}
