package com.example.oem.oweme;

import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;

/**
 The main list view fragment of the app. Takes up the whole screen when app is viewed in portrait mode
 and the left side of the screen when viewed in landscape.
 */
public class PortraitMenuFragment extends Fragment{

    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState){
        View mainMenu = inflater.inflate(R.layout.portrait_menu_fragment, container, false);
        return mainMenu;

        ArrayAdapter<View> adapter;


        adapter = new ArrayAdapter<View>(this, R.layout.person_info_element, )
    };






}

