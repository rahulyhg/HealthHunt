package in.healthhunt.model.preference;

import android.content.Context;
import android.content.SharedPreferences;

import java.util.Set;

/**
 * Created by abhishekkumar on 4/27/18.
 */

public class HealthHuntPreference {

    private final static String pref = "healthhuntpref";

    public static void putString(Context context, String key, String value) {
        SharedPreferences sharedPref = context.getSharedPreferences(pref,Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPref.edit();
        editor.putString(key, value);
        editor.commit();
    }

    public static void putSet(Context context, String key, Set<String> value) {
        SharedPreferences sharedPref = context.getSharedPreferences(pref,Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPref.edit();
        editor.putStringSet(key, value);
        editor.commit();
    }

    public static Set<String> getSet(Context context, String key) {
        SharedPreferences sharedPref = context.getSharedPreferences(pref,Context.MODE_PRIVATE);
        return sharedPref.getStringSet(key, null);
    }

    public static String getString(Context context, String key) {
        SharedPreferences sharedPref = context.getSharedPreferences(pref,Context.MODE_PRIVATE);
        return sharedPref.getString(key, null);
    }

    public static void remove(Context context, String key) {
        SharedPreferences sharedPref = context.getSharedPreferences(pref,Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPref.edit();
        editor.remove(key);
        editor.commit();
    }

    public static void clear(Context context) {
        SharedPreferences sharedPref = context.getSharedPreferences(pref,Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPref.edit();
        editor.clear();
        editor.commit();
    }

    public static void putBoolean(Context context, String key, boolean value) {
        SharedPreferences sharedPref = context.getSharedPreferences(pref,Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPref.edit();
        editor.putBoolean(key, value);
        editor.commit();
    }

    public static boolean getBoolean(Context context, String key) {
        SharedPreferences sharedPref = context.getSharedPreferences(pref,Context.MODE_PRIVATE);
        return sharedPref.getBoolean(key, false);
    }
}
