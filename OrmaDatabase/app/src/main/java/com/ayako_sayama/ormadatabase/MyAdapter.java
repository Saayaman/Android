package com.ayako_sayama.ormadatabase;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.github.gfx.android.orma.Relation;
import com.github.gfx.android.orma.widget.OrmaListAdapter;

/**
 * Created by ayako_sayama on 2017-03-15.
 */

class MyAdapter extends OrmaListAdapter<Todo> {

    public MyAdapter(@NonNull Context context, @NonNull Relation<Todo, ?> relation) {
        super(context, relation);
    }


    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view;
        if (convertView == null) {
            view = getLayoutInflater().inflate(android.R.layout.simple_list_item_2, null);
        } else {
            view = convertView;
        }

        Todo todo = getItem(position);
        TextView tvTitle = (TextView) view.findViewById(android.R.id.text1);
        if (todo.title != null) tvTitle.setText(todo.id + ":" + todo.title);
        TextView tvContent = (TextView) view.findViewById(android.R.id.text2);
        if (todo.content != null) tvContent.setText(todo.content);

        return view;
    }


    public void testUpdate(int position) {
        OrmaDao dao = new OrmaDao(getContext());
        Todo todo = getItem(position);
        todo.content = todo.content + ":";
        dao.update(todo);
    }

    public void testDelete(int position) {
        OrmaDao dao = new OrmaDao(getContext());
        Todo todo = getItem(position);
        dao.delete(todo);
    }
}
