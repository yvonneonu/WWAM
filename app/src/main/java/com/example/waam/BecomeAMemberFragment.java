package com.example.waam;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.text.SpannableString;
import android.text.Spanned;
import android.text.style.BulletSpan;
import android.text.style.ForegroundColorSpan;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.card.MaterialCardView;

import java.util.Objects;

import worker8.com.github.radiogroupplus.RadioGroupPlus;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link BecomeAMemberFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class BecomeAMemberFragment extends Fragment implements View.OnClickListener {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    private MaterialCardView linearLayoutOne, linearLayoutTwo, linearLayoutThree, linearLayoutFour;
    private LinearLayout layoutPlan;
    private LinearLayout layoutPlantwo;
    private LinearLayout layoutPlanthree;
    private LinearLayout layoutPlanfour;
    private View firstView, secondView, thirdView, fourthView;


    TextView[] firstFeature, secondFeature, thirdFeature, fourFeature;
    private View[] arrayView;
    private RadioGroupPlus radioGroupPlus;
    //private MenuItem menuItem;

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;


    public BecomeAMemberFragment() {
        // Required empty public constructor
    }
    // TODO: Rename and change types and number of parameters
    public static BecomeAMemberFragment newInstance(String param1, String param2) {
        BecomeAMemberFragment fragment = new BecomeAMemberFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d("TAG","I am in oncreate");

        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);

        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View view = inflater.inflate(R.layout.fragment_become_a_member, container, false);
        setHasOptionsMenu(true);

        SpannableString string = new SpannableString("Bullet point");
        string.setSpan(new BulletSpan(),0, 12, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);


        linearLayoutOne = view.findViewById(R.id.layone);
        linearLayoutTwo = view.findViewById(R.id.laytwo);
        linearLayoutThree = view.findViewById(R.id.laythree);
        linearLayoutFour = view.findViewById(R.id.layfour);
        LinearLayout linearLayoutFive = view.findViewById(R.id.purchase);

        radioGroupPlus = view.findViewById(R.id.plus);

        layoutPlan = view.findViewById(R.id.layoutplan);
        layoutPlantwo = view.findViewById(R.id.layoutplantwo);
        layoutPlanthree = view.findViewById(R.id.layoutplanthree);
        layoutPlanfour = view.findViewById(R.id.layoutplanfour);

        TextView firstTxt = view.findViewById(R.id.textView28);
        TextView secondTxt = view.findViewById(R.id.textView29);
        TextView thirdTxt = view.findViewById(R.id.textView32);
        TextView fourthTxt = view.findViewById(R.id.textView33);

        TextView firsttin = view.findViewById(R.id.textView34fourtin);
        TextView secondtin = view.findViewById(R.id.textView35fourtin);
        TextView thirdtin = view.findViewById(R.id.textView36fourtin);

        TextView fourtin = view.findViewById(R.id.textView37fourtin);

        TextView firsttwe = view.findViewById(R.id.textView38twe);
        TextView secondtwe = view.findViewById(R.id.textView39twe);
        TextView thirdtwe = view.findViewById(R.id.textView40twe);
        TextView fourttwe = view.findViewById(R.id.textView41twe);

        TextView firsthun = view.findViewById(R.id.textView42hun);
        TextView secondhun = view.findViewById(R.id.textView43hun);
        TextView thirdhun = view.findViewById(R.id.textView44hun);
        TextView fourthun = view.findViewById(R.id.textView45hun);


        firstView = view.findViewById(R.id.view7);
        secondView = view.findViewById(R.id.view8);
        thirdView = view.findViewById(R.id.view9);
        fourthView = view.findViewById(R.id.view11);
        firstFeature = new TextView[]{view.findViewById(R.id.textViewone),view.findViewById(R.id.textViewtwo),view.findViewById(R.id.textViewthree)};
        secondFeature = new TextView[]{view.findViewById(R.id.textViewfour),view.findViewById(R.id.textViewfive),view.findViewById(R.id.textViewsix)};
        thirdFeature = new TextView[]{view.findViewById(R.id.textViewseven),view.findViewById(R.id.textVieweight),view.findViewById(R.id.textViewnine)};
        fourFeature = new TextView[]{view.findViewById(R.id.textView19),view.findViewById(R.id.textView21),view.findViewById(R.id.textView20)};


        TextView[] arrayTin = new TextView[]{firsttin,secondtin,thirdtin,fourtin};
        TextView[] arrayTwe = new TextView[]{firsttwe,secondtwe,thirdtwe,fourttwe};
        TextView[] arrayHun = new TextView[]{firsthun,secondhun,thirdhun,fourthun};

        //Boolean[] state = new Boolean[]{firstActive,secondActive,thirdActive,fourthActive};
        arrayView = new View[]{firstView,secondView, thirdView,fourthView};


        linearLayoutOne.setOnClickListener(this);
        linearLayoutTwo.setOnClickListener(this);
        linearLayoutThree.setOnClickListener(this);
        linearLayoutFour.setOnClickListener(this);
        linearLayoutFive.setOnClickListener(this);


        firstTxt.setText(string);
        secondTxt.setText(string);
        thirdTxt.setText(string);
        fourthTxt.setText(string);

       for(int i= 0 ; i < arrayTin.length ; i++){
           arrayTin[i].setText(string);
       }

        for(int i= 0 ; i < arrayTin.length ; i++){
            arrayTwe[i].setText(string);
        }

        for(int i= 0 ; i < arrayTin.length ; i++){
            arrayHun[i].setText(string);
        }


        AppCompatActivity activity = (AppCompatActivity) getActivity();
        assert activity != null;
        Objects.requireNonNull(activity.getSupportActionBar()).setTitle("Upgrade Your Plan");
        return view;

    }


    @Override
    public void onCreateOptionsMenu(@NonNull Menu menu, @NonNull MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        inflater.inflate(R.menu.main_menu, menu);
        //menuItem = menu.findItem(R.id.invite);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
            Toast.makeText(getActivity(), "Am a responsing to plus", Toast.LENGTH_SHORT).show();
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onClick(View v) {

        final int layoutOne = R.id.layone;
        final int layoutTwo = R.id.laytwo;
        final int layoutThree = R.id.laythree;
        final int layoutFour = R.id.layfour;
        final int purchase = R.id.purchase;

        switch (v.getId()){
            case layoutOne:
                setClicked(linearLayoutTwo,linearLayoutThree,linearLayoutFour
                        ,linearLayoutOne,layoutPlantwo,layoutPlanthree,
                        layoutPlanfour,layoutPlan,secondFeature,thirdFeature,fourFeature,firstFeature,firstView);
                break;
            case layoutTwo:
                setClicked(linearLayoutOne,linearLayoutThree,linearLayoutFour,
                        linearLayoutTwo,layoutPlan,layoutPlanthree,layoutPlanfour,
                        layoutPlantwo,firstFeature,thirdFeature,fourFeature,secondFeature,secondView);
                break;

            case layoutThree:
                setClicked(linearLayoutOne,linearLayoutTwo,linearLayoutFour,
                        linearLayoutThree,layoutPlan,layoutPlantwo,
                        layoutPlanfour,layoutPlanthree,firstFeature,secondFeature,
                        fourFeature,thirdFeature,thirdView);
                break;

            case layoutFour:
                setClicked(linearLayoutOne,linearLayoutTwo,linearLayoutThree,
                        linearLayoutFour,layoutPlan,layoutPlantwo,
                        layoutPlanthree,layoutPlanfour,firstFeature,
                        secondFeature,thirdFeature,fourFeature,fourthView);
                break;

            case purchase:
                int choseId = radioGroupPlus.getCheckedRadioButtonId();
                switch (choseId){
                    //uu...
                }
                Intent intent = new Intent(getActivity(),PaymentPage.class);
                startActivity(intent);
        }
    }


    private void setClicked(MaterialCardView first, MaterialCardView second,
                            MaterialCardView third,MaterialCardView original,
                            LinearLayout linearLayoutone,LinearLayout linearLayouttwo,
                            LinearLayout linearLayoutthree,LinearLayout linearLayoutOriginal,
                            TextView[] arrayOne, TextView[] arrayTwo, TextView[] arrayThree, TextView[] arrayReal,View view){
        first.setBackgroundColor(Objects.requireNonNull(getActivity()).getResources().getColor(R.color.white));
        second.setBackgroundColor(getActivity().getResources().getColor(R.color.white));
        third.setBackgroundColor(getActivity().getResources().getColor(R.color.white));
        original.setBackgroundColor(getActivity().getResources().getColor(R.color.priceBackgrouond));
        linearLayoutone.setVisibility(View.GONE);
        linearLayouttwo.setVisibility(View.GONE);
        linearLayoutthree.setVisibility(View.GONE);
        linearLayoutOriginal.setVisibility(View.VISIBLE);

        for (View value : arrayView) {
            if (value == view) {
                value.setVisibility(View.VISIBLE);
            } else {
                value.setVisibility(View.GONE);
            }
        }

        for (TextView textView : arrayOne) {
            textView.setTextColor(getActivity().getResources().getColor(R.color.black));
        }

        for(int i = 0 ; i < arrayOne.length ; i++){
            arrayTwo[i].setTextColor(getActivity().getResources().getColor(R.color.black));
        }

        for(int i = 0 ; i < arrayOne.length ; i++){
            arrayThree[i].setTextColor(getActivity().getResources().getColor(R.color.black));
        }

        for(int i = 0 ; i < arrayOne.length ; i++){
            arrayReal[i].setTextColor(getActivity().getResources().getColor(R.color.white));
        }
    }



}