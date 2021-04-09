package com.example.ad2l2.utils;

import android.content.Context;
import android.content.SharedPreferences;

public class PrefsHelper {

    public  static final String APP_PREFERENCES = "MY SETTINGS";
    public  static  final  String BOOLEAN_FOT_SHOW_OPEN= "SHOW";
    public  static  final  String FOR_NAME = "NAME";
    public static final String SAVE_SORT ="SORT";
    private SharedPreferences sharedPreferences = null;

    public PrefsHelper(Context context) {
        sharedPreferences = context.getSharedPreferences(APP_PREFERENCES, Context.MODE_PRIVATE);

    }
    public  void saveBoardShown( boolean isShown){
        sharedPreferences.edit().putBoolean(BOOLEAN_FOT_SHOW_OPEN,isShown).apply();
    }
    public Boolean isBoardShown(){
        return  sharedPreferences.getBoolean(BOOLEAN_FOT_SHOW_OPEN,false);
    }

    public  void  setForName (String name){
        sharedPreferences.edit().putString(FOR_NAME, name).apply();
    }
    public String getForName(){
        return sharedPreferences.getString(FOR_NAME,"");
    }
    public  void  saveSort(Boolean saveSort){
        sharedPreferences.edit().putBoolean(SAVE_SORT, saveSort).apply();
    }
    public  Boolean getSaveSort(){
        return sharedPreferences.getBoolean(SAVE_SORT,false);
    }

}
