package com.example.ettie.teachersroom;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

/**
 * Created by Ettie on 6/29/2015.
 * Method from: http://www.android-ios-tutorials.com/android/android-custom-listview-example/
 */
public class ListEntryAdapter extends BaseAdapter {

    Context context;

    protected List<Entry> listEntry;
    LayoutInflater inflater;

    public ListEntryAdapter(Context context, List<Entry> listEntry) {
        this.listEntry = listEntry;
        this.inflater = LayoutInflater.from(context);
        this.context = context;
    }

    public int getCount() {
        return listEntry.size();
    }

    public Entry getItem(int position) {
        return listEntry.get(position);
    }

    public long getItemId(int position) {
        return listEntry.get(position).getEntryId();
    }

    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        if (convertView == null) {

            holder = new ViewHolder();
            convertView = this.inflater.inflate(R.layout.custom_list_view,
                    parent, false);

            holder.txtTitle = (TextView) convertView
                    .findViewById(R.id.tvPostTitle);
            holder.txtBody = (TextView) convertView
                    .findViewById(R.id.tvPostBody);
            holder.txtInfo = (TextView) convertView
                    .findViewById(R.id.tvPosterInfo);

            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        Entry entry = listEntry.get(position);
        holder.txtTitle.setText(entry.getEntryTitle());
        holder.txtBody.setText(entry.getEntryBody());
        holder.txtInfo.setText("Posted by " + entry.getEntryPoster() +
            " on " + entry.getEntryTime() +
            ", tagged as: " + entry.getEntryTag());

        return convertView;
    }

    private class ViewHolder {
        TextView txtTitle;
        TextView txtBody;
        TextView txtInfo;
        //ImageView img;
    }

}

