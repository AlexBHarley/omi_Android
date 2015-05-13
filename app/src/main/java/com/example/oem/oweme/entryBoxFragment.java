package com.example.oem.oweme;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
The box that pops up when you want to enter a name and $ amount to add
 */
public class entryBoxFragment extends Fragment{

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View entryBox = inflater.inflate(R.layout.entry_box_fragment, container, false);
        return entryBox;
    }
}
