package com.example.oem.oweme;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


public class personInfoElement {

    public View onCreate(LayoutInflater inflater,  ViewGroup container,  Bundle savedInstanceState) {

        View entryBox = inflater.inflate(R.layout.person_info_element, container, false);
        return entryBox;
    }
}