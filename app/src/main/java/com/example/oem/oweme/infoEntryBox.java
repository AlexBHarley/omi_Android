package com.example.oem.oweme;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

/**
 The box that pops up when you want to enter a name and $ amount to add
 */
public class infoEntryBox{


    public View onCreate(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View entryBox = inflater.inflate(R.layout.info_entry_box, container, false);
        /**
        //Button addPersonInfo = (Button) entryBox.findViewById(R.id.addPersonInfo);

        ////addPersonInfo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //ToDo get info from database and update the listview with details
            }
        });
         */
        return entryBox;

    }
}
