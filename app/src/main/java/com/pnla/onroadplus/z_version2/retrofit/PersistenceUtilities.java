package com.pnla.onroadplus.z_version2.retrofit;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.text.TextUtils;

public class PersistenceUtilities {
    /**
     * return preferences from this app
     * @param  context identify contex from call
     */
    public static SharedPreferences getSharedPreferences (Context context) {
        return PreferenceManager.getDefaultSharedPreferences( context );
    }

    /**Clean preferences
     * @param  context identify contex from call
     * @param  key     list of values to be clean*/
    public static void cleanPreferences(Context context,String ... key){
        SharedPreferences.Editor delate = getSharedPreferences(context).edit();
        for (String delatevalue : key){
            if (!TextUtils.isEmpty(delatevalue) && getSharedPreferences(context).contains(delatevalue))
            {
                delate.remove(delatevalue);
            }
        }
        delate.apply();
    }

    /**this method Edit
     * one preference
     * @param key value at edit*/
    public static void editPrefrerence(Context context,String key){
        getSharedPreferences(context)
                .edit()
                .putString(key,"")
                .apply();
    }

    /**
     * this method delate all preferences when is call
     *
     * */
    public static void cleanAllValues(Context context){
        for (String key : getSharedPreferences(context).getAll().keySet() ){
            cleanPreferences(context,new String[]{key});
        }
    }


    public static String CreateorEdit(Context context, String key, String value)
    {
        if (getSharedPreferences(context).contains(key)){
            getSharedPreferences(context).edit()
                    .putString(key,value)
                    .apply();
        }else {
            getSharedPreferences(context).edit()
                    .putString(key, "Escoge una opción")
                    .commit();
        }
        return getSharedPreferences(context).getString(key,"");
    }
    public static int CreateorEdit(Context context, String key, int value)
    {
        if (getSharedPreferences(context).contains(key)){
            getSharedPreferences(context).edit()
                    .putInt(key,value)
                    .apply();
        }else {
            getSharedPreferences(context).edit()
                    .putInt(key,value)
                    .commit();
        }
        return getSharedPreferences(context).getInt(key,0);
    }

    public static boolean CreateorEdit(Context context, String key, boolean value)
    {
        if (getSharedPreferences(context).contains(key)){
            getSharedPreferences(context).edit()
                    .putBoolean(key,value)
                    .apply();
        }else {
            getSharedPreferences(context).edit()
                    .putBoolean(key, false)
                    .commit();
        }
        return getSharedPreferences(context).getBoolean(key,false);
    }

}
