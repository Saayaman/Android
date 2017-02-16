package com.ayako_sayama.recyclerview;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        RecyclerView recyclerView = (RecyclerView)findViewById(R.id.recycleView);
        ItemData itemData[] = {
                new ItemData("Help", R.drawable.question),
                new ItemData("Cloud", R.drawable.soundcloud),
                new ItemData("Favorite", R.drawable.star),
                new ItemData("Delete", R.drawable.trash),
                new ItemData("Private", R.drawable.lock),
                new ItemData("Logout", R.drawable.poweroff),
                new ItemData("Admin", R.drawable.user),
                new ItemData("Help", R.drawable.question),
                new ItemData("Cloud", R.drawable.soundcloud),
                new ItemData("Favorite", R.drawable.star),
                new ItemData("Delete", R.drawable.trash),
                new ItemData("Private", R.drawable.lock),
                new ItemData("Logout", R.drawable.poweroff),
                new ItemData("Admin", R.drawable.user)

        };

        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        MyAdapter myAdapter = new MyAdapter(itemData);
        recyclerView.setAdapter(myAdapter);
        recyclerView.setItemAnimator(new DefaultItemAnimator());

    }

}
