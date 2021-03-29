package com.example.waam;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import java.util.ArrayList;
import java.util.List;

public class DiscoverEvent extends AppCompatActivity {
    private FragmentManager mFragmentManager;
    private List<Fragment> mFragments = new ArrayList<>();
    private List<String> mManagerNames = new ArrayList<>();

    private Fragment mCurrentFragment;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_discover_event);
        mFragmentManager = getSupportFragmentManager();
        intFragments();
    }

    private void intFragments() {

    }
}