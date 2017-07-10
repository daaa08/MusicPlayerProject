package com.example.da08.musicplayerproject;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * Created by Da08 on 2017. 7. 10..
 */

public class CustomAdapter extends RecyclerView.ViewHolder{


    public CustomAdapter(View itemView) {
        super(itemView);
    }

    class Holder extends RecyclerView.ViewHolder {
        public ImageView imgAlbum;
        public TextView txtTitle, txtSinger;
        public ImageButton btnMenu;

        public Holder(View itemView) {
            super(itemView);
            imgAlbum = (ImageView)itemView.findViewById(R.id.imgAlbum);
            txtTitle = (TextView)itemView.findViewById(R.id.txtTitle);
            txtSinger = (TextView)itemView.findViewById(R.id.txtSinger);
            btnMenu = (ImageButton) itemView.findViewById(R.id.btnMenu);
        }

    }
}
