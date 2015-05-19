package com.example.oem.oweme;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

//Currently does nothing. Not sure if will be needed
public class personInfoElement{

    public View onCreate(LayoutInflater inflater,  ViewGroup container,  Bundle savedInstanceState) {

        View entryBox = inflater.inflate(R.layout.row, container, false);
        return entryBox;


    }

    public void giveValues(EditText name, TextView newName, EditText money, TextView newMoney){
        newMoney.setText(
                money.getText().toString()
        );
        newName.setText(
                name.getText().toString()
        );
    }

    public void createNewListEntry(View listElement, DialogInterface dialog,
                                                View view, personInfoElement info){

        //infoEntryBox details
        EditText name =  (EditText)view.findViewById(R.id.nameField);
        EditText money = (EditText) view.findViewById(R.id.moneyField);

        //Need to set new list entry details to EditText values above^^^

        TextView newName = (TextView)listElement.findViewById(R.id.name);
        TextView newMoneyAmount = (TextView) listElement.findViewById(R.id.moneyamount);



        info.giveValues(name, newName, money, newMoneyAmount);


    }
}