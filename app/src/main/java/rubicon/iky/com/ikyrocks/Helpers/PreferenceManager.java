package rubicon.iky.com.ikyrocks.Helpers;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.Preference;
import android.util.Log;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import com.noodle.Noodle;
import com.noodle.description.Description;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Created by obake on 7/21/2017.
 */

public  class PreferenceManager {


    final static String AppId="com.ikyrocks.rubicoin";
    final static String PREFERENCE_INIT_STATUS="isInit";


    SharedPreferences prefs;
    Context ctx;
    GsonBuilder builder;
    Gson gson;

    public  PreferenceManager(Context c){
        super();
        ctx = c.getApplicationContext();
        
        prefs = ctx.getSharedPreferences(AppId,Context.MODE_PRIVATE);
        builder = new GsonBuilder();
        gson = builder.create();


    }


    public boolean IsInitialized(){
        return prefs.getBoolean(PREFERENCE_INIT_STATUS,false);
    }
    public void SetIsInitialized(boolean action){
        prefs.edit().putBoolean(PREFERENCE_INIT_STATUS,action).apply();

    }



}
