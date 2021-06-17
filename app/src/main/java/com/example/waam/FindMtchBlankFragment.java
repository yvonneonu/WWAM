package com.example.waam;

import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link FindMtchBlankFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class FindMtchBlankFragment extends Fragment {
    private TextView textView, eventText;
  // private machModelAdapter machModelAdapter1;
  // List<matchModel> matchModels = new ArrayList<>();
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    public static final String ARG_PARAM3 = "friendEvent";

    private Boolean fromEvent;

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;


    public FindMtchBlankFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment FindMtchBlankFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static FindMtchBlankFragment newInstance(String param1, String param2) {
        FindMtchBlankFragment fragment = new FindMtchBlankFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        fromEvent = false;
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);

            mParam2 = getArguments().getString(ARG_PARAM2);
            fromEvent = getArguments().getBoolean(ARG_PARAM3,false);

        }
      //  machModelAdapter1 = new machModelAdapter(matchModels,getActivity());


        Fragment fragment;

        if(fromEvent){
            fragment = new DrawerEventFragment();
            FragmentTransaction ft = getChildFragmentManager().beginTransaction();
            ft.replace(R.id.frame,fragment);
            ft.commit();
        }else{
            fragment = new DrawerMatchFragment();
//        eventText.setBackgroundColor(Color.BLUE);

            FragmentTransaction ft = getChildFragmentManager().beginTransaction();
            //eventText.setBackgroundColor(Color.BLUE);
            ft.replace(R.id.frame,fragment);
            ft.commit();
        }


    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_find_mtch_blank, container, false);
        textView = view.findViewById(R.id.mangend);
        eventText = view.findViewById(R.id.event);

        if(fromEvent) eventText.setBackgroundColor(Color.BLUE);
        else textView.setBackgroundColor(Color.BLUE);


        eventText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                if (v.getId() == eventText.getId()){
                    eventText.setBackgroundColor(Color.BLUE);
                    textView.setBackgroundResource(R.drawable.drawerborder);

                    Fragment fragment = new DrawerEventFragment();

                    FragmentTransaction ft = getChildFragmentManager().beginTransaction();
                    ft.replace(R.id.frame,fragment);
                    ft.commit();
                }
            }
        });


        textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                if (v.getId() == textView.getId()){
                    textView.setBackgroundColor(Color.BLUE);
                    eventText.setBackgroundResource(R.drawable.drawerborder);
                    Fragment fragment = new DrawerMatchFragment();

                    FragmentTransaction ft = getChildFragmentManager().beginTransaction();

                    ft.replace(R.id.frame,fragment);
                    ft.commit();
                }

            }
        });
        return view;

    }




    public void pressback(View view) {
        getActivity().finish();
    }


}