package com.example.waam;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import java.util.ArrayList;
import java.util.List;

public class MatchActivity extends AppCompatActivity {


    private static final String TAG = "SeconFragment";
    private TextView mTvTitle;
    private Toolbar mToolbar;
    private FragmentManager mFragmentManager;
    private List<Fragment> mFragments = new ArrayList<>();//存储所有的Fragment对象
    private List<String> mManagerNames = new ArrayList<>();//存储与Fragment对应的LayoutManager的名称

    private Fragment mCurrentFragment;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_match_fragment);
        mToolbar = findViewById(R.id.tool_bar);
        setSupportActionBar(mToolbar);
        mTvTitle = findViewById(R.id.tv_title);
        mFragmentManager = getSupportFragmentManager();

        initFragments();
    }
    private void initFragments() {
        SlideFragment1 slideFragment = new SlideFragment1();//滑动布局
        mFragments.add(slideFragment);
        mManagerNames.add("SlideLayoutManager1");

        mFragmentManager.beginTransaction()
                .add(R.id.container_layout, mFragments.get(0))
                //  .add(R.id.container_layout,mFragments.get(1))
                //  .add(R.id.container_layout,mFragments.get(2))
//                    .hide(mFragments.get(2))
//                    .hide(mFragments.get(1))
                .show(mFragments.get(0))
                .commit();
        mCurrentFragment = mFragments.get(0);
//            mTvTitle.setText(mManagerNames.get(0));
        switchFragment(0);
    }
    private void switchFragment(int position) {
        mFragmentManager.beginTransaction()
                .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE)
                // .hide(mCurrentFragment)
                .show(mFragments.get(position))
                .commit();
        mCurrentFragment = mFragments.get(position);
//            mTvTitle.setText(mManagerNames.get(position));
    }

    public void backtologin(View view) {
        Intent intent = new Intent(MatchActivity.this, DiscoverDrawerLayerout.class);
        startActivity(intent);
    }
}