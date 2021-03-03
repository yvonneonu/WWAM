package com.example.waam;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.Toast;
import android.widget.Toolbar;

import com.google.android.material.card.MaterialCardView;

import java.util.Objects;

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
    private LinearLayout linearLayoutOne, linearLayoutTwo, linearLayoutThree, linearLayoutFour, linearLayoutFive;
    private RadioButton radioButtonone;
    private RadioButton radioButtontwo;
    private RadioButton radioButtonthree;
    //private MenuItem menuItem;

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;


    public BecomeAMemberFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment BecomeAMemberFragment.
     */
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

        linearLayoutOne = view.findViewById(R.id.layone);
        linearLayoutTwo = view.findViewById(R.id.laytwo);
        linearLayoutThree = view.findViewById(R.id.laythree);
        linearLayoutFour = view.findViewById(R.id.layfour);
        linearLayoutFive = view.findViewById(R.id.purchase);


        radioButtonone = view.findViewById(R.id.radioButtonone);
        radioButtontwo = view.findViewById(R.id.radioButtontwo);
        radioButtonthree = view.findViewById(R.id.radioButtonthree);

        linearLayoutOne.setOnClickListener(this);
        linearLayoutTwo.setOnClickListener(this);
        linearLayoutThree.setOnClickListener(this);
        linearLayoutFour.setOnClickListener(this);
        linearLayoutFive.setOnClickListener(this);


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
        final int radioOne = R.id.radioButtonone;
        final int radioTwo = R.id.radioButtontwo;
        final int radioThree = R.id.radioButtonthree;
        final int purchase = R.id.purchase;


        switch (v.getId()){

            case layoutOne:
                setClicked(linearLayoutTwo,linearLayoutThree,linearLayoutFour,linearLayoutOne);
                break;

            case layoutTwo:
                setClicked(linearLayoutOne,linearLayoutThree,linearLayoutFour,linearLayoutTwo);
                break;

            case layoutThree:
                setClicked(linearLayoutOne,linearLayoutTwo,linearLayoutFour,linearLayoutThree);
                break;


            case layoutFour:
                setClicked(linearLayoutOne,linearLayoutTwo,linearLayoutThree,linearLayoutFour);
                break;

            case radioOne:
                manageCheckedRadio(radioButtonthree,radioButtontwo,radioButtonone);
                break;

            case radioTwo:
                manageCheckedRadio(radioButtonone,radioButtonthree,radioButtontwo);
                break;

            case radioThree:
                manageCheckedRadio(radioButtonone,radioButtontwo,radioButtonthree);
                break;

            case purchase:
                Intent intent = new Intent(getActivity(),PaymentPage.class);
                startActivity(intent);
        }
    }


    private void manageCheckedRadio(RadioButton radFirst, RadioButton radSec, RadioButton radReal){
        if(radFirst.isChecked()){
            radFirst.setChecked(false);
        }

        if(radSec.isChecked()){
            radSec.setChecked(false);
        }
        radReal.setChecked(false);

    }


    private void setClicked(LinearLayout first, LinearLayout second, LinearLayout third,LinearLayout original){
        first.setBackgroundResource(R.drawable.price_box_bg);
        second.setBackgroundResource(R.drawable.price_box_bg);
        third.setBackgroundResource(R.drawable.price_box_bg);
        original.setBackgroundResource(R.drawable.price_box_bg_full);
    }
}