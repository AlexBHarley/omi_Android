package com.example.oem.oweme;

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
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;


public class MainActivity extends ActionBarActivity implements AdapterView.OnItemClickListener{
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

        Contact newContact = new Contact("Anreeeeeeeee", 50);
        Contact newContact1 = new Contact("wwwww", 100);
        newContact.setAmount(20);
        db.insertContact(newContact);
        db.insertContact(newContact1);



        List<Contact> contactList = db.getAllContacts();
        for (Contact ti : contactList) {
            String log = "Id: " + ti.getId() + " , Body: " + ti.getName() +
                    " , Priority: " + ti.getAmount();
            Log.d("Name: ", log);
        }



        listView = (ListView) findViewById(R.id.listView);
        listView.setOnItemClickListener(this);
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
                                EditText info_box = (EditText) view.findViewById(R.id.info_box);

                                String name = name_edit_text.getText().toString();
                                Integer amount = Integer.parseInt(money.getText().toString());
                                String info = info_box.getText().toString();

                                Contact contact = new Contact();
                                contact.setName(name);
                                contact.setAmount(amount);

                                db.insertContact(contact);
                                //adapter.notifyDataSetChanged();
                            }
                        });
                dialog.show();
            case R.id.action_settings:
                return true;


        }
        return true;
    }


    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Contact c = db.getContact(Integer.parseInt(Long.toString(id)));
        Toast.makeText(this, c.getAmount_list(), Toast.LENGTH_SHORT)
        .show();

        Intent graphIntent = new Intent(this, GraphActivity.class);
        graphIntent.putExtra("name", c.getName());
        graphIntent.putExtra("amountList", c.getAmount_list());

        startActivity(graphIntent);

    }

    public void editContact(){

    }
}
