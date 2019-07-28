package com.rs.core.bluetooth;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Bluetooth searched device
 *
 * @author
 */
public final class RsBluetoothDevice implements Parcelable {

    public static final String ACTION_FOUND = "com.rs.bluetooth.ACTION_FOUND";//Search for device broadcasts
    public static final String ACTION_SEARCH_END = "com.rs.bluetooth.ACTION_SEARCH_END";
    public static final String ACTION_BT_INIT = "com.rs.bluetooth.ACTION_BT_INIT";
    public static final String ACTION_CONNET_STATE_CHANGE = "com.rs.bluetooth.ACTION_CONNET_STATE_CHANGE";
    public static final String ACTION_BTMUSIC_STATE_CHANGE = "com.rs.bluetooth.ACTION_BTMUSIC_STATE_CHANGE";
    public static final String ACTION_BTPHONESTATE_CHANGE = "com.rs.bluetooth.ACTION_BTPHONE_STATE_CHANGE";
    public static final String ACTION_BTPHONEBOOK_DOWNLOAD_END = "com.rs.bluetooth.ACTION_BTPHONEBOOK_DOWNLOAD_END";
    public static final String ACTION_BTCALL_LOGS_DOWNLOAD_END = "com.rs.bluetooth.ACTION_BTCALL_LOGS_DOWNLOAD_END";
    public static final String ACTION_BTCALL_LOGS = "com.rs.bluetooth.ACTION_BTCALL_LOGS";
    public static final String EXTRA_DEVICE = "extra_device";

    private String mac = "";
    private String name = "";
    private String type = "";
    private int ConnectState = -1;

    public int getConnectState() {
        return ConnectState;
    }

    public void setConnectState(int connectState) {
        ConnectState = connectState;
    }

    public String getMac() {
        return mac;
    }

    public void setMac(String mac) {
        this.mac = mac;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(mac);
        dest.writeString(name);
        dest.writeString(type);
        dest.writeInt(ConnectState);
    }

    public static final Parcelable.Creator<RsBluetoothDevice> CREATOR =
            new Parcelable.Creator<RsBluetoothDevice>() {

                @Override
                public RsBluetoothDevice createFromParcel(Parcel arg0) {
                    String mac = arg0.readString();
                    String name = arg0.readString();
                    String type = arg0.readString();
                    RsBluetoothDevice device = new RsBluetoothDevice();
                    device.setMac(mac);
                    device.setName(name);
                    device.setType(type);
                    device.setConnectState(arg0.readInt());
                    return device;
                }

                @Override
                public RsBluetoothDevice[] newArray(int arg0) {
                    return new RsBluetoothDevice[arg0];
                }

            };

    public String toString() {
        return mac + ":" + name + ":" + type + ":" + ConnectState;
    }

    ;

    public static RsBluetoothDevice ToDevice(String data) {
        if (data == null) return null;
        RsBluetoothDevice device = new RsBluetoothDevice();
        String[] da = data.split(":");
        device.mac = da[0];
        device.name = da[1];
        device.type = da[2];
        device.ConnectState = Integer.parseInt(da[3]);
        return device;
    }
}
