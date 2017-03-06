package com.ayako_sayama.apipicassovolley;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    List<Flowers> flowers;
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
        setRecyclerView();
    }

    private void setRecyclerView() {
        //split android phone screen layout to 2!
        flowers = new ArrayList<>();
        requestQueue = Volley.newRequestQueue(this);

        layoutManager = new GridLayoutManager(this, 2);
        recyclerView.setLayoutManager(layoutManager);
        adapter = new MyAdapter(this,flowers);
        recyclerView.setAdapter(adapter);
    }

    public void loadJSON(View view) {
        JsonArrayRequest jsonRequest = new JsonArrayRequest(
                JSON_PATH,
                new Response.Listener<JSONArray>() {
                    public void onResponse(JSONArray response) {
                        respond(response);
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                    }
                }
        );
        requestQueue.add(jsonRequest);
    }

    private void respond(JSONArray response) {
        for (int i = 0; i < response.length(); i++) {
            try{
                String name = response.getJSONObject(i).getString("name");
                if (name.length()>8){
                    //if the name is too long, substitute with dots...
                    name = name.substring(0,8)+"...";
                }
                String photo = response.getJSONObject(i).getString("photo");
                double price = response.getJSONObject(i).getDouble("price");
                flowers.add(new Flowers(name, photo, price));

            } catch (JSONException e){
                Toast.makeText(MainActivity.this, "JSON Exception", Toast.LENGTH_SHORT);
            }
        }
        adapter.notifyDataSetChanged();
    }


    public void addList(View view) {
        flowers.add(3,new Flowers("Rose","agapanthus.jpg",99));
        adapter.notifyItemInserted(3);
    }
}
