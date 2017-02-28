package com.ayako_sayama.itemtouchhelper;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        setUpRecyclerView();

    }

    private void setUpRecyclerView() {
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(new MyAdapter(this, mylist()));
        recyclerView.setHasFixedSize(true);
        setUpItemTouch();
    }


    private void setUpItemTouch() {

        ItemTouchHelper.SimpleCallback itemTouchHelper = new ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT) {

            @Override
            public boolean onMove(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, RecyclerView.ViewHolder target) {
                return false;
            }

            @Override
            public void onSwiped(RecyclerView.ViewHolder viewHolder, int direction) {
                int swipedPosition = viewHolder.getAdapterPosition();
                MyAdapter adapter = (MyAdapter)recyclerView.getAdapter();
                adapter.remove(swipedPosition);
            }
        };

        ItemTouchHelper touchHelper = new ItemTouchHelper(itemTouchHelper);
        touchHelper.attachToRecyclerView(recyclerView);
    }


    private List<ItemModel> mylist() {
        List<ItemModel> lists = new ArrayList<>();
        lists.add(new ItemModel("Ilove"));
        lists.add(new ItemModel("This is my life"));
        lists.add(new ItemModel("Walk Hard"));
        lists.add(new ItemModel("Comedy"));
        lists.add(new ItemModel("King of Rock"));
        lists.add(new ItemModel("Blah Blah"));
        return lists;
    }
}
