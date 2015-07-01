package com.example.oem.oweme;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by alex on 19/05/15.
 */
public class Contact implements Parcelable{
    private Integer id;
    private String name;
    private Integer amount;

    public Contact(){

    }

    public Contact(Integer ID, String name, Integer amount){
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

    public Integer getAmount() {
        return this.amount;
    }

    public void setAmount(Integer amount) {
        this.amount = amount;
    }

    public void setId(Integer id){
        this.id = id;
    }

    public int getId(){
        return this.id;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(id);
        parcel.writeString(name);
        parcel.writeInt(amount);
    }

    //Only used by ParcelCreator
    public Contact(Parcel in) {
        id = in.readInt();
        name = in.readString();
        amount = in.readInt();
    }
}
