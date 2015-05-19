package com.example.oem.oweme;

import android.app.AlertDialog;

import android.content.DialogInterface;
import android.content.res.Configuration;
import android.provider.Settings;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;

import java.util.ArrayList;


public class MainActivity extends ActionBarActivity {
    public ArrayList<Item> itemsArray;
    public ArrayAdapter<String> adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        int screenOrientation = getResources().getConfiguration().orientation;

        if(screenOrientation == Configuration.ORIENTATION_PORTRAIT){
            setContentView(R.layout.activity_main);
        } else {
            setContentView(R.layout.activity_main_landscape);
        }




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
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

        //The pop up box that you add name and $ amount to
    public void makeInfoEntryBox(View v){

        ArrayList itemsArray = new ArrayList<Item>();
        ArrayAdapter adapter = new ArrayAdapter<String>(this, R.layout.row,itemsArray);

        ListView listView = (ListView) findViewById(R.id.peopleListView);
        listView.setAdapter(adapter);

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

                        EditText name = (EditText)view.findViewById(R.id.nameField);
                        EditText money = (EditText)view.findViewById(R.id.moneyField);
                        String nameString = name.getText().toString();
                        String moneyString = money.getText().toString();

                        //generateData();

                        addItemToList(nameString, moneyString);
                        //adapter.notifyDataSetChanged();

                        /*
                        //Creates new personInfoElement to add to listview
                        personInfoElement info = new personInfoElement();
                        View listElement = inflater.inflate(R.layout.row, null);

                        //Adds info from prompt to list entry. Hopefully
                        info.createNewListEntry(listElement,dialog, view, info);

                        addToListView(info);
                        */
                    }
                });
        dialog.show();


    }

    protected void addItemToList(String name, String money){
        itemsArray.add(new Item(name, money));
        adapter.notifyDataSetChanged();

    }

    /*


        ArrayList<String> listItems=new ArrayList<String>();

        //DEFINING A STRING ADAPTER WHICH WILL HANDLE THE DATA OF THE LISTVIEW
        ArrayAdapter<String> adapter;

        //RECORDING HOW MANY TIMES THE BUTTON HAS BEEN CLICKED
        int clickCounter=0;

        adapter=new ArrayAdapter<String>(this,
                R.layout.row,
                    listItems);
            setListAdapter(adapter);


        //METHOD WHICH WILL HANDLE DYNAMIC INSERTION
        public void addItems(View v) {
            listItems.add("Clicked : "+clickCounter++);
            adapter.notifyDataSetChanged();
        }
        */


}
