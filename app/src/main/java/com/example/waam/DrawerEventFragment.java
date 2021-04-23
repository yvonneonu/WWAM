package com.example.waam;

import android.os.Bundle;
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
 * Use the {@link DrawerEventFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class DrawerEventFragment extends Fragment {
    private RecyclerView recyclerView;
    private List<eventdesignmodel> eventdesignmodels = new ArrayList<>();
    private eventmodelAdapter eventmodelAdapter;

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public DrawerEventFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment DrawerEventFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static DrawerEventFragment newInstance(String param1, String param2) {
        DrawerEventFragment fragment = new DrawerEventFragment();
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
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_drawer_event, container, false);
        eventDesign();
        recyclerView = view.findViewById(R.id.recycler2);
        eventmodelAdapter = new eventmodelAdapter(eventdesignmodels,getActivity());
        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false);

        recyclerView.setAdapter(eventmodelAdapter);
        recyclerView.setLayoutManager(layoutManager);
        return view;
    }

    private void eventDesign() {
        int[] display = {
                R.drawable.topnav_profile,
                R.drawable.top_scroll_profile_img,
                R.drawable.profile_img_user,
                R.drawable.group_img_2,
                R.drawable.topnav_profile,


        };
        String[] dispChat = {"Mystere by Circqur du Soleil at Treasure Island", "The Beatles@ LOVE by Cirque du Soleil",
                "NamesCon Global 2019", "Micheal Jackson One By Cirque Du Soleil",
                "Gwen Stefani: Just A Girl Concert"
        };
        String[] message = {"$45", "$50", "$85", "$25", "$95",

        };
        String[] price = {"$115", "$50", "$1,346", "Per Adult", "Per Adult",

        };
        String[] rate = {"5.5", "4.5", "8.5", "3.5", "6.5",

        };
        String[] rate2 = {"(100)", "(119)", "(200)", "(320)", "(240)",

        };

        for (int i = 0; i < display.length; i++) {
            eventdesignmodels.add(new eventdesignmodel(display[i], dispChat[i], message[i], price[i], rate[i], rate2[i]));
        }
    }
}