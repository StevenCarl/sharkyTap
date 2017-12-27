package com.example.cjignacio.tapsharky;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.zip.Inflater;

/**
 * Created by CJIgnacio on 12/22/2017.
 */

public class CustomAdapter extends PagerAdapter {

    private int imgs [] = {R.drawable.fishy1, R.drawable.fishy2, R.drawable.fishy3};
    private LayoutInflater inflater;
    private Context ctx;

    public  CustomAdapter(Context ctx){

        this.ctx = ctx;

    }

    @Override
    public int getCount() {
        return imgs.length;
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return (view == (LinearLayout)object);
    }

    @Override
    public Object instantiateItem (ViewGroup container, int position){
        inflater = (LayoutInflater)ctx.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = inflater.inflate(R.layout.swipe,container,false);
        ImageView img = (ImageView)view.findViewById(R.id.imageView);
        TextView tv = (TextView) view.findViewById(R.id.textView);
        img.setImageResource(imgs[position]);
        tv.setText("Image :"+position);
        container.addView(view);
        return view;
    }

    @Override
    public void destroyItem (ViewGroup container, int position, Object object){
        container.invalidate();
    }

}
