package com.example.ettie.teachersroom;

import java.util.Date;
import android.app.Fragment;
import android.database.Cursor;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.Button;
import android.widget.Toast;
/**
 * Created by Ettie on 6/30/2015.
 */
//page 101
public class EnterEntryFragment extends Fragment{

    String etTitle;
    String etMessage;
    String time;
    Entry entry;
    DBAdapter dbAdapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.enter_entry_layout, container, false);


        Button btnPost = (Button)view.findViewById(R.id.btnPost);
        btnPost.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                etTitle = ((EditText)getView().findViewById(R.id.etTitle)).getText().toString();
                etMessage = ((EditText)getView().findViewById(R.id.etMessageBody)).getText().toString();
                entry = new Entry();
                entry.setEntryTitle(etTitle);
                entry.setEntryBody(etMessage);
                time =  new Date().toString();
                entry.setEntryTime(time);

                //save record to database
                dbAdapter = new DBAdapter(getActivity());
                dbAdapter.open();
                dbAdapter.insertEntry(entry);
                dbAdapter.close();

                //get all entries
                DBAdapter dbAdapter = new DBAdapter(getActivity());
                dbAdapter.open();
                Cursor cursor = dbAdapter.getAllEntries();
                if (cursor.getCount() > 0) {
                    cursor.moveToFirst();
                    int id;
                    String title;
                    String body;
                    String poster;
                    String time;
                    Entry entry;
                    do {
                        id = cursor.getInt(cursor.getColumnIndex(DBAdapter.C_EID));
                        title = cursor.getString(cursor.getColumnIndex(DBAdapter.C_ETITLE));
                        body = cursor.getString(cursor.getColumnIndex(DBAdapter.C_EBODY));
                        poster = cursor.getString(cursor.getColumnIndex(DBAdapter.C_EPOSTER));
                        time = cursor.getString(cursor.getColumnIndex(DBAdapter.C_ETIME_OF_POST));
                        entry = new Entry(title, body, poster, time);
                        Toast.makeText(getActivity(), "Entry: " + entry.toString(), Toast.LENGTH_SHORT).show();
                    } while (cursor.moveToNext());
                }
                dbAdapter.close();
            }
        });
        return view;
    }
}
