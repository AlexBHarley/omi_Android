package com.example.oem.oweme;

import android.app.AlertDialog;

import android.app.Dialog;
import android.content.DialogInterface;
import android.content.res.Configuration;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.util.ArrayList;


public class MainActivity extends ActionBarActivity {

    ArrayList<String> listItems=new ArrayList<String>();

    //DEFINING A STRING ADAPTER WHICH WILL HANDLE THE DATA OF THE LISTVIEW
    ArrayAdapter<String> adapter;

    //RECORDING HOW MANY TIMES THE BUTTON HAS BEEN CLICKED
    int clickCounter=0;
}

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_main);


        int screenOrientation = getResources().getConfiguration().orientation;

        if(screenOrientation == Configuration.ORIENTATION_PORTRAIT){
            setContentView(R.layout.activity_main);
        } else {
            setContentView(R.layout.activity_main_landscape);
        }
        adapter=new ArrayAdapter<String>(this,
                android.R.layout.simple_list_item_1,
                listItems);
        listItems.setAdapter(adapter);
    }



    @Override
    public void onCreate(Bundle icicle) {
        super.onCreate(icicle);
        setContentView(R.layout.main);

    }

    //METHOD WHICH WILL HANDLE DYNAMIC INSERTION
    public void addItems(View v) {
        listItems.add("Clicked : "+clickCounter++);
        adapter.notifyDataSetChanged();
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

                        //Creates new personInfoElement to add to listview
                        personInfoElement info = new personInfoElement();
                        View listElement = inflater.inflate(R.layout.person_info_element, null);

                        //Adds info from prompt to list entry. Hopefully
                        info.createNewListEntry(listElement,dialog, view, info);

                        addToListView(info);
                    }
                });
        dialog.show();


    }
    /*
    public personInfoElement createNewListEntry(View listElement, DialogInterface dialog,
                                                View view, personInfoElement info){

        //infoEntryBox details
        EditText name =  (EditText)view.findViewById(R.id.nameField);
        EditText money = (EditText) view.findViewById(R.id.moneyField);

        //Need to set new list entry details to EditText values above^^^

        TextView newName = (TextView)listElement.findViewById(R.id.name);
        TextView newMoneyAmount = (TextView) listElement.findViewById(R.id.moneyamount);



        info.giveValues(name, newName, money, newMoneyAmount);

        return info;
    }
    */

    public void addToListView(personInfoElement infoElement){

        ArrayList<String> listItems=new ArrayList<String>();

        //DEFINING A STRING ADAPTER WHICH WILL HANDLE THE DATA OF THE LISTVIEW
        ArrayAdapter<String> adapter;

        //RECORDING HOW MANY TIMES THE BUTTON HAS BEEN CLICKED
        int clickCounter=0;

        adapter=new ArrayAdapter<String>(this,
                R.layout.person_info_element,
                    listItems);
            setListAdapter(adapter);


        //METHOD WHICH WILL HANDLE DYNAMIC INSERTION
        public void addItems(View v) {
            listItems.add("Clicked : "+clickCounter++);
            adapter.notifyDataSetChanged();
        }
}
