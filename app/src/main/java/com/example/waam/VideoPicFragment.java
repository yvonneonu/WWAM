package com.example.waam;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;

import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link VideoPicFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class VideoPicFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    private RecyclerView recyclerView;
    private VideoPicAdapter videoPicAdapter;
    private GeneralFactory generalFactory;
    private ProgressBar bar;
    private TextView textView;

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public VideoPicFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment VideoPicFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static VideoPicFragment newInstance(String param1, String param2) {
        VideoPicFragment fragment = new VideoPicFragment();
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

        generalFactory = GeneralFactory.getGeneralFactory(getActivity());

        generalFactory.loadVidPic("", new GeneralFactory.LoadVidPic() {
            @Override
            public void loadVidpic(List<VideoPicModel> videoPicModels) {
                if(isAdded()){
                    if(videoPicModels.size() > 0){
                        videoPicAdapter = new VideoPicAdapter(videoPicModels,getActivity());
                        recyclerView.setAdapter(videoPicAdapter);
                        recyclerView.setLayoutManager(new GridLayoutManager(getActivity(),3));
                        bar.setVisibility(View.GONE);
                    }else{
                        //you have no media uploaded...
                        String message = "There are no media";
                        bar.setVisibility(View.GONE);
                        textView.setText(message);
                    }

                }
            }
        });
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_video_pic, container, false);
        recyclerView = view.findViewById(R.id.vidpicrecycler);
        textView = view.findViewById(R.id.textView106);
        bar = view.findViewById(R.id.progressBar3);
        return view;
    }


}