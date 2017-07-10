package com.example.da08.musicplayerproject;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.da08.musicplayerproject.domain.Data;

import java.util.List;

public class PlayListActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    TextView txtTitleL, txtSingerL;
    ImageView imgAlbumL;
    ImageButton btnPlayL;

    CustomAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_play_list);

        recyclerView = (RecyclerView)findViewById(R.id.recyclerView);
        txtTitleL = (TextView)findViewById(R.id.txtSingerL);
        txtSingerL = (TextView)findViewById(R.id.txtSingerL);
        imgAlbumL = (ImageView)findViewById(R.id.imgAlbumL);
        btnPlayL = (ImageButton)findViewById(R.id.btnPlayL);

        adapter = new CustomAdapter(this);

        List<Data.Music>data = Data.read(this);  // 데이터 가져옴
        adapter.setData(data);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));


    }
}
