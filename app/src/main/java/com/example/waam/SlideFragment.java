package com.example.waam;


import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.google.gson.Gson;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


/**
 * Created by 钉某人
 * github: https://github.com/DingMouRen
 * email: naildingmouren@gmail.com
 */

public class SlideFragment extends Fragment {
    private static final String TAG = "SlideFragment";
    private RecyclerView mRecyclerView;
    // private SmileView mSmileView;
    private ImageView deny, aloow;
    private SlideLayoutManager mSlideLayoutManager;
    private ItemTouchHelper mItemTouchHelper;
    private ItemTouchHelperCallback mItemTouchHelperCallback;
    private MyAdapter mAdapter;
    private  List<EventResult> mList = new ArrayList<>();
    private List<EventResult> eventResults;
    private List<EventResult> interestEvent;
    private int mLikeCount = 50;
    private int mDislikeCount = 50;
    String token;
    View rootView;



    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.fragment_slide, container, false);
        token = SharedPref.getInstance(getContext()).getStoredToken();
        initView(rootView);


        eventResults = new ArrayList<>();
        interestEvent = new ArrayList<>();
        //initListener();
        //eventDispaly();
        return rootView;
    }

  /*  @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        token = SharedPref.getInstance(getContext()).getStoredToken();
        mRecyclerView = rootView.findViewById(R.id.recycler_view);

        initListener();
        eventDispaly();
    }*/

    private void initView(View rootView) {
        this.rootView = rootView;
        mRecyclerView = rootView.findViewById(R.id.recycler_view);
        //mSmileView = rootView.findViewById(R.id.smile_view);

        aloow = rootView.findViewById(R.id.allow);
        deny = rootView.findViewById(R.id.deny);
        deny.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), DiscoverDrawerLayerout.class);
                startActivity(intent);
            }
        });
        aloow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), MatchFragment.class);
                startActivity(intent);
            }
        });
       // deny = rootView.findViewById(R.id.deny);

        deny.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


               Log.d("Deny","Deny is clicked");
            }
        });

        //deny.seLike(mLikeCount);
        //mSmileView.setLike(mLikeCount);
        //     mSmileView.setDisLike(mDislikeCount);

        addData();
        eventDispaly();
         //mAdapter = new MyAdapter();
        //mRecyclerView.setAdapter(mAdapter);
      //mItemTouchHelperCallback = new ItemTouchHelperCallback(mRecyclerView.getAdapter(), mList);
        //mItemTouchHelper = new ItemTouchHelper(mItemTouchHelperCallback);
        //mSlideLayoutManager = new SlideLayoutManager(mRecyclerView, mItemTouchHelper);
        //mItemTouchHelper.attachToRecyclerView(mRecyclerView);
         //mRecyclerView.setLayoutManager(mSlideLayoutManager);

    }

    private void initListener() {
        if (mItemTouchHelperCallback != null){
            mItemTouchHelperCallback.setOnSlideListener(new OnSlideListener() {
                @Override
                public void onSliding(RecyclerView.ViewHolder viewHolder, float ratio, int direction) {
                    int position = viewHolder.getAdapterPosition();
                    EventResult eventResult = mList.get(position);
                    if (direction == ItemConfig.SLIDING_LEFT) {
                        Log.d("LEFT","Slide left");
                        Log.d(TAG, "LEFT" + eventResult.getTitle());

                    } else if (direction == ItemConfig.SLIDING_RIGHT) {
                        if(interestEvent.size() > 0){
                            for(int i = 0 ; i < interestEvent.size(); i++){
                                if(interestEvent.get(i).getId().equals(eventResult.getId())){
                                    Log.d("Added","Interest already added");
                                }else{
                                    interestEvent.add(eventResult);
                                }
                            }
                        }else{
                            interestEvent.add(eventResult);
                        }
                        Log.d("RIGHT","Slide right");
                        Log.d(TAG, "RIGHT" + eventResult.getTitle());
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
                    eventResults.clear();
                    eventResults.addAll(mList);

                }
            });
        }

    }

    /**
     * 向集合中添加数据
     */
    private void addData(){
        String[] icons = {"$389","$337", "$675","$389","$389","$389", "$389"};

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
           // mList.add(new EventResult(bgs[i],titles[i],icons[i],says[i], rateon[i], seconrate[i], neededStrike[i]));
        }
    }


    private void eventDispaly(){
        Call<EventRecordmodel> eventRecordmodelCall = ApiClient.getService().getEvent("Bearer "+token);
        eventRecordmodelCall.enqueue(new Callback<EventRecordmodel>() {
            @Override
            public void onResponse(Call<EventRecordmodel> call, Response<EventRecordmodel> response) {
                if (!response.isSuccessful()){
                    String message = "No Event";
                    Toast.makeText(getContext(), message, Toast.LENGTH_LONG).show();
                    Log.d("event", response.message());
                    Log.d("event1", response.errorBody().toString());
                    return;
                }

                // eventResults = response.body();


                EventRecordmodel eventRecordmodel = response.body();
                eventRecordmodel.getEvenRecord();

                eventResults = response.body().getEvenRecord();
                mList = response.body().getEvenRecord();
                initListener();
                mAdapter = new MyAdapter();
                mRecyclerView.setAdapter(mAdapter);

                mItemTouchHelperCallback = new ItemTouchHelperCallback(mRecyclerView.getAdapter(), mList);
                mItemTouchHelper = new ItemTouchHelper(mItemTouchHelperCallback);

                mSlideLayoutManager = new SlideLayoutManager(mRecyclerView, mItemTouchHelper);
                mItemTouchHelper.attachToRecyclerView(mRecyclerView);
                mRecyclerView.setLayoutManager(mSlideLayoutManager);
                //eventResults.add()



                  /*for (int i = 0; i < mList.size(); i++){
                      mList.get(i).getPhoto();
                      mList.get(i).getTitle();
                      mList.get(i).getShort_description();
                     // mList.add( new EventResult()getPhoto().toString());
                    //  mList.add(new EventResult(i).getPhoto())
                    //  eventResults.add(mList.get(i).getPhoto())
                    // SlideBean ben = mList.add(eventResults.get(i).getPhoto());

                      //mList.add(mList.get(i).getPhoto().toString().toString())
                    // mList.add(eventRecordmodel.getEvenRecord().get(i).getPhoto());
                    // mList.add(eventResults.get(i).getPhoto());
                    //name.add(eventResults.get(i).getPhoto());

                    // name.add(eventResults.get(i).getTitle());
                    // name.add(eventResults.get(i).getShort_description());


                }*/



                Log.d("BodyEvent",new Gson().toJson(response.body()));
            }

            @Override
            public void onFailure(Call<EventRecordmodel> call, Throwable t) {

                Log.d("no event",t.getMessage());
            }
        });
    }

    /**
     * 适配器
     */
    class MyAdapter extends RecyclerView.Adapter<MyAdapter.ViewHolder> {

        @NotNull
        @Override
        public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_slide, parent, false);
            return new ViewHolder(view);
        }

        @Override
        public void onBindViewHolder(ViewHolder holder, int position) {
            EventResult bean = mList.get(position);
           /* File path = Environment.getExternalStoragePublicDirectory(
                    Environment.DIRECTORY_PICTURES
            );
            File file = new File(path, "DemoPicture.jpg");
            try {
                path.mkdir();
            } catch (Exception e) {
                e.printStackTrace();
            }*/
            RequestOptions requestOptions = new RequestOptions();
            // holder.imgBg.setImageResource(bean.getItemBg());
           Glide.with(getActivity())
                    .asBitmap()
                    .load(bean.getPhoto())
                   .apply(requestOptions)
                    .into(holder.imgBg);
            //holder.imgBg.setImageResource(bean.getmItemBg());
            holder.tvTitle.setText(bean.getTitle());
            holder.userIcon.setText(bean.getShort_description());
           // holder.userSay.setText(bean.getmUserSay());
           // holder.firstra.setText(bean.getMfirstrate());
           // holder.secondra.setText(bean.getMsecondrating());
            //holder.strike.setText(bean.getNumberSrike());

            //holder.strike.setPaintFlags(holder.strike.getPaintFlags()| Paint.STRIKE_THRU_TEXT_FLAG);
        }


        @Override
        public int getItemCount() {
            return mList.size();
        }

        public class ViewHolder extends RecyclerView.ViewHolder {
            ImageView imgBg;
            TextView userIcon;
            TextView tvTitle;
            TextView userSay, firstra, secondra, strike;

            public ViewHolder(View itemView) {
                super(itemView);
                imgBg = itemView.findViewById(R.id.img_bg);
                userIcon = itemView.findViewById(R.id.img_user);
                tvTitle = itemView.findViewById(R.id.tv_title);
                userSay = itemView.findViewById(R.id.tv_user_say);

                secondra = itemView.findViewById(R.id.secon_rating);


            }
        }

    }
}
