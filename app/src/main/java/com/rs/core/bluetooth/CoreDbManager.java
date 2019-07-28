package com.rs.core.bluetooth;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;

import com.rs.core.util.DateUtils;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;



public class CoreDbManager {
        private  final String TAG = "CoreDbManager";
        private MDatabaseHelper db;
        SQLiteDatabase sql = null;
        private static class Tables{
                public static final String BT_CONTACTS= "bt_contacts";
                public static final String BT_CONTACTS_STATUS= "bt_contacts_status";
        };
        public CoreDbManager(Context context){
                db = new MDatabaseHelper(context);

        }

        public void changeDBMod(){
                try {
            String command = "chmod -R 777 /data/bt";
            Runtime runtime = Runtime.getRuntime();
            Process proc = runtime.exec(command);
           } catch (IOException e) {
                   e.printStackTrace();
           }
        }
        public void openDB(){
                sql = db.getWritableDatabase();
                changeDBMod();
        }
        private SQLiteDatabase getDB(){
                if(sql == null){
                        sql = db.getWritableDatabase();

                }
                return sql;
        }
        public void closeDb(){
                try {
                        sql.close();
                } catch (Exception e) {
                        e.printStackTrace();
                }

        }

        private void createTable() {
                try {
                        getDB().execSQL("create table if not exists bt_contacts (id integer primary key autoincrement,type text,name text,phone text,sortLetters text,mac text);");
                        getDB().execSQL("create table if not exists bt_contacts_status (id integer primary key,mac text,status integer,update_at DATETIME DEFAULT CURRENT_TIMESTAMP)");
                        closeDB();
                } catch (SQLException ex) {
                        ex.printStackTrace();
                }
        }
        private void dropTable() {
        try {
                getDB().execSQL("DROP TABLE IF EXISTS " + Tables.BT_CONTACTS);
                getDB().execSQL("DROP TABLE IF EXISTS " + Tables.BT_CONTACTS_STATUS);
        } catch (SQLException ex) {
           ex.printStackTrace();
        }
    }
        public void closeDB() {

    }
        public int getContactsCount(String mac){
                int count = 0;
                try{
                Cursor c = getDB().rawQuery("select count(id) sum from bt_contacts where mac = ? ",  new String[]{mac});
                while (c.moveToNext()) {
                        count = c.getInt(c.getColumnIndex("sum"));
                }
                c.close();
                } catch (Exception e) {
                        closeDB();
                        e.printStackTrace();
                }
        return count;
        }
        public String getNameByPhone(String phone,String mac){
                String name = "";
                try{
                        if(phone != null){
                                Cursor c = getDB().rawQuery("select name from bt_contacts where phone = ? and mac = ? limit 1",  new String[]{phone,mac});
                                while (c.moveToNext()) {
                                        name = c.getString(c.getColumnIndex("name"));
                                }
                                c.close();
                        }
                        Log.i(TAG,"getNameByPhone== phone=>"+phone+"   mac=>"+mac+"   name=>"+name);
                } catch (Exception e) {
                        closeDB();
                        Log.i(TAG," getNameByPhone error :phone=>"+phone+"   mac=>"+mac,e);
                }
        return name;
        }
        public ArrayList<Contact> getContacts(String mac,int pageIndex,int pageSize ){
                Log.i(TAG,"mac==>"+mac);
                ArrayList<Contact> contacts = new ArrayList<Contact>();
                try {
                        String sql  = "select type,name,phone,sortLetters from bt_contacts where mac = ? order by id asc limit "+pageSize +" offset "+((pageIndex-1)*pageSize);
                        Log.i(TAG,sql);
                        Cursor c = getDB().rawQuery(sql, new String[]{mac});
                while (c.moveToNext()) {
                        Contact contact = new Contact(c.getString(c.getColumnIndex("type")),c.getString(c.getColumnIndex("name")),c.getString(c.getColumnIndex("phone")),c.getString(c.getColumnIndex("sortLetters")));
                        contacts.add(contact);
                        Log.i(TAG,contact.name+"=>"+contact.phone);
                }
                c.close();
                } catch (Exception e) {
                        closeDB();
                        e.printStackTrace();
                }
        return contacts;
        }
        public void clearContacts(String mac){
                try{
                        getDB().execSQL("delete from bt_contacts where mac= ?",new Object[]{mac});
                } catch (Exception e) {
                        closeDB();
                        e.printStackTrace();
                }
                Log.i(TAG,"clearContacts==>"+mac);
        }
        public void insertContact(Contact contact,String mac){
                try{
                        ContentValues values = new ContentValues(5);
                        values.put("name", contact.name);
                        values.put("type",contact.type);
                        values.put("phone", contact.phone);
                        values.put("sortLetters", contact.sortLetters);
                        values.put("mac",mac);
                        getDB().insert(Tables.BT_CONTACTS, null, values);
                } catch (Exception e) {
                        closeDB();
                        e.printStackTrace();
                }
                Log.i(TAG,"insertContact==>"+mac+"   name=>"+contact.name);
        }
        public void updateBtContactStatus(int  id,int status){
                try{
                        getDB().execSQL("update bt_contacts_status set status =?  where id= ?",new Object[]{status,id});
                } catch (Exception e) {
                        closeDB();
                        e.printStackTrace();
                }
        }
        public void insertBtContactStatus(String mac,int status){
                try{
                        ContentValues values = new ContentValues(4);
                        values.put("mac", mac);
                        values.put("status",status);
                        values.put("mac",mac);
                        values.put("update_at",DateUtils.formatDate("yyyy-MM-dd HH:mm:ss", new Date()));
                        getDB().insert(Tables.BT_CONTACTS_STATUS, null, values);
                } catch (Exception e) {
                        closeDB();
                        e.printStackTrace();
                }
                Log.i(TAG,"insertBtContactStatus==>"+mac);
        }
        public BTContactStatus getBtContactStatus(String mac){
                BTContactStatus btContactStatus = null;
                try{
                        Cursor c = getDB().rawQuery("select id,mac,status,update_at from bt_contacts_status where mac = ? limit 0,1", new String[]{mac});
                        while (c.moveToNext()) {
                                btContactStatus = new BTContactStatus();
                                btContactStatus.id = c.getInt(c.getColumnIndex("id"));
                                btContactStatus.mac = c.getString(c.getColumnIndex("mac"));
                                btContactStatus.status = c.getInt(c.getColumnIndex("status"));
                                btContactStatus.updateAt = c.getString(c.getColumnIndex("update_at"));
                        }
                        c.close();
                } catch (Exception e) {
                        closeDB();
                        e.printStackTrace();
                }
                Log.i(TAG,"getBtContactStatus==>"+mac+"==>");
                return btContactStatus;
        }
        public void deleteBtContactStatus(int id){
                try{
                        getDB().delete(Tables.BT_CONTACTS_STATUS, "id="+id, null);
                } catch (Exception e) {
                        closeDB();
                        e.printStackTrace();
                }
                Log.i(TAG,"deleteBtContactStatus==>"+id);
        }

         private static class MDatabaseHelper extends SQLiteOpenHelper
            {

                 MDatabaseHelper(Context context)
                {
                    super(context, "/data/bt/bt_data", null, 1);
                }
                @Override
                public void onCreate(SQLiteDatabase db) {
                    // TODO Auto-generated method stub
                    try
                    {
                        db.execSQL("create table if not exists bt_contacts (id integer primary key autoincrement,type text,name text,phone text,sortLetters text,mac text);");
                                db.execSQL("create table if not exists bt_contacts_status (id integer primary key,mac text,status integer,update_at DATETIME DEFAULT CURRENT_TIMESTAMP)");
//                      db.execSQL(DATABASE_CREATE);
                    }
                    catch(SQLException e)
                    {
                        e.printStackTrace();
                    }
                }

                @Override
                public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

                }
            }

}
