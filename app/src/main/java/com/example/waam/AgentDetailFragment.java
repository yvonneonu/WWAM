package com.example.waam;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

/**
 * A simple {@link Fragment} subclass.
 * create an instance of this fragment.
 */
public class AgentDetailFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    private AgentModel agentModel;

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    /*public AgentDetailFragment() {
        // Required empty public constructor
    }*/
    public AgentDetailFragment(AgentModel agentModel){
        this.agentModel = agentModel;
    }
    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.

     * @return A new instance of fragment AgentDetailFragment.
     */
    // TODO: Rename and change types and number of parameters
    /*public static AgentDetailFragment newInstance(AgentModel agentModel) {
        AgentDetailFragment fragment = new AgentDetailFragment();
        Bundle args = new Bundle();
        args.putSerializable("agentmodee", agentModel);
        fragment.setArguments(args);
        return fragment;
    }*/

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }

      //agentModel = (AgentModel) getActivity().getIntent().getSerializableExtra("agentmodee");
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_agent_detail, container, false);
        ImageView imageone = view.findViewById(R.id.imageView36);
        TextView textViewname =  view.findViewById(R.id.textView88);
        TextView textViewRate = view.findViewById(R.id.textView90);
        TextView ratingValue = view.findViewById(R.id.textView89);
        Glide.with(getActivity())
                .asBitmap()
                .load(agentModel.getImage())
                .into(imageone);
        textViewname.setText(agentModel.getName());
        ratingValue.setText(agentModel.getRating());
        textViewRate.setText(agentModel.getRating1());
        return view;
    }
}