package com.example.oem.oweme;

import java.util.ArrayList;

/**
 * Created by alex on 19/05/15.
 */
public class Contact{
    private int id;
    private String name;
    private Integer amount;
    private String amount_list;

    public Contact(){
        this.id = id;
        this.name = name;
        this.amount = amount;
        this.amount_list = "";
    }

    public Contact(int Id, String name, Integer amount){
        this.id = Id;
        this.name = name;
        this.amount = amount;
        this.amount_list = amount.toString();
    }

    public Contact(int Id, String name, Integer amount, String amount_list){
        this.id = Id;
        this.name = name;
        this.amount = amount;
        this.amount_list = amount_list;
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
        if(amount_list.length() > 0) {
            amount_list.concat("," + amount.toString());
        } else {
            amount_list.concat(amount.toString());
        }
    }

    public String getAmount_list(){
        return this.amount_list;
    }

    public void setId(int id){
        this.id = id;
    }

    public int getId(){
        return this.id;
    }

}
