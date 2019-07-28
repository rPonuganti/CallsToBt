package com.rs.core.bluetooth;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import android.content.Context;
import android.os.IBinder;
import android.os.RemoteException;
// import android.os.ServiceManager;


import android.text.TextUtils;
import android.util.Log;
import android.widget.Toast;

public class RsBluetoothManager {

    private IRsBluetoothServer service;
    public static RsBluetoothManager manager = null;
    private String TAG = "RsBluetoothManager";

    private static Context mcontext = null;

    private RsBluetoothManager() {
        //service = IRsBluetoothServer.Stub.asInterface(ServiceManager.getService("rs_bluetooth_service"));

        Method method = null;
        try {
            method = Class.forName("android.os.ServiceManager").getMethod("getService", String.class);
            IBinder binder = (IBinder) method.invoke(null, "rs_bluetooth_service");
            if (binder != null) {
                service = IRsBluetoothServer.Stub.asInterface(binder);
            }
            else{
            }
        } catch (NoSuchMethodException | ClassNotFoundException | IllegalAccessException | InvocationTargetException e) {
            e.printStackTrace();
        }
    }

    public static RsBluetoothManager getSingleton() {
        if (manager == null) {

            manager = new RsBluetoothManager();
        }
        return manager;
    }

    /**
     * Start searching
     */
    public void startDiscovery() {
        try {
            service.startDiscovery();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Stop searching
     */
    public void stopDiscovery() {
        try {
            service.stopDiscovery();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Whether you are searching for devices
     *
     * @return
     */
    public boolean isDiscovery() {
        try {
            return service.isDiscovering();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    /**
     * @return No device is connected null
     */
    public RsBluetoothDevice getConnectDevice() {
        try {
            return RsBluetoothDevice.ToDevice(service.getConnectDevice());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }


    public void playMusic() {
        try {
            service.playMusic();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void stopMusic() {
        try {
            service.stopMusic();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void pauseMusic() {
        try {
            service.pauseMusic();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void playPauseMusic() {
        try {
            service.playPauseMusic();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void nextMusic() {
        try {
            service.nextMusic();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void preMusic() {
        try {
            service.preMusic();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void muteMusic() {
        try {
            service.muteMusic();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void unMuteMusic() {
        try {
            service.unMuteMusic();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public boolean isMusicPlaying() {
        try {
            return service.isMusicPlaying();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    public String getMusicInfo() {
        try {
            return service.getMusicInfo();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public void disConnectDevice(RsBluetoothDevice device) {
        try {
            service.disConnectDevice(device.getMac(), device.getName(),
                    device.getType());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void connectDevice(RsBluetoothDevice device) {
        try {
            service.connectDevice(device.getMac(), device.getName(),
                    device.getType());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void answerPhone() {//Answer
        try {
            service.answerPhone();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void noAnswerPhone() {//Reject
        try {
            service.noAnswerPhone();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void endPhone() {//End call
        try {
            service.endPhone();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void replayPhone() {//Recall
        try {
            service.replayPhone();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void placeCall(String number) {//dial number
        try {
            service.placeCall(number);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void putMsg(String number) {//Dial the extension number
        try {
            service.putMsg(number);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void resetBt() {
        try {
            service.resetBt();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public boolean getBtIsDownLoad() {
        try {
            return service.getBtIsDownLoad();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    public void SwitchVoice() {
        try {
            service.SwitchVoice();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void redia() {
        try {
            service.redia();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void reqAllCallLog() {
        try {
            service.reqAllCallLog();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void reqDiaCallLog() {
        try {
            service.reqDiaCallLog();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void reqMissedCallLog() {
        try {
            service.reqMissedCallLog();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void reqReciveCallLog() {
        try {
            service.reqReceiveCallLog();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void StartDownLoadPhoneBook() {
        try {
            service.downLoadPhoneBook();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public List<Contact> getAllCallLog() {
        try {
            return service.getAllCallLog();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public List<Contact> getAllContact() {
        try {
            return service.getAllContact();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public void reqVolume(String style, int volume) {
        if (TextUtils.isEmpty(style)) {
            return;
        }
        try {
            service.reqVolume(style, volume + "");
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }

    public void openBluetooth() {
        try {
            service.openBluetooth();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void closeBluetooth() {
        try {
            service.openBluetooth();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    /**
     * Get current pairing list
     *
     * @return
     */
    public ArrayList<RsBluetoothDevice> getBondsDevice() {
        Log.e(TAG, "Bluetoothservice--->listBonds start ");
        ArrayList<RsBluetoothDevice> list = new ArrayList<RsBluetoothDevice>();
        IniFile ini = new IniFile("/system/bt_conf.ini");
        int num = ini.getInt("CONFIGURE", "pairlisttotal", 0);
        if (num == 0) return list;
        for (int i = 0; i < num; i++) {
            String addr = ini.getString("PAIRSTLIST", "addr" + i, null);
            if (addr == null) continue;
            if (addr.length() != 12) continue;
            String name = ini.getString("PAIRSTLIST", "name" + i, "noName");
            Log.e("zhonghua", "Bluetoothservice--->listBonds name:" + name + " addr:" + addrConv(addr));
            RsBluetoothDevice device = new RsBluetoothDevice();
            device.setType("2");
            device.setName(name);
            device.setMac(addrConv(addr));
            list.add(device);
        }
        return list;
    }

    private String addrConv(String addr) {
        String newAddr = "";
        for (int i = 0; i < 6; i++) {
            newAddr += addr.substring(2 * i, 2 * i + 2);
            if (i != 5) newAddr += ":";
        }
        return newAddr;
    }


    class IniFile {
        private Pattern _section = Pattern.compile("\\s*\\[([^]]*)\\]\\s*");
        private Pattern _keyValue = Pattern.compile("\\s*([^=]*)=(.*)");
        private Map<String, Map<String, String>> _entries = new HashMap<String, Map<String, String>>();

        public IniFile(String path) {
            load(path);
        }

        public void load(String path) {
            BufferedReader br = null;
            try {
                br = new BufferedReader(new FileReader(path));
                String line;
                String section = null;
                while ((line = br.readLine()) != null) {
                    Matcher m = _section.matcher(line);
                    if (m.matches()) {
                        section = m.group(1).trim();
                    } else if (section != null) {
                        m = _keyValue.matcher(line);
                        if (m.matches()) {
                            String key = m.group(1).trim();
                            String value = m.group(2).trim();
                            Log.e("zhonghua", "key:::for:::::" + key + " value:" + value + " section:" + section);
                            Map<String, String> kv = _entries.get(section);
                            if (kv == null) {
                                Log.e("zhonghua", "_entries.put:::for:::::" + key + " value:" + value + " section:" + section);
                                _entries.put(section, kv = new HashMap<String, String>());
                            }
                            kv.put(key, value);
                        }
                    }
                }
            } catch (IOException e) {

            } finally {
                try {
                    if (br != null) br.close();
                } catch (IOException e) {
                }
            }
        }

        public String getString(String section, String key, String defaultvalue) {
            Map<String, String> kv = _entries.get(section);
            if (kv == null) {
                return defaultvalue;
            }
            String value = kv.get(key);
            return value == null ? defaultvalue : value;
        }

        public int getInt(String section, String key, int defaultvalue) {
            Map<String, String> kv = _entries.get(section);
            if (kv == null) {
                return defaultvalue;
            }
            String value = kv.get(key);
            return value == null ? defaultvalue : Integer.parseInt(value);
        }

        public float getFloat(String section, String key, float defaultvalue) {
            Map<String, String> kv = _entries.get(section);
            if (kv == null) {
                return defaultvalue;
            }
            String value = kv.get(key);
            return value == null ? defaultvalue : Float.parseFloat(value);
        }

        public double getDouble(String section, String key, double defaultvalue) {
            Map<String, String> kv = _entries.get(section);
            if (kv == null) {
                return defaultvalue;
            }
            String value = kv.get(key);
            return value == null ? defaultvalue : Double.parseDouble(value);
        }
    }


}
                                                  
