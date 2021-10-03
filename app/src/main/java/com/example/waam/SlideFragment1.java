package com.example.waam;

import android.content.Intent;
import android.location.Address;
import android.location.Geocoder;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

public class SlideFragment1 extends Fragment {
    private static final String TAG = "SlideFragment1";
    private ItemTouchHelperCallback mItemTouchHelperCallback;
    private MyAdapter1 mAdapter;
    private ShowPersonListener mShowPersonListener;
    private List<WaamUser> mList = new ArrayList<>();
    private int mLikeCount = 50;
    private int mDislikeCount = 50;
    private ImageView show;
    private int currentPerson ;
    private String token;
    private RecyclerView mRecyclerView;
    private ProgressBar progressBar;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_slide1, container, false);
        token = SharedPref.getInstance(getActivity()).getStoredToken();


        GeneralFactory.getGeneralFactory(getActivity())
                .fetchAllUser(friends -> {
                    mList = friends;
                    initView(rootView);
                    initListener();

                });

        return rootView;
    }

    private void initView(View rootView) {
       mRecyclerView = rootView.findViewById(R.id.recycler_view);
        //mSmileView = rootView.findViewById(R.id.smile_view);

        ImageView aloow1 = rootView.findViewById(R.id.allow);
        // private SmileView mSmileView;
        ImageView deny1 = rootView.findViewById(R.id.deny);
        progressBar = rootView.findViewById(R.id.progress);

        show = rootView.findViewById(R.id.imageView44);

        mAdapter = new MyAdapter1(mList);
        Log.d("warmUser", ""+mList.size());

        mRecyclerView.setAdapter(mAdapter);
        mItemTouchHelperCallback = new ItemTouchHelperCallback(mRecyclerView.getAdapter(), mList);
        ItemTouchHelper mItemTouchHelper = new ItemTouchHelper(mItemTouchHelperCallback);
        SlideLayoutManager mSlideLayoutManager = new SlideLayoutManager(mRecyclerView, mItemTouchHelper);
        mItemTouchHelper.attachToRecyclerView(mRecyclerView);
        mRecyclerView.setLayoutManager(mSlideLayoutManager);

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

        //This might not work
        show.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v){
                //Log.d("Mazi","Adaobi is Mazis daughter ");

                if(mItemTouchHelperCallback != null)
                {


                    Log.d("Mazi","Adaobi is Mazis daughter "+currentPerson);
                }
            }
        });
    }


    private void initListener() {
        mItemTouchHelperCallback.setOnSlideListener(new OnSlideListener() {
            @Override
            public void onSliding(RecyclerView.ViewHolder viewHolder, float ratio, int direction) {
                if (direction == ItemConfig.SLIDING_LEFT) {
                    currentPerson = viewHolder.getAdapterPosition();
                    WaamUser user = mList.get(currentPerson);
                    Log.d("userDisplay", ""+user.getFullname());
                    Log.d("currentPosition", ""+currentPerson);

                } else if (direction == ItemConfig.SLIDING_RIGHT) {
                    currentPerson = viewHolder.getAdapterPosition();
                    WaamUser user = mList.get(currentPerson);
                    Log.d("userDisplay", ""+user.getFullname());
                    Log.d("currentPositionRight", ""+currentPerson);
                   if (mItemTouchHelperCallback != null){
                       int position = viewHolder.getAdapterPosition();
                       WaamUser user1 = mList.get(position);
                       GeneralFactory.getGeneralFactory(getContext()).sendFriendRequest(user1);
                       Log.d("friendrequest", ""+user1);
                   }

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
                //work on tomorrow

                mList.clear(); // clear list

                mAdapter.notifyDataSetChanged();
            }
        });
    }

    private class MyAdapter1 extends RecyclerView.Adapter<MyAdapter1.Viewholder>{

        List<WaamUser> waamUserList;

        public MyAdapter1(List<WaamUser> waamUserList) {
            this.waamUserList = waamUserList;
        }

        @NonNull
        @Override
        public Viewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_slide2, parent, false);
            return new Viewholder(view);
        }

        @Override
        public void onBindViewHolder(@NonNull Viewholder holder, int position) {
            WaamUser bean = mList.get(position);
            progressBar.setVisibility(View.GONE);
            Glide.with(getActivity())
                    .asBitmap()
                    .load(bean.getImageUrl())
                    .into(holder.imgBg);
            holder.tvTitle.setText(bean.getFullname());

            final Geocoder geocoder = new Geocoder(getContext());
            //  if (bean.getZipcode().equals(mList.get(0).getZipcode())){

            final String zip = bean.getZipcode();
            try {
                List<Address> addresses = geocoder.getFromLocationName(zip, 1);
                if (addresses != null && !addresses.isEmpty()) {
                    Address address = addresses.get(0);

                    String abbreviate = address.getCountryName();
                    String[] FullName = address.getCountryName().split("");
                    String state = FullName[1];
                    Log.d("original", state);

                    String[] isoCountryCodes = Locale.getISOCountries();
                    Map<String, String> countryMap = new HashMap<>();
                    Locale locale;
                    String name;

                    // Iterate through all country codes:
                    for (String code : isoCountryCodes) {
                        // Create a locale using each country code
                        locale = new Locale("", code);
                        // Get country name for each code.
                        name = locale.getDisplayCountry();
                        // Map all country names and codes in key - value pairs.
                        countryMap.put(name, code);
                    }

                    Log.d("countryname", ""+countryMap.get(abbreviate));
                    String countryAbbre = countryMap.get(abbreviate);



                    String dateOfBirth = bean.getBirth_date();
                    String[] parts = dateOfBirth.split("-");
                    int part1 = Integer.parseInt(parts[0]);
                    int part2 = Integer.parseInt(parts[1]);
                    int part3 = Integer.parseInt(parts[2]);
                    Log.d("alldate", "" + part1);
                    Log.d("alldate2", "" + part2);
                    Log.d("alldate3", "" + part3);
                        Calendar dob = Calendar.getInstance();
                        Calendar today = Calendar.getInstance();

                        dob.set(part1, part2, part3);

                        int age = today.get(Calendar.YEAR) - dob.get(Calendar.YEAR);

                        if (today.get(Calendar.DAY_OF_YEAR) < dob.get(Calendar.DAY_OF_YEAR)){
                            age--;
                            Log.d("htle", ""+age);
                        }

                        Integer ageInt = new Integer(age);

                        String ageS = ageInt.toString();

                        holder.age1.setText(ageS);

                    Log.d("countryname", countryAbbre);
                    // Return the country code for the given country name using the map.
                    // Here you will need some validation or better yet
                    // a list of countries to give to user to choose from.

                    Log.d("location", address.getCountryName());
                    Log.d("location", "" + address.getLocality());
                    holder.img_user.setText(address.getLocality());
                    holder.country.setText(countryAbbre);


                }


            } catch (IOException e) {
                // handle exception
            }



            // holder.imgBg.setImageResource(bean.getItemBg());

            //holder.imgBg.setImageResource(bean.getImage());


        }


        @Override
        public int getItemCount() {
            //mList.size();
            return waamUserList.size();


        }

        public class Viewholder extends RecyclerView.ViewHolder {

            ImageView imgBg;
            TextView tvTitle;
            TextView img_user, country, age1;

            public Viewholder(@NonNull View itemView) {
                super(itemView);
                imgBg = itemView.findViewById(R.id.img_bg);
                tvTitle = itemView.findViewById(R.id.tv_title);
                img_user = itemView.findViewById(R.id.img_user);
                country = itemView.findViewById(R.id.textView190);
                age1 = itemView.findViewById(R.id.textView192);


                show.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        WaamUser user = waamUserList.get(getAdapterPosition());
                        Intent intent = new Intent(getActivity(),DiscoverDrawerLayerout.class);
                        intent.putExtra("YvonneSleep",user);
                        startActivity(intent);
                    }
                });

            }

        }
    }

    interface ShowPersonListener{
        void personListener(int pos);
    }


}