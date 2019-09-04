package com.andcoders.callstobt;

import android.content.ContentValues;
import android.content.Context;
import android.provider.BaseColumns;
import android.provider.CallLog;
import android.util.Log;

public class CallLogUpdater {
    private Context mPresenter;
    private String mPhoneNum;
    private long mIdLastOutGoingCall;

    /******************************************************************************************************
     * Constrcutor
     * @param context
     ****************************************************************************************************/
    public CallLogUpdater(Context context){
        mPresenter = context;
    }

    /*******************************************************************************************************
     *  Update Call log
     *  Register for Changes in the CallLog.
     ******************************************************************************************************/
    public void updateCallLog(String phNum){

        mPhoneNum = phNum;

        ContentValues updatedLogValue = new ContentValues();

        updatedLogValue.put(android.provider.CallLog.Calls.NUMBER, mPhoneNum);
        updatedLogValue.put(CallLog.Calls.DATE, System.currentTimeMillis());
        updatedLogValue.put(CallLog.Calls.DURATION, 0);
        updatedLogValue.put(CallLog.Calls.TYPE, CallLog.Calls.OUTGOING_TYPE);
        updatedLogValue.put(CallLog.Calls.NEW, 1);
        updatedLogValue.put(CallLog.Calls.CACHED_NAME, "");
        updatedLogValue.put(CallLog.Calls.CACHED_NUMBER_LABEL, "");

        mPresenter.getContentResolver().insert(CallLog.Calls.CONTENT_URI, updatedLogValue);
    }


}
