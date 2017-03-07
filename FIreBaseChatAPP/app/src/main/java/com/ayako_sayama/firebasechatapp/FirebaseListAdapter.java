package com.ayako_sayama.firebasechatapp;

import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import com.google.firebase.database.DatabaseReference;

/**
 * Created by ayako_sayama on 2017-03-05.
 */

class FirebaseListAdapter<ChatMessage> extends BaseAdapter {
    public FirebaseListAdapter(MainActivity mainActivity, Class<ChatMessage> chatMessageClass, int list_item, DatabaseReference reference) {
    }

    @Override
    public int getCount() {
        return 0;
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        return null;
    }


}
