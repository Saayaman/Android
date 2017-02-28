package com.ayako_sayama.itemtouchhelper;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ayako_sayama on 2017/02/27.
 */
public class MyAdapter extends RecyclerView.Adapter {

    private Context context;
    private List<ItemModel> itemList = new ArrayList<>();
    TextView itemTextView;


    public MyAdapter(Context context, List<ItemModel> itemList) {
        this.context = context;
        this.itemList = itemList;
    }


    private class MyViewHolder extends RecyclerView.ViewHolder {
        public MyViewHolder(View itemView) {
            super(itemView);
        }
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new MyViewHolder((ViewGroup) LayoutInflater.from(context).inflate(R.layout.list_row, parent, false));
}

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {

        itemTextView = (TextView)holder.itemView.findViewById(R.id.title_text_view);
        itemTextView.setText(itemList.get(position).getText());

    }

    @Override
    public int getItemCount() {
        return itemList.size();
    }

    public void remove(int position) {
        itemList.remove(position);
        notifyItemRemoved(position);
    }

}
