package com.example.oem.oweme;

import android.app.Activity;
import android.app.AlertDialog;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.res.Configuration;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Parcelable;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.baoyz.swipemenulistview.SwipeMenu;
import com.baoyz.swipemenulistview.SwipeMenuCreator;
import com.baoyz.swipemenulistview.SwipeMenuItem;
import com.baoyz.swipemenulistview.SwipeMenuListView;
import com.software.shell.fab.ActionButton;

import java.util.ArrayList;
import java.util.List;


public class MainActivity extends Activity implements AdapterView.OnItemClickListener, AdapterView.OnItemLongClickListener {
    public ArrayList<Contact> arrayList;
    private DebtDatabase db;
    private SwipeMenuListView listView;
    ActionButton actionButton;
    SwipeMenuCreator creator;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);

        db = new DebtDatabase(this);

        //List<Contact> contactList = db.getAllContacts();

        listView = (SwipeMenuListView) findViewById(R.id.listView);
        listView.setOnItemClickListener(this);
        listView.setOnItemLongClickListener(this);

        final ListViewAdapter adapter = new ListViewAdapter(this, R.layout.row, db.getAllContacts());
        listView.setAdapter(adapter);

        actionButton = (ActionButton) findViewById(R.id.action_button);
        actionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                // Get the layout inflater
                final LayoutInflater inflater = getLayoutInflater();

                final View view = inflater.inflate(R.layout.info_entry_box, null);
                builder.setView(view);
                AlertDialog dialog = builder.create();
                dialog.setTitle("Enter details");
                dialog.setButton(AlertDialog.BUTTON_NEGATIVE, "Cancel",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {

                                dialog.dismiss();
                            }
                        });
                dialog.setButton(AlertDialog.BUTTON_POSITIVE, "OK",
                        new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {

                                EditText name_edit_text = (EditText) view.findViewById(R.id.nameField);
                                EditText money = (EditText) view.findViewById(R.id.moneyField);
                                EditText info_box = (EditText) view.findViewById(R.id.info_box);

                                String name = name_edit_text.getText().toString();
                                Integer amount = Integer.parseInt(money.getText().toString());
                                String info = info_box.getText().toString();

                                Contact contact = new Contact();
                                contact.setName(name);
                                contact.setAmount(amount);

                                db.insertContact(contact);

                                final ListViewAdapter adapter = new ListViewAdapter(getApplicationContext(), R.layout.row, db.getAllContacts());
                                listView.setAdapter(adapter);

                                //adapter.notifyDataSetChanged();
                            }
                        });
                dialog.show();
            }
        });

        SwipeMenuCreator creator = new SwipeMenuCreator() {

            @Override
            public void create(SwipeMenu menu) {
                // create "open" item
                SwipeMenuItem openItem = new SwipeMenuItem(
                        getApplicationContext());
                // set item background
                openItem.setBackground(new ColorDrawable(Color.rgb(0xC9, 0xC9,
                        0xCE)));
                // set item width
                openItem.setWidth(90);
                // set item title
                openItem.setIcon(R.mipmap.ic_mode_edit_white_24dp);
                // set item title fontsize
                openItem.setTitleSize(18);
                // set item title font color
                openItem.setBackground(R.color.editcontact);
                // add to menu
                menu.addMenuItem(openItem);

                // create "delete" item
                SwipeMenuItem deleteItem = new SwipeMenuItem(
                        getApplicationContext());
                // set item background
                deleteItem.setBackground(new ColorDrawable(Color.rgb(0xF9,
                        0x3F, 0x25)));
                // set item width
                deleteItem.setWidth((90));
                // set a icon
                deleteItem.setIcon(R.mipmap.ic_delete_white_24dp);
                // add to menu
                menu.addMenuItem(deleteItem);
            }
        };

