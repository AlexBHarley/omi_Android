package com.example.oem.oweme;

import android.app.Dialog;
import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Context;
import android.content.res.Configuration;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;


public class MainActivity extends ActionBarActivity {

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
        Context context = this;
        Dialog infoEntryBox = new Dialog(context);

        infoEntryBox.setContentView(R.layout.info_entry_box);
        infoEntryBox.setTitle("New person details");

        Button addPersonInfo = (Button) findViewById(R.id.addPersonInfo);

    }
    //ToDo this may change when i figure out how to get rid of button when items are in the list
    public View addPerson(View v){
        personInfoElement toBeAdded = new personInfoElement();

        TextView money = (TextView) findViewById(R.id.moneyamount);
        TextView name = (TextView) findViewById(R.id.name);

        EditText moneyField= (EditText) findViewById(R.id.moneyField);
        EditText nameField = (EditText) findViewById(R.id.nameField);

        money.setText(
                moneyField.getText().toString()
        );
        name.setText(
                nameField.getText().toString()
        );

        return v;

    }



}
