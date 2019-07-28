package com.andcoders.callstobt;

import android.content.Context;
import android.preference.PreferenceManager;
import android.util.Log;

public class Globals {

    public static final boolean DEBUG = false;
    public static final String TAG = "CallsToBtPhone";

    public static void log(String str){
        if(DEBUG){
            Log.d(TAG, str);
        }
    }

    /**
     * Global class to store all the preferences.
     * @author Raghu
     *
     */
    public static class Prefs{
        /**
         * TAG to store Master Enbl/Dsbl Status in Preferences.
         */
        public static final String REDIRECTION_ENBL_DSBL = "REDIRECTION_ENBL_DSBL";

        /********************************************************************
         * Store the Master Enbl/Dsbl status in Preferences.
         */
        public static void setRedirectionEnblDsbl(Context context, boolean status){
            PreferenceManager.getDefaultSharedPreferences(context)
                    .edit()
                    .putBoolean(REDIRECTION_ENBL_DSBL, status)
                    .commit();
        }

        /********************************************************************
         * Get the Master Enbl/Dsbl status from Preference.
         */
        public static boolean getRedirectionEnblDsbl(Context context){
            return PreferenceManager.getDefaultSharedPreferences(context)
                    .getBoolean(REDIRECTION_ENBL_DSBL, true);
        }
    }
}
