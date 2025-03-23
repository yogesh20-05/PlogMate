package com.example.megamart;
import android.content.Context;
import android.content.SharedPreferences;

public class MySharedPreferences {
    private Context context;

 SharedPreferences sharedPreferences;
    public MySharedPreferences(Context context) {
        this.context = context;
    }

    public String getData() {
        return sharedPreferences.getString("number", null);
    }

    public void setMyDeta(String number) {
        SharedPreferences.Editor editor = context.getSharedPreferences("mydeta", Context.MODE_PRIVATE).edit();
        editor.putString("number", number);
        editor.apply();

    }



}
