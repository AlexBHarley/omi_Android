package com.example.oem.oweme;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by alex on 19/05/15.
 */
public class Contact{
    private int id;
    private String name;
    private int amount;

    public Contact(){

    }

    public Contact(int ID, String name, int amount){
        super();
        this.id = ID;
        this.name = name;
        this.amount = amount;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAmount() {
        return this.amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public void setId(int id){
        this.id = id;
    }

    public int getId(){
        return this.id;
    }

}
