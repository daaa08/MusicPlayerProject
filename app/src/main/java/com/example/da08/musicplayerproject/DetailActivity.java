package com.example.da08.musicplayerproject;

import android.content.Intent;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.SeekBar;
import android.widget.TextView;

import com.example.da08.musicplayerproject.domain.CurrentMusic;
import com.example.da08.musicplayerproject.domain.Data;

import java.util.List;


public class DetailActivity extends AppCompatActivity implements View.OnClickListener , NextAndPre {

    Uri musicUri = MediaStore.Audio.Media.EXTERNAL_CONTENT_URI;

    TextView txtTitleP;
    TextView txtSingerP;
    ViewPager viewPager;
    SeekBar seekBar;
    ImageButton btnShareP, btnUploadP, btnPlayP, btnNextP, btnPreP, btnLikeP, btnMenu, btnClose, btnPauseP, btnReStartP;
    Uri MUSIC_PLAY = null;
    List<Data.Music> datas = CurrentMusic.Instance;
    int position = CurrentMusic.currentPosition;

    DetailAdapter adapter = new DetailAdapter(datas);

    private MediaPlayer player = null;

    Boolean isPlaying = false;

    class SeekBarThread extends Thread{
        @Override
        public void run() {
            // 씨크바 막대기 조금씩 움직이기 (노래 끝날 때까지 반복)
            while(isPlaying) {
                seekBar.setProgress(player.getCurrentPosition());
            }
        }
    }

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
        btnPauseP = (ImageButton)findViewById(R.id.btnPauseP);
        btnReStartP = (ImageButton)findViewById(R.id.btnReStartP);

        txtTitleP.setText(datas.get(position).title);
        txtSingerP.setText(datas.get(position).artist);


        btnPlayP.setOnClickListener(this);
        btnPreP.setOnClickListener(this);
        btnNextP.setOnClickListener(this);
        btnShareP.setOnClickListener(this);
        btnUploadP.setOnClickListener(this);
        btnLikeP.setOnClickListener(this);
        btnClose.setOnClickListener(this);
        btnPauseP.setOnClickListener(this);
        btnReStartP.setOnClickListener(this);

        viewPager.setAdapter(adapter);
        viewPager.setCurrentItem(position);

        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                if (seekBar.getMax() == progress) {
                    isPlaying = false;
                    player.stop();
                }
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
                isPlaying = false;
                player.pause();
            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                isPlaying = true;
                int userControl = seekBar.getProgress(); // 사용자가 움직여놓은 위치
                player.seekTo(userControl);
                player.start();
                new SeekBarThread().start();
            }
        });
    }


    @Override
    public void onClick(View v) {

        Log.i("Detail","View id============================"+v.getId());
        Log.i("Detail","prep id============================"+R.id.btnPreP);
        switch (v.getId()) {
            case R.id.btnPreP:
                Pre();
                break;

            case R.id.btnPlayP:
               play();
                btnPlayP.setVisibility(View.INVISIBLE);
                btnPauseP.setVisibility(View.VISIBLE);
                break;

            case R.id.btnNextP:
                Next();
                break;

            case R.id.btnShareP:
                sharing();
                break;

            case R.id.btnUploadP:

                break;

            case R.id.btnLikeP:

                break;
            case R.id.btnClose:
                this.finish();
                break;

            case R.id.btnPauseP:
                player.pause();
                isPlaying = false;
                btnPauseP.setVisibility(View.INVISIBLE);
                btnReStartP.setVisibility(View.VISIBLE);
                break;
            case R.id.btnReStartP:

                player.start(); // 시작
                isPlaying = true; // 재생하도록 flag 변경
                new SeekBarThread().start(); // 쓰레드 시작

                btnPauseP.setVisibility(View.VISIBLE);
                btnReStartP.setVisibility(View.INVISIBLE);
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

    private void play(){

        musicUri = datas.get(position).musicUri;
        if (player != null) {
            player.release();
        }
        player = MediaPlayer.create(this, musicUri);
        player.setLooping(false);
        player.start();

        int a = player.getDuration(); // 노래의 재생시간(miliSecond)
        seekBar.setMax(a);// 씨크바의 최대 범위를 노래의 재생시간으로 설정
        new SeekBarThread().start(); // 씨크바 그려줄 쓰레드 시작
        isPlaying = true; // 씨크바 쓰레드 반복 하도록
    }

    @Override
    public void Next() {
        player.release();
        position++;
        musicUri = datas.get(position).musicUri;
        if (player != null) {
            player.release();
        }
        player = MediaPlayer.create(this, musicUri);
        player.setLooping(false);
        player.start();


    }

    @Override
    public void Pre() {
        Log.i("Detail","position==========================="+position);
        player.release();

        position--;
        musicUri = datas.get(position).musicUri;

        if (player != null) {
            player.release();
        }
        player = MediaPlayer.create(this, musicUri);
        player.setLooping(false);
        player.start();


    }
}



