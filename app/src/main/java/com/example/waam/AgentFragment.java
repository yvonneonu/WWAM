package com.example.waam;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link AgentFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class AgentFragment extends Fragment {
    private RecyclerView recyclerView;
    private AgentAdapter agentAdapter;
    private List<AgentModel> agentModelList;

    private GeneralFactory generalFactory;

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
        generalFactory = GeneralFactory.getGeneralFactory(getActivity());
        agentModelList = generalFactory.getAgentModelList();

        agentAdapter = new AgentAdapter(agentModelList,getActivity());

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_agent, container, false);

        recyclerView = view.findViewById(R.id.recyclerView);
        GridLayoutManager gridLayoutManager = new GridLayoutManager(getActivity(), 2);
        recyclerView.setAdapter(agentAdapter);

        recyclerView.setLayoutManager(gridLayoutManager);

        agentAdapter.AgentMethod(new AgentAdapter.OnAgentListener() {
            @Override
            public void onAgentCick(int position) {
                AgentModel agentModel = agentModelList.get(position);
                Fragment fr = new AgentDetailFragment(agentModel);
                getActivity().getSupportFragmentManager().beginTransaction()
                        .replace(R.id.fragmentcontainer,fr)
                        .addToBackStack(null)
                        .commit();
            }

        });
        //recyclerView1.setLayoutManager(linearLayoutManager1);
        return view;


    }




}