package com.example.ettie.teachersroom;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

/**
 * Created by Ettie on 6/30/2015.
 */
//page 101
public class EnterEntryFragment extends Fragment{

    EditText etTitle;
    EditText etMessage;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.enter_entry_layout, container, false);
        return view;
    }
}
