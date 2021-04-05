package com.example.ad2l2.utils;

import android.content.Context;
import android.content.SharedPreferences;

public class PrefsHelper {
    private final  String BOARD_KEY= "board_key";
    private SharedPreferences sharedPreferences = null;

    public PrefsHelper(Context context) {
        sharedPreferences = context.getSharedPreferences("pref", Context.MODE_PRIVATE);

    }
    public  void saveBoardShown( boolean isShown){
        sharedPreferences.edit().putBoolean(BOARD_KEY,isShown).apply();
    }
    public Boolean isBoardShown(){
        return  sharedPreferences.getBoolean(BOARD_KEY,false);
    }
}
