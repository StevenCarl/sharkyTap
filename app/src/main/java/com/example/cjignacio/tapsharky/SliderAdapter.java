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
            R.drawable.ins3,
            R.drawable.ins4

    };

    public String [] slide_headings ={
            "How To Swim",
            "Fish Points",
            "Avoid this",
            "Eat Timer"
    };

    public String [] slide_descs ={
            "To start the game TAP anywhere in the screen, HOLD to SWIM UP and RELEASE to SWIM DOWN",
            "To acquire points eat the fishes, DIFFERENT FISH comes with DIFFERENT POINTS",
            "To avoid game over DO NOT EAT OR BUMP to poison bottles and puffer fishes",
            "To gain more points just EAT but OBSERVE the 1 MINUTE TIMER if it runs out its over. "
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
