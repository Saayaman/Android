package com.ayako_sayama.itemtouchhelperundo;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.util.Log;
import android.view.View;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    RecyclerView recyclerView;
    MyAdapter myAdapter;

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
        final ItemTouchHelper.SimpleCallback itemTouchCallBack = new ItemTouchHelper.SimpleCallback(0,ItemTouchHelper.LEFT) {
            public static final String TAG = "MainActivity";

            @Override
            public boolean onMove(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, RecyclerView.ViewHolder target) {
                return false;
            }

            @Override
            public void onSwiped(RecyclerView.ViewHolder viewHolder, int direction) {
                int swipedPosition = viewHolder.getAdapterPosition();
                myAdapter = (MyAdapter)recyclerView.getAdapter();
                myAdapter.remove(swipedPosition);
            }

            @Override
            public void onChildDraw(Canvas c, RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, float dX, float dY, int actionState, boolean isCurrentlyActive) {
                Log.i(TAG, "onChildDraw: It is Drawn");
                View itemView = viewHolder.itemView;

                Drawable background = new ColorDrawable(Color.RED);
                Drawable xMark = ContextCompat.getDrawable(MainActivity.this,R.drawable.ic_clear_black_24dp);
                xMark.setColorFilter(Color.WHITE, PorterDuff.Mode.SRC_ATOP);
                int xMarkMargin = (int) MainActivity.this.getResources().getDimension(R.dimen.ic_clear_margin);

//                    boolean initiated = true;
                if (actionState == ItemTouchHelper.ACTION_STATE_SWIPE){
                    background.setBounds(itemView.getRight() + (int) dX, itemView.getTop(), itemView.getRight(), itemView.getBottom());
                    background.draw(c);

                    // draw x mark
                    int itemHeight = itemView.getBottom() - itemView.getTop();
                    int intrinsicWidth = xMark.getIntrinsicWidth();
                    int intrinsicHeight = xMark.getIntrinsicWidth();

                    int xMarkLeft = itemView.getRight() - xMarkMargin - intrinsicWidth;
                    int xMarkRight = itemView.getRight() - xMarkMargin;
                    int xMarkTop = itemView.getTop() + (itemHeight - intrinsicHeight)/2;
                    int xMarkBottom = xMarkTop + intrinsicHeight;
                    xMark.setBounds(xMarkLeft, xMarkTop, xMarkRight, xMarkBottom);

                    xMark.draw(c);

                }
                super.onChildDraw(c, recyclerView, viewHolder, dX, dY, actionState, isCurrentlyActive);
            }
        };

        ItemTouchHelper itemTouch = new ItemTouchHelper(itemTouchCallBack);
        itemTouch.attachToRecyclerView(recyclerView);
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
