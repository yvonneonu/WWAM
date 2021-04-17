package com.example.waam;

import android.content.Intent;
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
 * Use the {@link AgentFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class AgentFragment extends Fragment {
    private RecyclerView recyclerView;
    private RecyclerView recyclerView1;
    private AgentAdapter agentAdapter;


    private List<AgentModel> agentModelList = new ArrayList<>();


    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public AgentFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment AgentFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static AgentFragment newInstance(String param1, String param2) {
        AgentFragment fragment = new AgentFragment();
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
        addAgent();
        View view = inflater.inflate(R.layout.fragment_agent, container, false);
      //  View
        recyclerView = view.findViewById(R.id.recyclerView);
       //recyclerView1 = view.findViewById(R.id.rec);

        agentAdapter = new AgentAdapter(agentModelList,getActivity());
        //agentAdapter1 = new AgentAdapter1(agentModel1s, getActivity());

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL,false);
       // LinearLayoutManager linearLayoutManager1 = new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false);


        recyclerView.setAdapter(agentAdapter);
      //  recyclerView1.setAdapter(agentAdapter1);

        recyclerView.setLayoutManager(linearLayoutManager);

        agentAdapter.AgentMethod(new AgentAdapter.OnAgentListener() {
            @Override
            public void onAgentCick(int position) {
                Intent intent = new Intent(getActivity(), AgentProfile.class);
                startActivity(intent);
            }
        });
        //recyclerView1.setLayoutManager(linearLayoutManager1);
        return view;


    }

    private void addAgent() {
        int[] display = {
                R.drawable.agent_1_img,
                R.drawable.agent_2_img,
                R.drawable.agent_3_img,
                R.drawable.agent_4_img,
                R.drawable.agent_5_img,
                R.drawable.agent_6_img,
                R.drawable.agent_1_img
        };
        String[] name = {"Ebuka Obi", "Blessing Peter", "Brown White", "Alexander Helger", "Chris Paul", "Peter Mac", "LordBroke Saint"
        };

        String[] rating = {"3.5", "5.0", "4.2", "5.1", "4.1", "7.0", "5.8"

        };
        String[] rating2 = {"(101 Ratings)", "(109 Ratings)", "(115 Ratings)", "(209 Ratings)", "(159 Ratings)", "(100 Ratings)", "(119 Ratings)"

        };

        int[] display1 = {
                R.drawable.agent_6_img,
                R.drawable.agent_5_img,
                R.drawable.agent_4_img,
                R.drawable.agent_3_img,
                R.drawable.agent_2_img,
                R.drawable.agent_1_img,
                R.drawable.agent_6_img
        };
        String[] name1 = {"LordBroke Saint", "Brown White", "Ebuka Obi", "Blessing Peter", "Peter Mac", "Alexander Helger", "Chris Paul"
        };

        String[] rating1 = {"4.5", "3.0", "3.2", "4.1", "3.1", "6.0", "4.8"

        };
        String[] rating3 = {"(102 Ratings)", "(105 Ratings)", "(103 Ratings)", "(109 Ratings)", "(150 Ratings)", "(101 Ratings)", "(115 Ratings)"

        };



        for (int i = 0; i < display.length; i++){
            agentModelList.add(new AgentModel(display[i], name[i], rating[i], rating2[i], display1[i], name1[i], rating1[i], rating3[i]));
        }
    }


}