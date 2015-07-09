package com.example.oem.oweme;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by alex on 28/06/15.
 */
public class DebtDatabase extends SQLiteOpenHelper{
    public static final String DATABASE_NAME = "debtdb.db";
    public static final String CONTACTS_TABLE = "contacts1";
    public static final String CONTACTS_COLUMN_ID = "id";
    public static final String CONTACTS_COLUMN_NAME = "name";
    public static final String CONTACTS_COLUMN_AMOUNT = "amount";
    public static final String CONTACTS_COLUMN_AMOUNT_LIST = "amount_list";


    public DebtDatabase(Context context){
        super(context, DATABASE_NAME, null, 1);
    }
    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE " + CONTACTS_TABLE +
                "(" + CONTACTS_COLUMN_ID + " INTEGER PRIMARY KEY," +
                CONTACTS_COLUMN_NAME + " TEXT," +
                CONTACTS_COLUMN_AMOUNT + " INTEGER," +
                CONTACTS_COLUMN_AMOUNT_LIST + " TEXT)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        if (newVersion == 1) {
            // Wipe older tables if existed
            db.execSQL("DROP TABLE IF EXISTS " + CONTACTS_TABLE);
            // Create tables again
            onCreate(db);
        }
    }

    public void insertContact(Contact contact){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(CONTACTS_COLUMN_NAME, contact.getName());
        cv.put(CONTACTS_COLUMN_AMOUNT, contact.getAmount());
        cv.put(CONTACTS_COLUMN_AMOUNT_LIST, contact.getAmount_list());
        long rowId = db.insertOrThrow(CONTACTS_TABLE, null, cv);
        db.close();
    }

    public List<Contact> getAllContacts(){
        List<Contact> contactArrayList= new ArrayList<Contact>();

        String query = "SELECT * FROM " + CONTACTS_TABLE;
        SQLiteDatabase sqLiteDatabase = this.getReadableDatabase();
        Cursor c = sqLiteDatabase.rawQuery(query, null);

        if(c.moveToFirst()){
            do{
                Contact contact = new Contact();
                contact.setId(c.getInt(0));
                contact.setName(c.getString(1));
                contact.setAmount(c.getInt(2));
                contactArrayList.add(contact);
            } while(c.moveToNext());
        }
        if (c != null)
            c.close();

        return contactArrayList;
    }

    public Contact getContact(int id) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor =  db.rawQuery( "select * from " +CONTACTS_TABLE + " where id="+id+"", null );

        if(cursor != null && cursor.moveToFirst()){
            cursor.moveToFirst();
            Contact contact = new Contact(cursor.getString(1), Integer.parseInt(cursor.getString(2)),
                    cursor.getString(3));
            cursor.close();
            return contact;

        }
        Contact f = new Contact("nuuuuuug", 10);
        return f;
    }
    /*
    public void editContact(int id, int amount){
        SQLiteDatabase db = this.getReadableDatabase();
        db.update(CONTACTS_TABLE, )
    }
    */
}
