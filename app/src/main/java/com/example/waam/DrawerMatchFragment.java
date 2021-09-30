package com.example.waam;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link DrawerMatchFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class DrawerMatchFragment extends Fragment {
   private RecyclerView recyclerView;
   private machModelAdapter machModelAdapter;
   // matchModel matchModel;
    private List<matchModel> matchModels = new ArrayList<>();

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public DrawerMatchFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment DrawerMatchFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static DrawerMatchFragment newInstance(String param1, String param2) {
        DrawerMatchFragment fragment = new DrawerMatchFragment();
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

        if(machModelAdapter != null){
            machModelAdapter.MatchMethod(new machModelAdapter.onMatchListener() {
                @Override
                public void OnMatchClick(int position) {
                    matchModel match = matchModels.get(position);
                    Intent intent = new Intent(getActivity(), Unfriend.class);
                    startActivity(intent);
                }
            });
        }

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_drawer_match, container, false);
        design();
        Log.d("match", ""+matchModels.size());
        recyclerView = view.findViewById(R.id.recycler1);
        machModelAdapter = new machModelAdapter(matchModels,getActivity());

        machModelAdapter.DotMethod(new machModelAdapter.onDotListener() {
            @Override
            public void OnDotClick(int position) {
                matchModel match = matchModels.get(position);

                UnfriendBottomsheet bottomsheet = new UnfriendBottomsheet();
                bottomsheet.show(getFragmentManager(), "TAG");
                //  bottomsheet.
            }
        });

        machModelAdapter.MatchMethod(new machModelAdapter.onMatchListener() {
            @Override
            public void OnMatchClick(int position) {
                matchModel match = matchModels.get(position);

                UnfriendBottomsheet bottomsheet = new UnfriendBottomsheet();
                bottomsheet.show(getFragmentManager(), "TAG");

              //  bottomsheet.

               // Intent intent = new Intent(getActivity(), Unfriend.class);
              //  Log.d("modelAdapter", "true");
                //startActivity(intent);
            }
        });

        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false);

        recyclerView.setAdapter(machModelAdapter);
        recyclerView.setLayoutManager(layoutManager);
        return view;


    }


    private void design() {
        int[] display = {
                R.drawable.topnav_profile,
                R.drawable.top_scroll_profile_img,
                R.drawable.profile_img_user,
                R.drawable.group_img_2,
                R.drawable.topnav_profile,
                R.drawable.top_scroll_profile_img,
                R.drawable.profile_img_user

        };
        String[] dispChat = {"Ebuka, 38", "Blessing, 45", "Brown, 32", "Alexander, 31", "Chris, 25", "Peter, 18", "LordBroke, 28"
        };
        String[] message = {"Las Vegas, NV", "Lagos state, Nigeria", "Las Vegas, NV", "Las Vegas, NV§", "Las Vegas, NV",
                "Las Vegas, NV", "Las Vegas, NV"

        };

        for (int i = 0; i < display.length; i++) {
            matchModels.add(new matchModel(display[i], dispChat[i], message[i]));
        }
    }

}