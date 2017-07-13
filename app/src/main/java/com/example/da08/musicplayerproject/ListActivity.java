package com.example.da08.musicplayerproject;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.da08.musicplayerproject.domain.CurrentMusic;
import com.example.da08.musicplayerproject.domain.Data;

import java.util.List;

import jp.wasabeef.glide.transformations.CropCircleTransformation;

public class ListActivity extends AppCompatActivity implements ListAdapter.Callback{

    RecyclerView recyclerView;
    TextView txtTitleL, txtSingerL;
    ImageView imgAlbumL;
    ImageButton btnPlayL, btnMenuL, btnSearchL;
    ListAdapter adapter;

    List<Data.Music> datas = CurrentMusic.Instance;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_play_list);

        recyclerView = (RecyclerView)findViewById(R.id.recyclerView);
        txtTitleL = (TextView)findViewById(R.id.txtTitleL);
        txtSingerL = (TextView)findViewById(R.id.txtSingerL);
        imgAlbumL = (ImageView)findViewById(R.id.imgAlbumL);
        btnPlayL = (ImageButton)findViewById(R.id.btnPlayL);

        adapter = new ListAdapter(this);
        CurrentMusic.Instance = Data.read(this);
        datas = CurrentMusic.Instance;
        adapter.setData(datas);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        recyclerView.addOnItemTouchListener(new RecyclerView.OnItemTouchListener() {
            float downX, downY;

            @Override
            public boolean onInterceptTouchEvent(RecyclerView rv, MotionEvent e) {
                downX = e.getX();
                downY = e.getY();
                View v = recyclerView.findChildViewUnder(e.getX(),e.getY());
                if(v.getHeight() > Math.abs(downY - e.getY())){
                    int position = recyclerView.getChildAdapterPosition(v);
                    itemSelected(position);
                }
                return false;
            }

            @Override
            public void onTouchEvent(RecyclerView rv, MotionEvent e) {

            }

            @Override
            public void onRequestDisallowInterceptTouchEvent(boolean disallowIntercept) {

            }
        });

//        recyclerView.setOnTouchListener(new View.OnTouchListener() {
//            float downX, downY;
//            @Override
//            public boolean onTouch(View v, MotionEvent event) {
//                Log.e(getClass().getSimpleName(),"onTouch");
//                switch (event.getAction()){
//                    case MotionEvent.ACTION_DOWN:
//                    case MotionEvent.ACTION_POINTER_DOWN:
//                        downX = event.getX();
//                        downY = event.getY();
//                        return true;
//                    case MotionEvent.ACTION_UP:
//                    case MotionEvent.ACTION_POINTER_UP:
//                    case MotionEvent.ACTION_CANCEL:
//                        Log.e(getClass().getSimpleName(),downY+": "+event.getY());
//                        View view = recyclerView.findChildViewUnder(event.getX(),event.getY());
//                        if (view.getHeight() > Math.abs(downY - event.getY())) {
//                            int position = recyclerView.getChildAdapterPosition(view);
//                            itemSelected(position);
//                        }
//                        return false;
//                }
//                return false;
//            }
//        });

    }


    @Override
    public void itemSelected(final int position) {
        txtTitleL.setText(datas.get(position).title);
        txtSingerL.setText(datas.get(position).artist);
        Glide.with(this).load(datas.get(position).albumArt).placeholder(R.mipmap.ic_launcher_round)
                .bitmapTransform(new CropCircleTransformation(this)).into(imgAlbumL);


    }

}
