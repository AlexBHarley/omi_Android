package com.example.oem.oweme;

/**
 * Created by alex on 19/05/15.
 */
public class Item {

    private String name;
    private String amount;

    public Item(String name, String amount){
        super();
        this.name = name;
        this.amount = amount;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }
}
