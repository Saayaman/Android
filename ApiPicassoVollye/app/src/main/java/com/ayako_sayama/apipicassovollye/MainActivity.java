package com.ayako_sayama.apipicassovollye;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private ArrayList<Flowers> flowers;
    RecyclerView recyclerView;
    RecyclerView.Adapter adapter;
    RecyclerView.LayoutManager layoutManager;

    public static final String PHOTO_PATH = "http://services.hanselandpetal.com/photos/";
    public static final String JSON_PATH = "http://services.hanselandpetal.com/feeds/flowers.json";
    RequestQueue requestQueue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        layoutManager = new GridLayoutManager(this,2);
        //split android phone screen layout to 2!

        flowers = new ArrayList<>();
//        flowers.add(new Flowers("Rose","princess_flower.jpg"));
//        flowers.add(new Flowers("Te","california_snow.jpg"));
//        flowers.add(new Flowers("Re","haight_ashbury.jpg"));
//        flowers.add(new Flowers("Olo","mona_lavender.jpg"));
//        flowers.add(new Flowers("Tg","camellia.jpg"));
//        flowers.add(new Flowers("DDs","bougainvillea.jpg"));
        recyclerView.setLayoutManager(layoutManager);
        adapter = new MyAdapter(flowers, this);
        recyclerView.setAdapter(adapter);
        requestQueue = Volley.newRequestQueue(this,flowers);

    }
}
