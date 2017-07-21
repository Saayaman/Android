package com.ayako_sayama.ormadatabase;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    ListView listView;
    MyAdapter myAdapter;
    OrmaDao dao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toast.makeText(getApplicationContext(), "isOnline " + isOnline(this), Toast.LENGTH_SHORT).show();

        // initialize
        dao = new OrmaDao(getApplicationContext());
        listView = (ListView) findViewById(R.id.listView);
        myAdapter = new MyAdapter(this, dao.getTodoRelation());
        listView.setAdapter(myAdapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                myAdapter.testUpdate(position);
            }
        });
        listView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {

            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                myAdapter.testDelete(position);
                //execute the method only for true
                return false;

            }
        });

        findViewById(R.id.addButton).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Todo todo = new Todo();
                todo.title = "Test @" + Integer.toHexString(todo.hashCode());
                todo.content = Long.toString(System.currentTimeMillis());
                long start = System.currentTimeMillis();
                dao.insert(todo);
                Log.e("test", "insert cost " + (System.currentTimeMillis() - start) + "ms");
            }
        });
    }

    public static boolean isOnline(Context context) {
        ConnectivityManager cm =
                (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo netInfo = cm.getActiveNetworkInfo();
        return netInfo != null && netInfo.isConnectedOrConnecting();
    }
}
