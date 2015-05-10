package com.example.oem.oweme;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by OEM on 10/05/2015.
 */
public class PortraitMenuFragment extends Fragment{
    /*
    protected static LandscapeFragment newInstance(){
        LandscapeFragment landscapeFragment = new LandscapeFragment();
        return landscapeFragment;
    }
    */

    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState){
        View mainMenu = inflater.inflate(R.layout.portrait_menu_fragment, container, false);
        return mainMenu;
    };
}

