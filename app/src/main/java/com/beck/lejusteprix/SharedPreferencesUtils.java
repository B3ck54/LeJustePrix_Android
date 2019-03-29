package com.beck.lejusteprix;

import android.content.Context;
import android.content.SharedPreferences;

//import com.google.gson.Gson;


public class SharedPreferencesUtils {

    // Stocker les sharedPreferences
    public final static String GAME_DATA = "GameData";
    public final static String CleNom = "CleNom";
    public final static String CleScore = "CleScore";
    public final static String CleTimer = "CleTimer";

    //private final static Gson gson = new Gson();

    /**
     * Concerne Sauvegarde du pseudo
     * @param context
     * @param nom
     */
    public static void sauvegarderNom (Context context,String nom){
        SharedPreferences sharedPreferences = context.getSharedPreferences(GAME_DATA,Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
            editor.putString(CleNom,nom);
            editor.apply();
    }

    public static String recupererNom (Context context){
        SharedPreferences sharedPreferences = context.getSharedPreferences(GAME_DATA, Context.MODE_PRIVATE);
        return sharedPreferences.getString(CleNom,"");
    }

    /*******************************************************************************************************/



}
