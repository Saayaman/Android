package com.ayako_sayama.listview;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.IdRes;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

public class MainActivity extends Activity {

    ListView listView;
    MyAdapter adapter;

    Human [] humans = new Human[7];

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        humans[0] = new Human("Ayako", 29);
        humans[1] = new Human("Derrick", 27);
        humans[2] = new Human("Hiroki", 29);
        humans[3] = new Human("Josh", 29);
        humans[4] = new Human("Ike", 23);
        humans[5] = new Human("John", 24);
        humans[6] = new Human("Kyel", 29);

        listView = (ListView) findViewById(R.id.list1);

        adapter = new MyAdapter(this,android.R.layout.simple_expandable_list_item_2, android.R.id.text1, humans);
        listView.setAdapter(adapter);

    }


    private class MyAdapter extends ArrayAdapter<Human>{


        public MyAdapter(@NonNull Context context, @LayoutRes int resource, @IdRes int textViewResourceId, @NonNull Human[] objects) {
            super(context, resource, textViewResourceId, objects);
        }

        @NonNull
        @Override
        public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
             View view = super.getView(position, convertView, parent);
            TextView nameTextView = (TextView) view.findViewById(android.R.id.text1);
            TextView ageTextView = (TextView) view.findViewById(android.R.id.text2);

            nameTextView.setText(humans[position].getName());
            ageTextView.setText(Integer.toString(humans[position].getAge()));

            return view;
        }
    }
}
