package com.example.ad2l2.ui.home;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.io.Serializable;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;
@Entity
public class HomeViewModel implements Serializable {
    @PrimaryKey (autoGenerate = true)
    private  int id ;
    private String title;
    private String description;
//  private DateFormat dateFormat= new SimpleDateFormat("dd MMMM HH : mm");
// private String date = dateFormat.format(new Date());

    public HomeViewModel(String title, String description) {
        this.title = title;
        this.description = description;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

 //   public DateFormat getDateFormat() {
   //     return dateFormat;
    //}

    //public void setDateFormat(DateFormat dateFormat) {
     //   this.dateFormat = dateFormat;
    //}

  //  public String getDate() {
    //  return date;
    //}

    //public void setDate(String date) {
      //  this.date = date;
    //}

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }



}