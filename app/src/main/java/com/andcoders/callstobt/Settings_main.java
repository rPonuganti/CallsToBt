package com.andcoders.callstobt;

import android.app.Activity;
import android.content.ComponentName;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.widget.CompoundButton;
import android.widget.Switch;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class Settings_main extends AppCompatActivity implements CompoundButton.OnCheckedChangeListener {

    Switch mGlobalReditionSwitch;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.settings_main);

    }

    @Override
    public void onCheckedChanged(CompoundButton compoundButton, boolean b) {



    }

}
