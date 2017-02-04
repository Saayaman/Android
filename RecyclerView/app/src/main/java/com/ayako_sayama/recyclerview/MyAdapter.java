package com.ayako_sayama.recyclerview;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * Created by ayako_sayama on 2017/01/30.
 */
public class MyAdapter extends RecyclerView.Adapter<MyAdapter.ViewHolder>{
    private ItemData[] itemsData;

    public MyAdapter(ItemData[] itemData){
        this.itemsData = itemData;
    }

    public MyAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType){
        View itemLayoutView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_layout, parent, false);
        //parentがないと、親要素のwidthやheightでつくってくれなーい。falseはattachrootしない。という意味。

        ViewHolder viewHolder = new ViewHolder(itemLayoutView);
        return viewHolder;
        }

    @Override
    public void onBindViewHolder(ViewHolder viewHolder, int position) {

        // - get data from your itemsData at this position
        // - replace the contents of the view with that itemsData

        viewHolder.txtViewTitle.setText(itemsData[position].getTitle());
        viewHolder.imgViewIcon.setImageResource(itemsData[position].getImageUrl());


    }

    // inner class to hold a reference to each item of RecyclerView
    public static class ViewHolder extends RecyclerView.ViewHolder {

        public TextView txtViewTitle;
        public ImageView imgViewIcon;

        public ViewHolder(View itemLayoutView) {
            super(itemLayoutView);
            txtViewTitle = (TextView) itemLayoutView.findViewById(R.id.textView);
            imgViewIcon = (ImageView) itemLayoutView.findViewById(R.id.imageView);
        }
    }

    // Return the size of your itemsData (invoked by the layout manager)
    @Override
    public int getItemCount() {
        return itemsData.length;
    }
}


