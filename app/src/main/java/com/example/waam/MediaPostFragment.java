package com.example.waam;

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

import com.google.firebase.auth.FirebaseAuth;

import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link MediaPostFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class MediaPostFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    private RecyclerView recyclerView;
    private VideoPicAdapter videoPicAdapter;
    private TextView textView;
    private FirebaseAuth mAuth;
    private ProgressBar bar;
    private String id;

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public MediaPostFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment MediaPostFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static MediaPostFragment newInstance(String param1, String param2) {
        MediaPostFragment fragment = new MediaPostFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }

        mAuth = FirebaseAuth.getInstance();
        id = mAuth.getUid();


    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_media_post, container, false);

        recyclerView = view.findViewById(R.id.imagerecycler);
        textView = view.findViewById(R.id.textView106);
        bar = view.findViewById(R.id.progressBar3);
        GeneralFactory.getGeneralFactory(getContext())
                .loadVidPic(id, new GeneralFactory.LoadVidPic() {
                    @Override
                    public void loadVidpic(List<VideoPicModel> videoPicModels) {
                        videoPicAdapter = new VideoPicAdapter(videoPicModels,getActivity());
                        Log.d("loadpic","inside pic");
                        if(isAdded()){
                            if(videoPicModels.size() > 0){
                                recyclerView.setVisibility(View.VISIBLE);
                                textView.setVisibility(View.GONE);
                                recyclerView.setAdapter(videoPicAdapter);
                                recyclerView.setLayoutManager(new GridLayoutManager(getActivity(),3));
                                bar.setVisibility(View.GONE);
                            }else{
                                //you have no media uploaded...
                                recyclerView.setVisibility(View.GONE);
                                textView.setVisibility(View.VISIBLE);
                                String message = "There are no media";
                                bar.setVisibility(View.GONE);
                                textView.setText(message);
                                Log.d("ElseVidpic","I am here running");
                            }

                        }else{
                            Log.d("Problem","Not added yet");
                        }
                    }
                });

        return view;
    }
}