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
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        // Get the layout inflater
        LayoutInflater inflater = getLayoutInflater();
        // Inflate and set the layout for the dialog
        // Pass null as the parent view because its going in the dialog
        // layout
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

                        //Fix up this function to return personInfoElement
                        //Then pass that to addToListView()

                        createNewListEntry(dialog, view);

                        //addToListView();


                    }
                });
        dialog.show();


    }

    public void createNewListEntry(AlertDialog dialog, View view){



        personInfoElement info = new personInfoElement();

        //infoEntryBox details
        EditText name =  (EditText)view.findViewById(R.id.nameField);
        EditText money = (EditText) view.findViewById(R.id.moneyField);

        //Need to set new list entry details to EditText values above^^^

        TextView newName = (TextView) info.findViewById(R.id.name);
        TextView newMoneyAmount = (TextView) info.findViewById(R.id.moneyamount);

        newMoneyAmount.setText(
                money.getText().toString()
        );
        newName.setText(
                newName.getText().toString()
        );
    }

}
