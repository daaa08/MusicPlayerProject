package com.example.da08.musicplayerproject;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.da08.musicplayerproject.domain.Data;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Da08 on 2017. 7. 10..
 */

public class CustomAdapter extends RecyclerView.Adapter<CustomAdapter.Holder>{

    Context context;

    List<Data.Music> data = new ArrayList<>();

    public CustomAdapter(Context context) {
        this.context = context;
    }

    public void setData(List<Data.Music> data){  // 음악 목록 데이터 세팅
        this.data = data;

    }


    @Override
    public int getItemCount() {
        return data.size();
    }

    @Override
    public Holder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_list,parent,false);
        return new Holder(view);
    }

    @Override
    public void onBindViewHolder(Holder holder, int position) {
        holder.position = position;
        holder.txtTitle.setText(data.get(position).title);
        holder.txtSinger.setText(data.get(position).artist);
        Glide.with(context).load(data.get(position).albumArt).into(holder.imgAlbum);

    }

    class Holder extends RecyclerView.ViewHolder {

        public int position;
        public TextView txtTitle, txtSinger;
        public ImageButton btnMenu;
        public ImageView imgAlbum;

        public Holder(View itemView) {
            super(itemView);
            txtTitle = (TextView)itemView.findViewById(R.id.txtTitle);
            txtSinger = (TextView)itemView.findViewById(R.id.txtSinger);
            imgAlbum = (ImageView)itemView.findViewById(R.id.imgAlbum);
            btnMenu = (ImageButton)itemView.findViewById(R.id.btnMenu);


        }

    }
}
