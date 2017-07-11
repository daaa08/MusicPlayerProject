package com.example.da08.musicplayerproject;

import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageButton;
import android.widget.SeekBar;
import android.widget.TextView;

import com.example.da08.musicplayerproject.domain.Data;

import java.util.ArrayList;
import java.util.List;


public class DetailActivity extends AppCompatActivity {

    TextView txtTitleP;
    TextView txtSingerP;
    ViewPager viewPager;
    SeekBar seekBar;
    ImageButton btnShareP, btnUploadP, btnPlayP, btnNextP,btnPreP, btnLikeP;

    List<Data.Music> datas = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_player);

        viewPager = (ViewPager)findViewById(R.id.viewPager);

        txtTitleP = (TextView)findViewById(R.id.txtTitleP);
        txtSingerP = (TextView)findViewById(R.id.txtSingerP);
        seekBar = (SeekBar)findViewById(R.id.seekBar);
        btnShareP = (ImageButton)findViewById(R.id.btnShareP);
        btnUploadP = (ImageButton)findViewById(R.id.btnUploadP);
        btnPlayP = (ImageButton)findViewById(R.id.btnPlayP);
        btnNextP = (ImageButton)findViewById(R.id.btnNextP);
        btnPreP = (ImageButton)findViewById(R.id.btnPreP);
        btnLikeP = (ImageButton)findViewById(R.id.btnLikeP);

    }

}



