package com.andcoders.callstobt;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

import com.rs.core.bluetooth.RsBluetoothManager;

public class OutGoingCallReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        RsBluetoothManager mBtManager = null;

        final String dailingNumber = intent.getStringExtra(Intent.EXTRA_PHONE_NUMBER);

        mBtManager = RsBluetoothManager.getSingleton();
        if(mBtManager != null)
        {
            if(mBtManager.getConnectDevice() != null)
            {
                mBtManager.placeCall(dailingNumber);

                // Block the current ongoing call
                this.setResultData(null);
                Toast.makeText(context, "Initiating Call via connected Bluetooth Phone", Toast.LENGTH_SHORT).show();
            }
            else
            {
                Toast.makeText(context, "No Bluetooth Phone is connected", Toast.LENGTH_SHORT).show();
            }

        }

    }
}
