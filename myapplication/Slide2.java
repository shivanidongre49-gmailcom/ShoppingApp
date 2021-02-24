package com.example.myapplication;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import androidx.annotation.NonNull;
import androidx.viewpager.widget.PagerAdapter;

public class Slide2 extends PagerAdapter {

    Context con;
    LayoutInflater layoutInflater;

     public Slide2(Context con)
     {
         this.con=con;
     }
     public  int[] images={
        R.drawable.game,
        R.drawable.kitchen,
        R.drawable.plus,
             R.drawable.furnitures
     };

    @Override
    public int getCount() {
        return images.length;
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return view==(RelativeLayout) object;
    }
    public  Object instantiateItem(ViewGroup container, int position)
    {
        layoutInflater=(LayoutInflater) con.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View v=layoutInflater.inflate(R.layout.activity_slide2, container, false);
        ImageView image=(ImageView) v.findViewById(R.id.game);
        image.setImageResource(images[position]);
        container.addView(v);
        return v;
    }
    public void destroyItem(ViewGroup container, int position,Object object){
        container.removeView((RelativeLayout)object);
    }
}