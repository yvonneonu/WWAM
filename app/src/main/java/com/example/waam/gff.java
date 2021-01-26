package com.example.waam;

public class gff {
  /*  Context context;
    LayoutInflater layoutInflater;
    private int[] getBackground = new int[]{
            R.drawable.friendsbg,
            R.drawable.match_bg,
            R.drawable.getaways_bg,
            R.drawable.events_bg,
            R.drawable.agents_bg,

    };
    public int getBackground(Context context1){
        context = context1;
        return getBackground.length;
    }

    public int[] images = {
            R.drawable.icon_friends,
            R.drawable.icon_match,
            R.drawable.icon_getaways,
            R.drawable.icon_events,
            R.drawable.icon_friends,
            R.drawable.swipe_dot_1_active,
            R.drawable.swipe_dot_5_active,
            R.drawable.swipe_dot_3_active,
            R.drawable.swipe_dot_4_active,
    };

    public String[] slide_writeup = {
            "Find New Friends with every swipe",
            "Find New Matches with every swipe",
            "Find New Gateways with every swipe",
            "Find New Events with every swipe",
            "Find Your Dating Agents get help finding your match"
    };
    public SliderAdapter(Context context){
        this.context = context;
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {
        layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = layoutInflater.inflate(R.layout.slider,container,false);
        ImageView imageView = new ImageView(context);
        imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
        imageView.setImageResource(getBackground[position]);
        container.addView(imageView, 0);
        //container.addView(view);

        ImageView imageView1 = view.findViewById(R.id.imageViewSlider);
        ImageView imageView2 = view.findViewById(R.id.imageicon);
        TextView textView = view.findViewById(R.id.imagetext);
        TextView textViewSlidHead = view.findViewById(R.id.signup);
        //imageView1.setImageResource(images[position]);
        imageView2.setImageResource(images[position]);
        textView.setText(slide_writeup[position]);

        // return imageView;
        //ViewPager pager= view.findViewById(R.id.viewpage);

        // ConstraintLayout constraintLayout =
        // constraintLayout = view.findViewById(R.id.constrained);
//
//        constraintLayout.setBackground(ContextCompat.getDrawable(context, background[position]));
        //
        /*Glide.with(context)
                .asBitmap()
                .load(images[position])
                .into(imageView1);

        container.addView(view, 0);
        return view;

    }

    @Override
    public int getCount() {
        return slide_writeup.length;
    }

    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return view == object;
    }
    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        // container.removeView((ImageView) object);
        //container.removeView((ConstraintLayout)object);
        container.removeView((View)object);
    }*/
}
