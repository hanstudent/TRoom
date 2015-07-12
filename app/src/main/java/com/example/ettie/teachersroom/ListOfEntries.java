package com.example.ettie.teachersroom;


import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;
import java.util.ArrayList;

//page 214 for more info
public class ListOfEntries extends AppCompatActivity implements AdapterView.OnItemClickListener {

    ArrayList<Entry> entryArrayList;
    ListView listView;
    //Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_of_entries);
        setTitle("Teacher's Room");

        //intent = new Intent(ListOfEntries.this, NewEntry.class);
        //from: http://www.android-ios-tutorials.com/android/android-custom-listview-example/
        entryArrayList = new ArrayList<Entry>();
        Entry gw1 = new Entry("George Washington", "Any ideas?", "Posted by aUser");
        entryArrayList.add(gw1);

        DBAdapter dbAdapter = new DBAdapter(this);
        dbAdapter.open();
        Cursor cursor = dbAdapter.getAllEntries();
        if (cursor.getCount() > 0) {
            cursor.moveToFirst();
            int id;
            String title;
            String body;
            String tag;
            String poster;
            String time;
            Entry entry;
            do {
                id = cursor.getInt(cursor.getColumnIndex(DBAdapter.C_EID));
                title = cursor.getString(cursor.getColumnIndex(DBAdapter.C_ETITLE));
                body = cursor.getString(cursor.getColumnIndex(DBAdapter.C_EBODY));
                tag = cursor.getString(cursor.getColumnIndex(DBAdapter.C_ETAG));
                poster = cursor.getString(cursor.getColumnIndex(DBAdapter.C_EPOSTER));
                time = cursor.getString(cursor.getColumnIndex(DBAdapter.C_ETIME_OF_POST));
                entry = new Entry(title, body, tag, poster, time);
                entryArrayList.add(entry);
            } while (cursor.moveToNext());
        }
        dbAdapter.close();

    //Add entries to listview:
    listView = (ListView) findViewById(R.id.listView);
    ListEntryAdapter adapter = new ListEntryAdapter(this, entryArrayList);
    listView.setAdapter(adapter);
    listView.setOnItemClickListener(this);
    }

    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Entry selectedEntry = entryArrayList.get(position);
        Toast.makeText(this, "You've selected :\n Entry Title : " + selectedEntry.getEntryTitle() +
                "\n Entry Body : "+ selectedEntry.getEntryBody(), Toast.LENGTH_SHORT).show();
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_list_of_entries, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.newPost:
                Intent intent = new Intent(this, NewEntry.class);
                startActivity(intent);
                return true;
            case R.id.about:
                Toast.makeText(this, R.string.BSD, Toast.LENGTH_SHORT).show();
                return true;
            case R.id.contact:
                Toast.makeText(this, R.string.contactInfo, Toast.LENGTH_LONG).show();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}

//Misc. code:
    /*import android.app.Activity;
    import android.app.Fragment;
    import android.app.FragmentManager;
    import android.app.FragmentTransaction;
    import android.support.v4.app.FragmentActivity;
    import java.util.Date;*/
 /*FragmentTransaction ft = getFragmentManager().beginTransaction();
        EnterEntryFragment enter = new EnterEntryFragment();
        ft.add(R.id.bottom, enter, "bottom");
        ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
        ft.commit();
        getSupportFragmentManager().executePendingTransactions();*/
 /* RelativeLayout rLayoutList = (RelativeLayout)findViewById(R.id.rLayout_for_list_of_entries);
        RelativeLayout relativeLayout = (RelativeLayout)findViewById(R.id.rLayout_for_adding_entries);
        rLayoutList.addView(relativeLayout);*/
    /*  Doesn't seem to do anything different than what's already been done:
        FragmentManager fragmentManager = getFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        Fragment fragmentEntry = new Fragment();
        fragmentTransaction.add(R.id.myFragment, fragmentEntry);
        fragmentTransaction.commit();*/
  /* @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_list_of_entries, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.newPost:
                Intent intent = new Intent(this, NewPost.class);
                startActivity(intent);
            case R.id.about:
                Toast.makeText(this, R.string.BSD, Toast.LENGTH_SHORT).show();
                return true;
            case R.id.contact:
                Toast.makeText(this, R.string.contactInfo, Toast.LENGTH_LONG).show();
                return true;
        }
        return false;
    }*/
 /*   listView = (ListView) findViewById(R.id.listView);
        ListEntryAdapter adapter = new ListEntryAdapter(this, entryArrayList);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(this);*/

//Fragment stuff:
//
        /*FragmentManager fragmentManager = getFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        EnterEntryFragment myFrag = new EnterEntryFragment();

// work here to change Activity fragments (add, remove, etc.).  Example here of adding.
        fragmentTransaction.add(R.id.myFragment, myFrag);
        fragmentTransaction.commit();*/