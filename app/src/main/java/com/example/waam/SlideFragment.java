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
    String id = "1";
    String event = "1";



    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.fragment_slide, container, false);
        token = SharedPref.getInstance(getContext()).getStoredToken();
        initView(rootView);
        eventResults = new ArrayList<>();
        interestEvent = new ArrayList<>();
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

        ImageView viewProfilefragment = rootView.findViewById(R.id.imageView44);

        ImageView aloow = rootView.findViewById(R.id.allow);
        ImageView deny = rootView.findViewById(R.id.deny);
        viewProfilefragment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
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
                Intent intent = new Intent(getActivity(), MatchActivity.class);
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

        //addData();
        eventDispaly();

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

                        //display();
                        EventUserPost eventUserPost = new EventUserPost(id, eventResult.getId());
                        eventUser(eventUserPost);
                      /*  if(interestEvent.size() > 0){
                            for(int i = 0 ; i < interestEvent.size(); i++){
                                if(interestEvent.get(i).getId().equals(eventResult.getId())){
                                    Log.d("Added","Interest already added");
                                }else{
                                    interestEvent.add(eventResult);
                                }
                            }
                        }else{
                            interestEvent.add(eventResult);
                        }*/
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
                    //eventResults.clear();
                    //eventResults.addAll(mList);
                    //Log.d("hyuh", "help");

                    eventDispaly();
                }
            });
        }

    }

    /**
     * 向集合中添加数据
     */


    private void eventUser(EventUserPost eventUserPost){
        Call<EventUserPostResponse> eventUserPostResponseCall = ApiClient.getService().eventUser( eventUserPost, "Bearer "+token);
        eventUserPostResponseCall.enqueue(new Callback<EventUserPostResponse>() {
            @Override
            public void onResponse(Call<EventUserPostResponse> call, Response<EventUserPostResponse> response) {
                if (!response.isSuccessful()){
                    String message = "Event not sent";
                    Toast.makeText(getActivity(), message, Toast.LENGTH_LONG).show();
                    Log.d("Event",response.message());
                    Log.d("Event",response.errorBody().toString());
                    return;
                }
                String message = "Event Successfully clicked";
                Toast.makeText(getActivity(), message, Toast.LENGTH_LONG).show();
                Log.d("Body",new Gson().toJson(response.body()));
            }

            @Override
            public void onFailure(Call<EventUserPostResponse> call, Throwable t) {

                Log.d("no image",t.getMessage());
            }
        });
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

                eventResults = response.body().getEvenRecord();
                mList = response.body().getEvenRecord();

                mAdapter = new MyAdapter();
                mRecyclerView.setAdapter(mAdapter);

                mItemTouchHelperCallback = new ItemTouchHelperCallback<>(mRecyclerView.getAdapter(), mList);
                mItemTouchHelper = new ItemTouchHelper(mItemTouchHelperCallback);
                initListener();
                mSlideLayoutManager = new SlideLayoutManager(mRecyclerView, mItemTouchHelper);
                mItemTouchHelper.attachToRecyclerView(mRecyclerView);
                mRecyclerView.setLayoutManager(mSlideLayoutManager);



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
            RequestOptions requestOptions = new RequestOptions();
           Glide.with(getActivity())
                    .asBitmap()
                    .load(bean.getPhoto())
                   .apply(requestOptions)
                    .into(holder.imgBg);
            holder.tvTitle.setText(bean.getTitle());
            holder.userIcon.setText(bean.getShort_description());

        }


        @Override
        public int getItemCount() {
            return mList.size();
        }

        public class ViewHolder extends RecyclerView.ViewHolder {
            ImageView imgBg;
            TextView userIcon;
            TextView tvTitle;

            public ViewHolder(View itemView) {
                super(itemView);
                imgBg = itemView.findViewById(R.id.img_bg);
                userIcon = itemView.findViewById(R.id.tv_user_say);
                tvTitle = itemView.findViewById(R.id.tv_title);


            }
        }

    }
}
