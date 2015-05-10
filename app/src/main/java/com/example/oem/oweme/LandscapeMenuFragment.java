package com.example.oem.oweme;

import android.support.v4.app.Fragment;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by OEM on 11/05/2015.
 */
public class LandscapeMenuFragment extends android.support.v4.app.Fragment {

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View horizMenu = inflater.inflate(R.layout.horizontal_info_frag, container, false);

        return horizMenu;
    }
}
