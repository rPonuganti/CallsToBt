package com.rs.core.util;

import android.util.Log;

public class Logs {

        public static void  Info(String tag,String msg){
                Log.i(tag, msg);
        }
public static void  Error(String tag,String msg){
                Log.e(tag, msg);
        }
}
