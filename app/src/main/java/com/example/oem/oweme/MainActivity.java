package com.example.oem.oweme;

import android.app.Activity;
import android.app.AlertDialog;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.res.Configuration;
import android.os.Parcelable;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;


public class MainActivity extends ActionBarActivity{
    public ArrayList<Contact> arrayList;
    private DebtDatabase db;
    private ListView listView;
    ListViewAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);

        /*
        int screenOrientation = getResources().getConfiguration().orientation;

        if(screenOrientation == Configuration.ORIENTATION_PORTRAIT){
            setContentView(R.layout.activity_main);
        } else {
            setContentView(R.layout.activity_main_landscape);
        }
        */

        db = new DebtDatabase(this);

        db.insertContact(new Contact(1, "Ben", -10));
        db.insertContact(new Contact(2, "John", 12));
        db.insertContact(new Contact(3, "Susie", -5));
        db.insertContact(new Contact(3, "Martin", 13));
        db.insertContact(new Contact(3, "Joe", 9));
        db.insertContact(new Contact(3, "Jeffrey", -2));
        db.insertContact(new Contact(3, "Ann", 1));
        db.insertContact(new Contact(3, "Fred", 8));



        List<Contact> contactList = db.getAllContacts();
        for (Contact ti : contactList) {
            String log = "Id: " + ti.getId() + " , Body: " + ti.getName() +
                    " , Priority: " + ti.getAmount();
            Log.d("Name: ", log);
        }



        listView = (ListView) findViewById(R.id.listView);
        ListViewAdapter adapter = new ListViewAdapter(this, R.layout.row, contactList);
        listView.setAdapter(adapter);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
// Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.

        switch (item.getItemId()) {
            //noinspection SimplifiableIfStatement
            case R.id.addContact:
                AlertDialog.Builder builder = new AlertDialog.Builder(this);
                // Get the layout inflater
                final LayoutInflater inflater = getLayoutInflater();

                final View view = inflater.inflate(R.layout.info_entry_box, null);
                builder.setView(view);
                AlertDialog dialog = builder.create();
                dialog.setTitle("Enter details");
                dialog.setButton(AlertDialog.BUTTON_NEGATIVE, "Cancel",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {

                                dialog.dismiss();
                            }
                        });
                dialog.setButton(AlertDialog.BUTTON_POSITIVE, "OK",
                        new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {

                                EditText name_edit_text = (EditText) view.findViewById(R.id.nameField);
                                EditText money = (EditText) view.findViewById(R.id.moneyField);
                                String name = name_edit_text.getText().toString();
                                Integer amount = Integer.parseInt(money.getText().toString());

                                //db.insertContact(name, amount);
                                //adapter.notifyDataSetChanged();
                            }
                        });
                dialog.show();
            case R.id.action_settings:
                return true;


        }
        return true;
    }


}
