package com.suli.suliapp.data.preferences;

import android.content.Context;
import android.content.SharedPreferences;

public class PreferencesManager {

    private Context context;

    public static final String ACCESS_TOKEN = "accessToken";

    public PreferencesManager(Context context) {
        this.context = context;
    }

    public SharedPreferences getSharedPreferences(){
        return context.getSharedPreferences("SuliPreferences", context.MODE_PRIVATE);
    }


    public void saveAccessToken(String accessToken){
        getSharedPreferences().edit().putString(ACCESS_TOKEN, accessToken).apply();
    }

    public String getAccessToken(){
        return getSharedPreferences().getString(ACCESS_TOKEN, "");
    }

    public boolean isLogged(){
        return getSharedPreferences().contains(ACCESS_TOKEN);
    }

    public void logout(){
        getSharedPreferences().edit().clear().apply();
    }

}