// set creator
        listView.setMenuCreator(creator);

        listView.setOnMenuItemClickListener(new SwipeMenuListView.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(int position, SwipeMenu menu, int index) {
                final int position_final = position;
                switch (index) {
                    case 0:
                        Toast.makeText(getApplicationContext(), "Edit position" + Integer.toString(position_final), Toast.LENGTH_SHORT)
                                .show();


                        AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                        // Get the layout inflater
                        final LayoutInflater inflater = getLayoutInflater();

                        final View alert_view = inflater.inflate(R.layout.edit_contact, null);
                        builder.setView(alert_view);
                        AlertDialog dialog = builder.create();
                        dialog.setTitle("Edit Contact");

                        //Positive
                        dialog.setButton(AlertDialog.BUTTON_NEGATIVE, "They Owe Me",
                                new DialogInterface.OnClickListener() {
                                    public void onClick(DialogInterface dialog, int which) {

                                        EditText amount_edit = (EditText) alert_view.findViewById(R.id.edit_amount);
                                        EditText description = (EditText) alert_view.findViewById(R.id.description);

                                        Integer amount_to_update = Integer.parseInt(amount_edit.getText().toString());
                                        String desc = description.getText().toString();

                                        db.editContactFromPosition(position_final, amount_to_update, "TheyOweMe");
                                        //db.editContact(id, amount_to_update, "TheyOweMe");

                                        final ListViewAdapter adapter = new ListViewAdapter(getApplicationContext(), R.layout.row, db.getAllContacts());
                                        listView.setAdapter(adapter);
                                    }
                                });

                        //Negative
                        dialog.setButton(AlertDialog.BUTTON_POSITIVE, "I Owe Them",
                                new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {

                                        EditText amount_edit = (EditText) alert_view.findViewById(R.id.edit_amount);
                                        EditText description = (EditText) alert_view.findViewById(R.id.description);

                                        Integer amount_to_update = Integer.parseInt(amount_edit.getText().toString());
                                        String desc = description.getText().toString();
                                        db.editContactFromPosition(position_final, amount_to_update, "IOweThem");

                                       // db.editContact(id, amount_to_update, "IOweThem");

                                        final ListViewAdapter adapter = new ListViewAdapter(getApplicationContext(), R.layout.row, db.getAllContacts());
                                        listView.setAdapter(adapter);
                                    }
                                });
                        dialog.show();
                        break;
                    case 1:
                        // delete
                        Toast.makeText(getApplicationContext(), "Delete position" + Integer.toString(position), Toast.LENGTH_SHORT)
                                .show();
                        break;
                }
                // false : close the menu; true : not close the menu
                return false;
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
// Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.

        switch (item.getItemId()) {
            case R.id.action_settings:
                return true;
        }
        return true;
    }


    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Contact c = db.getContact(Integer.parseInt(Long.toString(id)));
        Toast.makeText(this, c.getAmount_list(), Toast.LENGTH_SHORT)
        .show();

        Intent graphIntent = new Intent(this, GraphActivity.class);
        graphIntent.putExtra("name", c.getName());
        graphIntent.putExtra("amountList", c.getAmount_list());

        startActivity(graphIntent);

    }
    //Edit contact
    @Override
    public boolean onItemLongClick(AdapterView<?> parent, View view, int position, final long id) {
        AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
        // Get the layout inflater
        final LayoutInflater inflater = getLayoutInflater();

        final View alert_view = inflater.inflate(R.layout.edit_contact, null);
        builder.setView(alert_view);
        AlertDialog dialog = builder.create();
        dialog.setTitle("Edit Contact");

        //Positive
        dialog.setButton(AlertDialog.BUTTON_NEGATIVE, "They Owe Me",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {

                        EditText amount_edit = (EditText) alert_view.findViewById(R.id.edit_amount);
                        EditText description = (EditText) alert_view.findViewById(R.id.description);

                        Integer amount_to_update = Integer.parseInt(amount_edit.getText().toString());
                        String desc = description.getText().toString();

                        db.editContact(id, amount_to_update, "TheyOweMe");

                        final ListViewAdapter adapter = new ListViewAdapter(getApplicationContext(), R.layout.row, db.getAllContacts());
                        listView.setAdapter(adapter);
                    }
                });

        //Negative
        dialog.setButton(AlertDialog.BUTTON_POSITIVE, "I Owe Them",
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                        EditText amount_edit = (EditText) alert_view.findViewById(R.id.edit_amount);
                        EditText description = (EditText) alert_view.findViewById(R.id.description);

                        Integer amount_to_update = Integer.parseInt(amount_edit.getText().toString());
                        String desc = description.getText().toString();

                        db.editContact(id, amount_to_update, "IOweThem");

                        final ListViewAdapter adapter = new ListViewAdapter(getApplicationContext(), R.layout.row, db.getAllContacts());
                        listView.setAdapter(adapter);
                    }
                });
        dialog.show();

        return true;
    }

}

