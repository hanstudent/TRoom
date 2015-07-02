package com.example.ettie.teachersroom;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

//page 214 for more info
public class ListOfEntries extends Activity implements AdapterView.OnItemClickListener {

   // private String[] listOfEntries; /* = {"George Washington", "Any ideas?", "Posted by aUser"}*/
    ArrayList<Entry> entryArrayList;
    ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_of_entries);
        setTitle("Teacher's Room");

        //from: http://www.android-ios-tutorials.com/android/android-custom-listview-example/
        entryArrayList = new ArrayList<Entry>();
        Entry gw1 = new Entry("George Washington", "Any ideas?", "Posted by aUser");
        entryArrayList.add(gw1);
        listView = (ListView) findViewById(R.id.listView);
        ListEntryAdapter adapter = new ListEntryAdapter(this, entryArrayList);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(this);
       /* RelativeLayout rLayoutList = (RelativeLayout)findViewById(R.id.rLayout_for_list_of_entries);
        RelativeLayout relativeLayout = (RelativeLayout)findViewById(R.id.rLayout_for_adding_entries);
        rLayoutList.addView(relativeLayout);*/
    /*  Doesn't seem to do anything different than what's already been done:
        FragmentManager fragmentManager = getFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        Fragment fragmentEntry = new Fragment();
        fragmentTransaction.add(R.id.myFragment, fragmentEntry);
        fragmentTransaction.commit();*/
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
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
