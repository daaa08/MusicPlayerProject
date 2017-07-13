package com.example.da08.musicplayerproject;

import android.content.Context;
import android.content.Intent;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageButton;
import android.widget.SeekBar;
import android.widget.TextView;

import com.example.da08.musicplayerproject.domain.CurrentMusic;
import com.example.da08.musicplayerproject.domain.Data;

import java.util.List;


public class DetailActivity extends AppCompatActivity implements View.OnClickListener {

    Uri musicUri = MediaStore.Audio.Media.EXTERNAL_CONTENT_URI;

    Context context;

    TextView txtTitleP;
    TextView txtSingerP;
    ViewPager viewPager;
    SeekBar seekBar;
    ImageButton btnShareP, btnUploadP, btnPlayP, btnNextP, btnPreP, btnLikeP, btnMenu, btnClose;
    Uri MUSIC_PLAY = null;
    List<Data.Music> datas = CurrentMusic.Instance;
    int position = CurrentMusic.currentPosition;

    DetailAdapter adapter = new DetailAdapter(datas);

    private static MediaPlayer player = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_player);

        // intent값 가져오기
        Intent intent = getIntent();
        if (intent != null) {
            String temp = "";
            temp = intent.getStringExtra("MUSIC_PLAY");
            MUSIC_PLAY = Uri.parse(temp);

        }


        viewPager = (ViewPager) findViewById(R.id.viewPager);
        txtTitleP = (TextView) findViewById(R.id.txtTitleP);
        txtSingerP = (TextView) findViewById(R.id.txtSingerP);
        seekBar = (SeekBar) findViewById(R.id.seekBar);
        btnShareP = (ImageButton) findViewById(R.id.btnShareP);
        btnUploadP = (ImageButton) findViewById(R.id.btnUploadP);
        btnPlayP = (ImageButton) findViewById(R.id.btnPlayP);
        btnNextP = (ImageButton) findViewById(R.id.btnNextP);
        btnPreP = (ImageButton) findViewById(R.id.btnPreP);
        btnLikeP = (ImageButton) findViewById(R.id.btnLikeP);
        btnClose = (ImageButton) findViewById(R.id.btnClose);

        txtTitleP.setText(datas.get(position).title);
        txtSingerP.setText(datas.get(position).artist);


        btnPlayP.setOnClickListener(this);
        btnPlayP.setOnClickListener(this);
        btnNextP.setOnClickListener(this);
        btnShareP.setOnClickListener(this);
        btnUploadP.setOnClickListener(this);
        btnLikeP.setOnClickListener(this);
        btnClose.setOnClickListener(this);

        viewPager.setAdapter(adapter);
        viewPager.setCurrentItem(position);
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btnPreP:
                previous(position);
                break;

            case R.id.btnPlayP:
               play(position);
                break;

            case R.id.btnNextP:
                play(position+1);
                break;

            case R.id.btnShareP:
                sharing();
                break;

            case R.id.btnUploadP:

                break;

            case R.id.btnLikeP:

                break;
            case R.id.btnClose:
                Intent intent = new Intent(this, ListActivity.class);
                startActivity(intent);
                break;
        }
    }

    private void sharing(){
        Intent intent = new Intent();
        intent.setAction(Intent.ACTION_SEND);
        intent.setType("text/plain");
        intent.putExtra(Intent.EXTRA_SUBJECT, "음악공유");
        Intent chooser = Intent.createChooser(intent, "공유");
        startActivity(chooser);
    }

    private void play(int position){
        musicUri = datas.get(position).musicUri;
        if (player != null) {
            player.release();
        }
        player = MediaPlayer.create(this, musicUri);
        player.setLooping(false);
        player.start();
    }

    private void previous(int position){
        musicUri = datas.get(position-1).musicUri;
        if (player != null) {
            player.release();
        }
        player = MediaPlayer.create(this, musicUri);
        player.setLooping(false);
        player.start();

    }
}



