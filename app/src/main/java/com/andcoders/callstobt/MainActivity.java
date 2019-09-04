 package com.andcoders.callstobt;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.Manifest;
import android.content.ComponentName;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import com.rs.core.bluetooth.RsBluetoothManager;

 public class MainActivity extends AppCompatActivity implements View.OnClickListener, CompoundButton.OnCheckedChangeListener {

    private RsBluetoothManager mBtManager = null;
    private final int PERMISSION_REQUEST_RESULT = 0x55;
    private final String APP_GITHUB_LINK = "https://github.com/RaPrPo/CallsToBt";

     Switch mGlobalReditionSwitch;

     @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        /* Request run time permissions */
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.M) {
            if (checkSelfPermission(Manifest.permission.READ_PHONE_STATE) == PackageManager.PERMISSION_DENIED ||
                checkSelfPermission(Manifest.permission.READ_CONTACTS) == PackageManager.PERMISSION_DENIED ||
                checkSelfPermission(Manifest.permission.PROCESS_OUTGOING_CALLS) == PackageManager.PERMISSION_DENIED ||
                checkSelfPermission(Manifest.permission.WRITE_CALL_LOG) == PackageManager.PERMISSION_DENIED
            )
            {
                String[] permissions = {Manifest.permission.READ_PHONE_STATE,
                                        Manifest.permission.CALL_PHONE,
                                        Manifest.permission.PROCESS_OUTGOING_CALLS};
                requestPermissions(permissions, PERMISSION_REQUEST_RESULT);
            }
        }

        /* Register for State Change of the Switch */
        mGlobalReditionSwitch = (Switch) findViewById(R.id.id_switch_enable_redirection);
        mGlobalReditionSwitch.setOnCheckedChangeListener(this);
        mGlobalReditionSwitch.setChecked(Globals.Prefs.getRedirectionEnblDsbl(this));

        /* Set version text */
         TextView versionTextView = (TextView) findViewById(R.id.id_text_version);
         try {
             PackageInfo pInfo = this.getPackageManager().getPackageInfo(getPackageName(), 0);
             String version = pInfo.versionName;
             versionTextView.setText("V " + version);

         } catch (PackageManager.NameNotFoundException e) {
             e.printStackTrace();
         }

         // For testing
         //CallLogUpdater mCallLogUpdater = new CallLogUpdater(this);
         //mCallLogUpdater.updateCallLog("+919894311381");
    }

     @Override
     public void onClick(View view) {

     }

     @Override
     public void onCheckedChanged(CompoundButton compoundButton, boolean switchState) {
         switch (compoundButton.getId()){
             case R.id.id_switch_enable_redirection:
                 if(switchState)
                 {
                     enableBroadCastReceiver();
                 }else{
                     disableBroadCastReceiver();
                 }
                 Globals.Prefs.setRedirectionEnblDsbl(this, switchState);
                 break;

             default:
                 break;
         }

     }

     @Override
     public boolean onCreateOptionsMenu(Menu menu) {
         getMenuInflater().inflate(R.menu.main_menu, menu);
         return true;
     }

     /****************************************************************************************************
      * Call back function for the
      ***************************************************************************************************/
     @Override
     public boolean onOptionsItemSelected(@NonNull MenuItem item) {
         switch (item.getItemId()) {
             // case R.id.id_menu_settings:
                 // Intent i = new Intent(this, Settings_main.class);
                 // startActivity(i);
                 // Toast.makeText(this, "Settings clicekd", Toast.LENGTH_SHORT).show();
             //    return true;

             case R.id.id_menu_github:
                 Intent i = new Intent(Intent.ACTION_VIEW, Uri.parse(APP_GITHUB_LINK));
                 startActivity(i);
                 return true;

             default:
                 return super.onOptionsItemSelected(item);
         }
     }

     /****************************************************************************************************
      * EnableBroadCast Receiver
      ***************************************************************************************************/
     public void enableBroadCastReceiver(){
         ComponentName receiver = new ComponentName(this, OutGoingCallReceiver.class);
         PackageManager pm = this.getPackageManager();

         pm.setComponentEnabledSetting(receiver,
                 PackageManager.COMPONENT_ENABLED_STATE_ENABLED,
                 PackageManager.DONT_KILL_APP);
     }

     /****************************************************************************************************
      * Disable Broadcast Receiver
      ***************************************************************************************************/
     public void disableBroadCastReceiver(){
         ComponentName receiver = new ComponentName(this, OutGoingCallReceiver.class);
         PackageManager pm = this.getPackageManager();

         pm.setComponentEnabledSetting(receiver,
                 PackageManager.COMPONENT_ENABLED_STATE_DISABLED,
                 PackageManager.DONT_KILL_APP);
     }

 }
