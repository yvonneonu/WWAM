package com.example.waam;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import com.dingmouren.layoutmanagergroup.echelon.EchelonLayoutManager;

public class EchelonFragment extends Fragment {
    private RecyclerView mRecyclerView;
    private EchelonLayoutManager mLayoutManager;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.activity_discover_event, container, false);
        mRecyclerView = rootView.findViewById(R.id.recycler_view);
        return rootView;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        initData();
    }

    private void initData() {
        mLayoutManager = new EchelonLayoutManager(getContext());
        mRecyclerView.setLayoutManager(mLayoutManager);
        mRecyclerView.setAdapter(new MyAdapter());

    }

    private class MyAdapter extends RecyclerView.Adapter<MyAdapter.ViewHolder> {
        //   private int[] icons = {R.mipmap.header_icon_1, R.mipmap.header_icon_2, R.mipmap.header_icon_3, R.mipmap.header_icon_4};
        private int[] bgs = {R.drawable.eventcardimg, R.drawable.discovermatchesbox, R.drawable.diningout, R.drawable.winetasting};
        private String[] nickNames = {"list of name"};
        private String[] descs = {
                "hello",
                "Test fgutefde",
                "ewjhfdtyewhkjfn",
                "ewfbfwytjdkjhqi8ygd",
                "edfctygqhwvjdyckje"
        };

        @Override
        public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
                View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_echelo, parent, false);
             return new ViewHolder(view);
        }


        @Override
        public void onBindViewHolder(ViewHolder holder, final int position) {
            // holder.icon.setImageResource(icons[position % 4]);
            holder.nickName.setText(nickNames[position % 4]);
            holder.desc.setText(descs[position % 5]);
            holder.bg.setImageResource(bgs[position % 4]);
        }

        @Override
        public int getItemCount() {
            return 60;
        }

        public class ViewHolder extends RecyclerView.ViewHolder {
            ImageView icon;
            ImageView bg;
            TextView nickName;
            TextView desc;

            public ViewHolder(View itemView) {
                super(itemView);
                //icon = itemView.findViewById(R.id.img_icon);
                bg = itemView.findViewById(R.id.img_bg);
                // nickName = itemView.findViewById(R.id.tv_nickname);
                // desc = itemView.findViewById(R.id.tv_desc);
            }
        }
    }

}

