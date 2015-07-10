package com.example.ettie.teachersroom;

import java.util.Date;
import android.app.Fragment;
import android.database.Cursor;
import android.os.Bundle;
import android.view.KeyEvent;
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

    String wholePost;
    String title;
    String message;
    String time;
    String etTag;
    Entry entry;
    DBAdapter dbAdapter;
    String delims;
    EditText post;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.enter_entry_layout, container, false);
      post = (EditText)view.findViewById(R.id.newPost);
        //title.setHint("Title");
       post.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               post.setHint("");
           }
       });
       post.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                post.setHint("Title: Post body");
            }
        });
        /*
        message = (EditText)view.findViewById(R.id.etMessageBody);
        message.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                message.setHint("");
            }
        });
        message.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                title.setHint("Message");
            }
        });*/

        Button btnPost = (Button)view.findViewById(R.id.btnPost);
        btnPost.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               // etTitle = ((EditText)getView().findViewById(R.id.etTitle)).getText().toString();
                //etTitle = title.getText().toString();
                //etMessage = ((EditText)getView().findViewById(R.id.etMessageBody)).getText().toString();
                //etMessage = message.getText().toString();
                wholePost = ((EditText)getView().findViewById(R.id.newPost)).getText().toString();
                delims = ":";
                String[] tokens = wholePost.split(delims);
                entry = new Entry();
                title = tokens[0];
                message = "";
                for (int i = 1; i < wholePost.length(); i++) {
                    message += tokens[i];
                }
                entry.setEntryTitle(title);
                entry.setEntryBody(message);
                time =  new Date().toString();
                entry.setEntryTime(time);
                etTag = title;
                entry.setEntryTag(etTag);

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
                        Toast.makeText(getActivity(), "Entry: " + entry.toString(), Toast.LENGTH_SHORT).show();
                    } while (cursor.moveToNext());
                }
                dbAdapter.close();
            }
        });
        return view;
    }
}
