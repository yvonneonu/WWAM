package com.example.waam;

import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import java.util.ArrayList;
import java.util.List;

public class DiscoverEvent extends AppCompatActivity {

        private static final String TAG = "MainActivity";
        private TextView mTvTitle;
        private Toolbar mToolbar;
        private FragmentManager mFragmentManager;
        private List<Fragment> mFragments = new ArrayList<>();//存储所有的Fragment对象
        private List<String> mManagerNames = new ArrayList<>();//存储与Fragment对应的LayoutManager的名称

        private Fragment mCurrentFragment;

        @Override
        protected void onCreate(Bundle savedInstanceState) {
            requestWindowFeature(Window.FEATURE_NO_TITLE);
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_discover_event);
            getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

            mToolbar = findViewById(R.id.tool_bar);
            setSupportActionBar(mToolbar);
           mTvTitle = findViewById(R.id.tv_title);
            mFragmentManager = getSupportFragmentManager();

            initFragments();


        }

        private void initFragments() {
            SlideFragment slideFragment = new SlideFragment();//滑动布局
            mFragments.add(slideFragment);
            mManagerNames.add("SlideLayoutManager");

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

       /* @Override
        public boolean onCreateOptionsMenu(Menu menu) {
            getMenuInflater().inflate(R.menu.menu, menu);
            return true;
        }*/

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
            finish();
        /*Intent intent = new Intent(DiscoverEvent.this, DiscoverDrawerLayerout.class);
        startActivity(intent);*/
    }
}