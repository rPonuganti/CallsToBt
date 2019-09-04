package com.andcoders.callstobt;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.telecom.Call;
import android.widget.Toast;

import com.rs.core.bluetooth.RsBluetoothManager;

public class OutGoingCallReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        RsBluetoothManager mBtManager = null;
        CallLogUpdater mCallLogUpdater;

        final String dailingNumber = intent.getStringExtra(Intent.EXTRA_PHONE_NUMBER);

        mCallLogUpdater = new CallLogUpdater(context);


        mBtManager = RsBluetoothManager.getSingleton();
        if(mBtManager != null)
        {
            if(mBtManager.getConnectDevice() != null)
            {
                mBtManager.placeCall(dailingNumber);

                // Block the current ongoing call
                this.setResultData(null);
                Toast.makeText(context, "Initiating Call via connected Bluetooth Phone", Toast.LENGTH_SHORT).show();

                /* Update Call log */
                mCallLogUpdater.updateCallLog(dailingNumber);
            }
            else
            {
                Toast.makeText(context, "No Bluetooth Phone is connected", Toast.LENGTH_SHORT).show();
            }

        }

    }
}
