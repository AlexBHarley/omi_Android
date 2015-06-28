package com.example.oem.oweme;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by alex on 19/05/15.
 */
public class ListViewAdapter extends ArrayAdapter<Contact>{
    private final Context context;
    private final ArrayList<Contact> itemsArrayList;
    private static LayoutInflater inflater = null;

    public ListViewAdapter(Context context, ArrayList<Contact> itemsArrayList){
        super(context, R.layout.row, itemsArrayList);

        this.context = context;
        this.itemsArrayList = itemsArrayList;

        inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        //Get rowView from inflater
        View rowView = inflater.inflate(R.layout.row, parent, false);

        //Get text for row
        TextView name = (TextView) rowView.findViewById(R.id.name);
        TextView moneyamount = (TextView) rowView.findViewById(R.id.moneyamount);

        //Set text for values
        name.setText(itemsArrayList.get(position).getName());
        moneyamount.setText(itemsArrayList.get(position).getAmount());

        return rowView;
    }
}