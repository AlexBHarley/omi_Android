package com.example.oem.oweme;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;

/**
 * Created by alex on 28/06/15.
 */
public class DebtDatabase extends SQLiteOpenHelper{
    public static final String DATABASE_NAME = "debtdb.db";
    public static final String CONTACTS_TABLE = "contacts";
    public static final String CONTACTS_COLUMN_ID = "id";
    public static final String CONTACTS_COLUMN_NAME = "name";
    public static final String CONTACTS_COLUMN_AMOUNT = "amount";

    public DebtDatabase(Context context){
        super(context, DATABASE_NAME, null, 1);
    }
    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE " + CONTACTS_TABLE +
                "(" + CONTACTS_COLUMN_ID + " INTEGER PRIMARY KEY" +
                ", " + CONTACTS_COLUMN_NAME + " TEXT," +
                " " + CONTACTS_COLUMN_AMOUNT + " INTEGER)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }

    public void insertContact(String name, Integer amount){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(CONTACTS_COLUMN_NAME, name);
        cv.put(CONTACTS_COLUMN_AMOUNT, amount);
        db.insert(CONTACTS_TABLE, null, cv);
        db.close();
    }

    public ArrayList<Contact> getAllContacts(){
        ArrayList<Contact> contactArrayList= new ArrayList<Contact>();

        String query = "SELECT * FROM " + CONTACTS_TABLE;
        SQLiteDatabase sqLiteDatabase = this.getReadableDatabase();
        Cursor c = sqLiteDatabase.rawQuery(query, null);

        if(c.moveToFirst()){
            do{
                Contact contact = new Contact();
                contact.setId(Integer.parseInt(c.getString(0)));
                contact.setName(c.getString(1));
                contact.setAmount(Integer.parseInt(c.getString(2)));
                contactArrayList.add(contact);
            } while(c.moveToNext());
        }
        c.close();
        sqLiteDatabase.close();
        return contactArrayList;
    }
}
