package com.jack.meituangoodsdetails.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.jack.meituangoodsdetails.R;

import androidx.recyclerview.widget.RecyclerView;


public class MainAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private LayoutInflater layoutInflater;

    public MainAdapter(Context context){
        layoutInflater=LayoutInflater.from(context);
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ViewHolderItem(layoutInflater.inflate(R.layout.layout_main_item,parent,false));
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        ViewHolderItem viewHolderItem=(ViewHolderItem)holder;
        viewHolderItem.mainItem.setText("main"+position);
    }

    @Override
    public int getItemCount() {
        return 20;
    }

    class ViewHolderItem extends RecyclerView.ViewHolder{
        public TextView mainItem;
        public ViewHolderItem(View itemView) {
            super(itemView);
            mainItem=itemView.findViewById(R.id.main_item);
        }
    }


}
