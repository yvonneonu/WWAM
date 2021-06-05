package com.example.waam;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;

import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * o
 * create an instance of this fragment.
 */
public class VideoPicFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    private RecyclerView recyclerView;
    private VideoPicAdapter videoPicAdapter;
    private final WaamUser waamUser;
    private ProgressBar bar;
    private TextView textView;
    private GeneralFactory generalFactory;
    private static final String VIDEOPIC = "videopic";


    public VideoPicFragment(WaamUser waamUser) {
        this.waamUser = waamUser;
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @return A new instance of fragment VideoPicFragment.
     */
    // TODO: Rename and change types and number of parameters
  /*  public static VideoPicFragment newInstance(WaamUser waamUser) {
        VideoPicFragment fragment = new VideoPicFragment();
        Bundle args = new Bundle();
        args.putSerializable(VIDEOWAAM,waamUser);
        fragment.setArguments(args);
        return fragment;
    }*/

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        generalFactory = GeneralFactory.getGeneralFactory(getActivity());


    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_video_pic, container, false);
        recyclerView = view.findViewById(R.id.vidpicrecycler);
        textView = view.findViewById(R.id.textView106);
        bar = view.findViewById(R.id.progressBar3);
        String path = waamUser.getUid();
        Log.d("userId",path);
        generalFactory.loadVidPic(path, new GeneralFactory.LoadVidPic() {
            @Override
            public void loadVidpic(List<VideoPicModel> videoPicModels) {
                videoPicAdapter = new VideoPicAdapter(videoPicModels,getActivity());
                Log.d("loadpic","inside pic");
                if(isAdded()){
                    if(videoPicModels.size() > 0){
                        recyclerView.setVisibility(View.VISIBLE);
                        Log.d("Size",""+videoPicModels.size());
                        recyclerView.setAdapter(videoPicAdapter);
                        recyclerView.setLayoutManager(new GridLayoutManager(getActivity(),3));
                        bar.setVisibility(View.GONE);
                    }else{
                        //you have no media uploaded...
                        recyclerView.setVisibility(View.GONE);
                        String message = "There are no media";
                        bar.setVisibility(View.GONE);
                        textView.setText(message);
                        Log.d("ElseVidpic","I am here running");
                    }

                }else{
                    Log.d("Problem","Not added yet");
                }

                videoPicAdapter.showPicVid(new VideoPicAdapter.MediaListener() {
                    @Override
                    public void mediaListener(int position) {
                        Intent intent = new Intent();
                        intent.putExtra("videoPicModels",videoPicModels.get(position));
                        startActivity(intent);

                    }
                });

            }
        });
        return view;
    }


}