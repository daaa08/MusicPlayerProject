package com.example.da08.musicplayerproject;

import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;

import com.example.da08.musicplayerproject.domain.CurrentMusic;
import com.example.da08.musicplayerproject.domain.Data;

import java.util.List;

/**
 * Created by Da08 on 2017. 7. 11..
 */

public class DetailAdapter extends PagerAdapter{

    List<Data.Music> datas = CurrentMusic.Instance;


    public DetailAdapter(List<Data.Music>datas){
        this.datas = datas;
    }

    @Override
    public int getCount() {
        return datas.size();
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        return null;
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }


}
