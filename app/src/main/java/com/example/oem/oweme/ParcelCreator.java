package com.example.oem.oweme;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by alex on 1/07/15.
 */

public class ParcelCreator implements Parcelable.Creator<Contact>{

    @Override
    public Contact createFromParcel(Parcel parcel) {
        return new Contact(parcel);
    }

    @Override
    public Contact[] newArray(int i) {
        return new Contact[i];
    }
}