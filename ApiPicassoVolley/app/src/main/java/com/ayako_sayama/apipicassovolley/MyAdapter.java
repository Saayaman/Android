package com.ayako_sayama.apipicassovolley;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.List;

import static com.ayako_sayama.apipicassovolley.MainActivity.PHOTO_PATH;

/**
 * Created by ayako_sayama on 2017/02/21.
 */
public class MyAdapter extends RecyclerView.Adapter<MyAdapter.Holder> {

    private List<Flowers> flowers;
    private Context context;


    public MyAdapter(Context context, List<Flowers> flowers) {
        this.context = context;
        this.flowers = flowers;
    }

    public class Holder extends RecyclerView.ViewHolder {
        TextView title, price;
        ImageView imageView, menuBtn;

        public Holder(View itemView) {
            super(itemView);
            title =(TextView)itemView.findViewById(R.id.textViewTitle);
            price =(TextView)itemView.findViewById(R.id.textViewPice);
            imageView = (ImageView)itemView.findViewById(R.id.customThumb);
            menuBtn = (ImageView)itemView.findViewById(R.id.customMenuBtn);
        }
    }

    @Override
    public Holder onCreateViewHolder(ViewGroup parent, int viewType) {
       View view = LayoutInflater.from(context).inflate(R.layout.card_view,parent,false);
            return new Holder(view);
    }

    @Override
    public void onBindViewHolder(final Holder holder, final int position) {

        holder.title.setText(flowers.get(position).getName());
        holder.price.setText(String.valueOf(flowers.get(position).getPrice()));
        Picasso.with(context).load(PHOTO_PATH+flowers.get(position).getPhoto()).into(holder.imageView);
        holder.imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                flowers.remove(holder.getAdapterPosition());
                notifyItemRemoved(holder.getAdapterPosition());
            }
        });

//        holder.menuBtn.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                PopupMenu popupMenu = new PopupMenu(context, v);
//                MenuInflater menuInflater = popupMenu.getMenuInflater();
//                menuInflater.inflate(R.menu.card_menu, popupMenu.getMenu());
//                popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
//                    @Override
//                    public boolean onMenuItemClick(MenuItem item) {
//                        switch (item.getItemId()){
//                            case R.id.menuDelete:
//
//                                flowers.remove(holder.getAdapterPosition());
//                                notifyItemRemoved(holder.getAdapterPosition());
////                                notifyItemRangeChanged(position, flowers.size());
//                                Toast.makeText(context, holder.title.getText()+"deleted", Toast.LENGTH_SHORT).show();
//                                return true;
//                            case R.id.menuBuy:
//                                Toast.makeText(context, holder.title.getText()+"bought", Toast.LENGTH_SHORT).show();
//                                return true;
//                        }
//                        return false;
//                    }
//                });
//            }
//
//        });
    }

    @Override
    public int getItemCount() {
        return this.flowers.size();
    }
}
