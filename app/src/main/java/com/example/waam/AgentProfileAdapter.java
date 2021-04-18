package com.example.waam;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.viewpager.widget.PagerAdapter;

public class AgentProfileAdapter extends PagerAdapter {
        private LayoutInflater layoutInflater;
        private Context context;




        private int[] getBackground = {
                R.drawable.agent_4_img,
                R.drawable.agent_4_img,
                R.drawable.agent_4_img,
                R.drawable.agent_4_img,
                R.drawable.agent_4_img,
        };



        public AgentProfileAdapter(Context context) {
            this.context = context;
        }

        @Override
        public int getCount() {
            return getBackground.length;
        }

        @Override
        public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
            return view.equals(object);
        }

        @NonNull
        @Override
        public Object instantiateItem(@NonNull ViewGroup container, int position) {
            layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            View view = layoutInflater.inflate(R.layout.prolifeslider,container,false);

            ImageView imageView1;

            ImageView back;

           // back = view.findViewById(R.id.background);
            imageView1 = view.findViewById(R.id.imageView34);


           imageView1.setImageResource(getBackground[position]);

            //ImageView imageView5 = new ImageView(context);
            //imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
           // back.setImageResource(getBackground[position]);
//        container.addView(imageView, 1);
            container.addView(view);




//        container.addView(view, 0);

            return view;



        }


        @Override
        public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
            container.removeView((View) object);

        }

    }

