package com.example.waam;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.Objects;

public class AgentDetailFragment extends Fragment {

    private final AgentModel agentModel;

    public AgentDetailFragment(AgentModel agentModel){
        this.agentModel = agentModel;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
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
        Glide.with(Objects.requireNonNull(getActivity()))
                .asBitmap()
                .load(agentModel.getImage())
                .into(imageone);
        textViewname.setText(agentModel.getName());
        ratingValue.setText(agentModel.getRating());
        textViewRate.setText(agentModel.getRating1());
        return view;
    }
}