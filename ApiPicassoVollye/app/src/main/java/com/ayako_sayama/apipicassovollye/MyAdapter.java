package com.ayako_sayama.apipicassovollye;

import android.content.Context;
import android.support.v7.widget.PopupMenu;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import static android.content.ContentValues.TAG;
import static com.ayako_sayama.apipicassovollye.MainActivity.PHOTO_PATH;

/**
 * Created by ayako_sayama on 2017/02/21.
 */
public class MyAdapter extends RecyclerView.Adapter<MyAdapter.Holder> {

    private ArrayList<Flowers> flowers;
    private Context context;


    public MyAdapter(ArrayList<Flowers> flowers, Context context) {
        this.flowers = flowers;
        this.context = context;
    }

    @Override
    public Holder onCreateViewHolder(ViewGroup parent, int viewType) {
       View view = LayoutInflater.from(context).inflate(R.layout.card_view,parent,false);
       Holder holder =  new Holder(view);
            return holder;
    }

    @Override
    public void onBindViewHolder(final Holder holder, final int position) {
        holder.textView.setText(flowers.get(position).getName());
        Picasso.with(context).load(PHOTO_PATH+flowers.get(position).getPhoto()).into(holder.imageView);
        Log.i(TAG, "onBindViewHolder: "+flowers.get(position).getPhoto());
        holder.menuBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PopupMenu popupMenu = new PopupMenu(context, v);
                MenuInflater menuInflater = popupMenu.getMenuInflater();
                menuInflater.inflate(R.menu.card_menu, popupMenu.getMenu());
                popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem item) {
                        switch (item.getItemId()){
                            case R.id.menuDelete:
//                                flowers.remove(position);
//                                notifyItemRemoved(position);
//                                notifyItemRangeChanged(position, flowers.size());
                                Toast.makeText(context, holder.textView.getText()+"deleted", Toast.LENGTH_SHORT).show();
                                return true;
                            case R.id.menuBuy:
                                Toast.makeText(context, holder.textView.getText()+"bought", Toast.LENGTH_SHORT).show();
                                return true;
                        }
                        return false;
                    }
                });
            }

        });
    }

    @Override
    public int getItemCount() {
        return this.flowers.size();
    }


    public class Holder extends RecyclerView.ViewHolder {
        TextView textView;
        ImageView imageView, menuBtn;

        public Holder(View itemView) {
            super(itemView);
            textView=(TextView)itemView.findViewById(R.id.customTextview);
            imageView = (ImageView)itemView.findViewById(R.id.customThumb);
            menuBtn = (ImageView)itemView.findViewById(R.id.customMenuBtn);
        }
    }
}
