package com.example.waam;


import android.content.Context;
import android.text.Spannable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager.widget.ViewPager;

public class SliderAdapter extends PagerAdapter {
    private LayoutInflater layoutInflater;
    private Context context;

    private int[] getBackground = new int[]{
            R.drawable.friendsbg,
            R.drawable.match_bg,
            R.drawable.getaways_bg,
            R.drawable.events_bg,
            R.drawable.agents_bg,

    };

    public int[] images = {
            R.drawable.icon_friends,
            R.drawable.icon_match,
            R.drawable.icon_getaways,
            R.drawable.icon_events,
            R.drawable.icon_agent,
    };

    public String[] slide_writeup = {
            "Find New Friends",
            "Find New Matches",
            "Find New Gateways",
            "Find New Events",
            "Find Your Dating Agents"

    };
    public String[] describ = {
            "with every swipe",
            "with every swipe",
            "with every swipe",
            "with every swipe",
            "get help finding your match"
    };
    public SliderAdapter(Context context) {
        this.context = context;
    }

    @Override
    public int getCount() {
        return slide_writeup.length;
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return view.equals(object);
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {
        layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = layoutInflater.inflate(R.layout.slider,container,false);
        ImageView imageView;
        ImageView imageView1;
        TextView textView;
        ImageView back;
        TextView desc;

        back = view.findViewById(R.id.background);
        imageView = view.findViewById(R.id.image);
        imageView1 = view.findViewById(R.id.image1);
        textView = view.findViewById(R.id.title);
        desc = view.findViewById(R.id.title1);

        //ImageView imageView5 = new ImageView(context);
        //imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
        back.setImageResource(getBackground[position]);
//        container.addView(imageView, 1);
        container.addView(view);

        imageView1.setImageResource(images[position]);
        textView.setText(slide_writeup[position]);
        desc.setText(describ[position]);
//        container.addView(view, 0);

        return view;
    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        container.removeView((View) object);

    }

}
