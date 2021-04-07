package com.example.waam;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

/**

 * create an instance of this fragment.
 */
public class SlideFragment1 extends Fragment {
    private static final String TAG = "SlideFragment1";
    private RecyclerView mRecyclerView;
    // private SmileView mSmileView;
    private ImageView deny1, aloow1;
    private SlideLayoutManager mSlideLayoutManager;
    private ItemTouchHelper mItemTouchHelper;
    private ItemTouchHelperCallback mItemTouchHelperCallback;
    private SlideFragment.MyAdapter mAdapter;
    private List<SlideBean> mList = new ArrayList<>();
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

        mAdapter = new SlideFragment.MyAdapter();
        //mAdapter = new MyAdapter();
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

    /**
     * 向集合中添加数据
     */
    private void addData() {
        String[] icons = {"$389", "$337", "$675", "$389", "$389", "$389", "$389"};

        String[] titles = {"Tours with Chamber Access", "Belief Tours with Chamber Access", "Tours with Chamber Access", "DreamingTours with Chamber Access", "Tours with Chamber Access", "Confidence Tours with Chamber Access"};
        String[] says = {
                "Vega to GC West Rim Helicopter",
                "Keep on going never give up.",
                "Vega to GC West Rim Helicopter Tours with Chamber Access",
                "I can because i think i can.",
                "Vega to GC West Rim Helicopter Tours with Chamber Access",
                "Keep on going never give up.",
                "Whatever is worth doing is worth doing well.",
        };
        String[] neededStrike = {"$145", "$213", "$245", "$234", "$244", "$131", "$311"};
        int[] bgs = {R.drawable.diningout,
                R.drawable.eventcardimg,
                R.drawable.nightclubsdancing,
                R.drawable.travel,
                R.drawable.coffeeconversation,
                R.drawable.diningout,
                R.drawable.discovereventsbox
        };
        String[] rateon = {"3.4", "4.6", "8.9", "7.5", "9.3", "7.3", "5.8"};
        String[] seconrate = {"(119 Ratings)", "(309 Ratings)", "(329 Ratings)", "(209 Ratings)", "(165 Ratings)", "(109 Ratings)", "(197 Ratings)"};

        for (int i = 0; i < 6; i++) {
           // mList.add(new SlideBean1(bgs[i], titles[i], icons[i], says[i], rateon[i], seconrate[i], neededStrike[i]));
        }
    }


    /**
     * 适配器
     */
}
