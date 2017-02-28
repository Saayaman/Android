package com.ayako_sayama.itemtouchhelperundo;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ayako_sayama on 2017/02/28.
 */
public class MyAdapter extends RecyclerView.Adapter {

    private Context context;
    private List<ItemModel> itemList = new ArrayList<>();
    TextView textView;

    public MyAdapter(Context context, List<ItemModel> mylist) {
        this.context = context;
        this.itemList = mylist;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new MyViewHolder(LayoutInflater.from(context).inflate(R.layout.list_row,parent,false));
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        textView = (TextView)holder.itemView.findViewById(R.id.title_text_view);
        textView.setText(itemList.get(position).getText());
    }

    @Override
    public int getItemCount() {
        return itemList.size();
    }

    public void remove(int swipedPosition) {
        itemList.remove(swipedPosition);
        notifyItemRemoved(swipedPosition);
    }

    private class MyViewHolder extends RecyclerView.ViewHolder {
        public MyViewHolder(View inflate) {
            super(inflate);
        }
    }
}
