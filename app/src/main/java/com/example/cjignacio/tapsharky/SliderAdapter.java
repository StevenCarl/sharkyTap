package com.example.cjignacio.tapsharky;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.VideoView;

/**
 * Created by CJIgnacio on 12/23/2017.
 */

public class SliderAdapter extends PagerAdapter {

    Context context;
    LayoutInflater layoutInflater;

    public SliderAdapter (Context context){
        this.context = context;
    }

    // Arrays
    public int [] slide_images ={
            R.drawable.ins1,
            R.drawable.ins2,
            R.drawable.ins3

    };

    public String [] slide_headings ={
            "How To Swim",
            "Fish Points",
            "Avoid this"
    };

    public String [] slide_descs ={
            "To StartActivity the game TAP anywhere in the screen, hold to SWIM UP and release to SWIM DOWN",
            "To acquire points eat the fishes, different fish comes with different points",
            "To avoid game over do not eat or bump to poison bottles and puffer fishes"
    };

    @Override
    public int getCount() {
        return slide_headings.length;
    }

    @Override
    public boolean isViewFromObject(View view, Object object){
        return view == (RelativeLayout) object;
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position){

        layoutInflater = (LayoutInflater) context.getSystemService(context.LAYOUT_INFLATER_SERVICE);
        View view = layoutInflater.inflate(R.layout.slide_layout,container,false);

        ImageView slideImageView = (ImageView) view.findViewById(R.id.slide_image);
        TextView slideHeading = (TextView) view.findViewById(R.id.slide_heading);
        TextView slideDescription = (TextView) view.findViewById(R.id.slide_desc);

        slideImageView.setImageResource(slide_images[position]);
        slideHeading.setText(slide_headings[position]);
        slideDescription.setText(slide_descs[position]);

        container.addView(view);

        return view;
    }

    @Override
    public void destroyItem (ViewGroup container, int position, Object object){

        container.removeView((RelativeLayout)object );
    }
}
