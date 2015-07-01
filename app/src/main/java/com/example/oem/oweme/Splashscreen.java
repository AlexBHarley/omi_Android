package com.example.oem.oweme;

import android.app.Activity;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.util.Log;

import java.util.ArrayList;

/**
 * Created by alex on 1/07/15.
 */
public class Splashscreen extends Activity {
    DebtDatabase debtDatabase;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        debtDatabase= new DebtDatabase(this);
        Log.i("Splash_log", "on create");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        new GetData().execute();
    }


    private class GetData extends AsyncTask<Void, Void, Void> {

        ArrayList<Contact> contactList = new ArrayList<Contact>();
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            Log.i("Splash_log", "on pre");
        }

        @Override
        protected Void doInBackground(Void... voids) {
            Log.i("Splash_log", "do in back");

            for(Contact item : debtDatabase.getAllContacts()){
                contactList.add(item);
            }
            Log.i("Splash_log", "do in back2    `");
            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            Log.i("Splash_log", "on post");
            super.onPostExecute(aVoid);

            Intent information = new Intent(Splashscreen.this, MainActivity.class);
            information.putParcelableArrayListExtra("contact_list", contactList);
            startActivity(information);

            finish();
        }
    }
}