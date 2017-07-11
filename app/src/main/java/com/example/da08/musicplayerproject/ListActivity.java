package com.example.da08.musicplayerproject;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.da08.musicplayerproject.domain.CurrentMusic;
import com.example.da08.musicplayerproject.domain.Data;

import java.util.List;

public class ListActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    TextView txtTitleL, txtSingerL;
    ImageView imgAlbumL;
    ImageButton btnPlayL;
    ListAdapter adapter;

    int position;

    List<Data.Music> datas = CurrentMusic.Instance;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_play_list);

        recyclerView = (RecyclerView)findViewById(R.id.recyclerView);
        txtTitleL = (TextView)findViewById(R.id.txtSingerL);
        txtSingerL = (TextView)findViewById(R.id.txtSingerL);
        imgAlbumL = (ImageView)findViewById(R.id.imgAlbumL);
        btnPlayL = (ImageButton)findViewById(R.id.btnPlayL);

        adapter = new ListAdapter(this);
        CurrentMusic.Instance = Data.read(this);
        datas = CurrentMusic.Instance;
        adapter.setData(datas);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
    }
}
