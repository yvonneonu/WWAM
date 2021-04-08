package com.example.waam;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class SlideFragment1 extends Fragment {
    private static final String TAG = "SlideFragment1";
    private RecyclerView mRecyclerView;
    // private SmileView mSmileView;
    private ImageView deny1, aloow1;
    private SlideLayoutManager mSlideLayoutManager;
    private ItemTouchHelper mItemTouchHelper;
    private ItemTouchHelperCallback mItemTouchHelperCallback;
    private MyAdapter1 mAdapter;
    private List<SlideBean1> mList = new ArrayList<>();
    private int mLikeCount = 50;
    private int mDislikeCount = 50;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_slide1, container, false);
        initView(rootView);
        initListener();
        return rootView;
    }

    private void initView(View rootView) {
        mRecyclerView = rootView.findViewById(R.id.recycler_view);
        //mSmileView = rootView.findViewById(R.id.smile_view);

        aloow1 = rootView.findViewById(R.id.allow);
        deny1 = rootView.findViewById(R.id.deny);

        deny1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), DiscoverDrawerLayerout.class);
                startActivity(intent);
            }
        });
        aloow1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), Matchfriends.class);
                startActivity(intent);
            }
        });
        mAdapter = new MyAdapter1();
        mRecyclerView.setAdapter(mAdapter);
        addData();
        mItemTouchHelperCallback = new ItemTouchHelperCallback(mRecyclerView.getAdapter(), mList);
        mItemTouchHelper = new ItemTouchHelper(mItemTouchHelperCallback);
        mSlideLayoutManager = new SlideLayoutManager(mRecyclerView, mItemTouchHelper);
        mItemTouchHelper.attachToRecyclerView(mRecyclerView);
        mRecyclerView.setLayoutManager(mSlideLayoutManager);
    }


    private void initListener() {
        mItemTouchHelperCallback.setOnSlideListener(new OnSlideListener() {
            @Override
            public void onSliding(RecyclerView.ViewHolder viewHolder, float ratio, int direction) {
                if (direction == ItemConfig.SLIDING_LEFT) {
                } else if (direction == ItemConfig.SLIDING_RIGHT) {
                }
            }

            @Override
            public void onSlided(RecyclerView.ViewHolder viewHolder, Object o, int direction) {
                if (direction == ItemConfig.SLIDED_LEFT) {
                    mDislikeCount--;
                    // mSmileView.setDisLike(mDislikeCount);
                    //  mSmileView.disLikeAnimation();
                } else if (direction == ItemConfig.SLIDED_RIGHT) {
                    mLikeCount++;
                    //mSmileView.setLike(mLikeCount);
                    // mSmileView.likeAnimation();
                }
                int position = viewHolder.getAdapterPosition();
                Log.e(TAG, "onSlided--position:" + position);
            }

            @Override
            public void onClear() {
                addData();
            }
        });
    }

    private void addData() {
        String[] titles = {"Adrea. 26", "Dorathy, 22", "Kiyomi, 25", "David. 30", "Don, 35", "Kira, 27"};
        String[] says = {
                "San Francisco, CA",
                "San Jose, NA",
                "Foster City, CA",
                "Palo Alto, NA",
                "San Carlos, CA",
                "Redwood City, NA",
                "San Francisco, NA",
        };

        int[] bgs = {R.drawable.discovermatchesbox,
                R.drawable.travel,
                R.drawable.eventcardimg,
                R.drawable.diningout,
                R.drawable.nightclubsdancing,
                R.drawable.coffeeconversation,
                R.drawable.discovereventsbox
        };

        for (int i = 0; i < 6; i++) {
            mList.add(new SlideBean1(bgs[i], titles[i], says[i]));

                    //SlideBean1(bgs[i], titles[i], icons[i]));
        }
    }


    private class MyAdapter1 extends RecyclerView.Adapter<MyAdapter1.Viewholder>{

        @NonNull
        @Override
        public Viewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_slide2, parent, false);
            return new Viewholder(view);
        }

        @Override
        public void onBindViewHolder(@NonNull Viewholder holder, int position) {
            SlideBean1 bean = mList.get(position);
            // holder.imgBg.setImageResource(bean.getItemBg());
            holder.imgBg.setImageResource(bean.getImage());
            holder.tvTitle.setText(bean.getNameNage());
            holder.img_user.setText(bean.getLocation());

        }

        @Override
        public int getItemCount() {
            return mList.size();
        }

        public class Viewholder extends RecyclerView.ViewHolder {

            ImageView imgBg;
            TextView tvTitle;
            TextView img_user;



            public Viewholder(@NonNull View itemView) {
                super(itemView);
                imgBg = itemView.findViewById(R.id.img_bg);
                tvTitle = itemView.findViewById(R.id.tv_title);
                img_user = itemView.findViewById(R.id.img_user);
            }
        }
    }
}