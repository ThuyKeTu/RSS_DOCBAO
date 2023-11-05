package com.example.rss_docbao;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

public class AdapterLV extends BaseAdapter {
    private ArrayList<String> item;
    private Activity activity;

    public AdapterLV(Activity activity, ArrayList<String> item){
        this.item = item;
        this.activity = activity;
    }



    @Override
    public int getCount() {
        return item.size();
    }

    @Override
    public Object getItem(int position) {
        return item.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        LayoutInflater inflater = activity.getLayoutInflater();
        convertView = inflater.inflate(R.layout.viewlist, null);
        TextView title = (TextView) convertView.findViewById(R.id.title);
        title.setText(item.get(position));
        return convertView;
    }
}
