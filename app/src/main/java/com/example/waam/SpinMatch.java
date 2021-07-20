package com.example.waam;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;

import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link SpinMatch#newInstance} factory method to
 * create an instance of this fragment.
 */
public class SpinMatch extends Fragment {

    private ConstraintLayout saveit;
    private String token;
    private int count;
    private int count1;
    private int count2;
    private int count3;
    private int count4;
    private int count5;
    private int count6;
    private int count7;
    private int count8;
    private int count9;

    private String  spinn2, spinn, ret, spinehnic, spinfaith, spinPolitics, spinChildren, spinSmoke, spinDrink, spinsala;


    private int zero, first, second, third, fourth, five, six, seven, eight, night;


    private TextView textView, swipe, careerText, bod, ethnictext, faithtext, politictext, childrentext, smoketext, drinktext, salatext, namme;

    Spinner spinner, careerSpin, body, ethni, fait, polit, childre, smok, drink, sala;

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public SpinMatch() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment SpinMatch.
     */
    // TODO: Rename and change types and number of parameters
    public static SpinMatch newInstance(String param1, String param2) {
        SpinMatch fragment = new SpinMatch();
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
        token = SharedPref.getInstance(getActivity()).getStoredToken();

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_spin_match, container, false);

        count = 0;
        count1 = 0;
        saveit = view.findViewById(R.id.saveit);

        spinner = view.findViewById(R.id.one);
        bod = view.findViewById(R.id.textView2);
        careerSpin = view.findViewById(R.id.carer);
        body = view.findViewById(R.id.spinnN);
        ethni = view.findViewById(R.id.ethnic);
        fait = view.findViewById(R.id.faith);
        polit = view.findViewById(R.id.politic);
        childre = view.findViewById(R.id.children);
        smok = view.findViewById(R.id.smoke);
        drink = view.findViewById(R.id.drink);
        sala = view.findViewById(R.id.salary);



        textView = view.findViewById(R.id.textView);
        careerText = view.findViewById(R.id.textView26);
        ethnictext = view.findViewById(R.id.textView3);
        faithtext = view.findViewById(R.id.textView4);
        politictext = view.findViewById(R.id.textView5);
        childrentext = view.findViewById(R.id.textView6);
        smoketext = view.findViewById(R.id.textView7);
        drinktext = view.findViewById(R.id.textView8);
        salatext = view.findViewById(R.id.textView9);

        saveit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String message = "Saved Successfully";

                Toast.makeText(getActivity(), message, Toast.LENGTH_LONG).show();

            }
        });

        careerSpin.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                count++;
                Log.d("InCareer","Running");
                Log.d("InCareer",""+count);

                if (count > 1) careerText.setText(careerSpin.getSelectedItem().toString());
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                count1++;
                if(count1 > 1) textView.setText(spinner.getSelectedItem().toString());

                zero = 0;

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });


        body.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                count2++;
                if (count2> 1) bod.setText(body.getSelectedItem().toString());

                first = 0;

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });


        ethni.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                count3++;
                if (count3> 1)ethnictext.setText(ethni.getSelectedItem().toString());
                second = 0;

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        fait.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                count4++;
                if(count4> 1)faithtext.setText(fait.getSelectedItem().toString());
                third = 0;
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        polit.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                count5++;

                if(count5> 1)politictext.setText(polit.getSelectedItem().toString());
                fourth = 0;

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        childre.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                count6++;
                if(count6> 1)childrentext.setText(childre.getSelectedItem().toString());

                five = 0;
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        smok.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                count7++;
                if (count7> 1)smoketext.setText(smok.getSelectedItem().toString());
                six = 0;
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        drink.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                count8++;
                if (count8> 1)drinktext.setText(drink.getSelectedItem().toString());
                seven = 0;
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        sala.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                count9++;
                if (count9> 1)salatext.setText(sala.getSelectedItem().toString());
                eight = 0;
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });


        FetchSpinnerValues.getSpinnerValues().fetchOccupation(new FetchSpinnerValues.OccupationListener() {
            @Override
            public void onOccupationListener(List<String> userSchool) {
                ArrayAdapter<String> userSchollAdapter = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_spinner_item, userSchool);
                userSchollAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                careerSpin.setAdapter(userSchollAdapter);
                spinn2 = careerSpin.getSelectedItem().toString();
                //careerText.setText(spinn2);
                //educate.setText(spinn);
            }
        }, token);

        FetchSpinnerValues.getSpinnerValues().fetchEducation(new FetchSpinnerValues.EducationListener() {
            @Override
            public void onEducationListener(List<String> qualification) {
                ArrayAdapter<String> qualificationAdapter = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_spinner_item, qualification);
                qualificationAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                spinner.setAdapter(qualificationAdapter);
                spinn = spinner.getSelectedItem().toString();

            }
        },token);

        FetchSpinnerValues.getSpinnerValues().fetchBody(new FetchSpinnerValues.BodyTypeListener() {
            @Override
            public void onBodyTypeListener(List<String> userBody) {
                ArrayAdapter<String> userBodyAdapter = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_spinner_item, userBody);
                userBodyAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                body.setAdapter(userBodyAdapter);
                ret = body.getSelectedItem().toString();
            }
        }, token);

        FetchSpinnerValues.getSpinnerValues().fetchEthnicity(userEthnicity -> {
            ArrayAdapter<String> userEhtnicityAdapter = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_spinner_item, userEthnicity);
            userEhtnicityAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            ethni.setAdapter(userEhtnicityAdapter);
            spinehnic = ethni.getSelectedItem().toString();
        }, token);

        FetchSpinnerValues.getSpinnerValues().fetchFaith(userFaith ->  {
            ArrayAdapter<String> userFaithAdapter = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_spinner_item, userFaith);
            userFaithAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            fait.setAdapter(userFaithAdapter);
            spinfaith = fait.getSelectedItem().toString();
        }, token);

        FetchSpinnerValues.getSpinnerValues().fetchPolitics(userPolitics ->  {
            ArrayAdapter<String> userPoliticsAdapetr = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_spinner_item, userPolitics);
            userPoliticsAdapetr.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            polit.setAdapter(userPoliticsAdapetr);
            spinPolitics = polit.getSelectedItem().toString();
        }, token);

        FetchSpinnerValues.getSpinnerValues().fetchChildren(userChildren ->  {
            ArrayAdapter<String> userChildrenAdapetr = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_spinner_item, userChildren);
            userChildrenAdapetr.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            childre.setAdapter(userChildrenAdapetr);
            spinChildren = childre.getSelectedItem().toString();
        }, token);

        FetchSpinnerValues.getSpinnerValues().fetchSmoke(userSmoke ->  {
            ArrayAdapter<String> userSmokeAdapter = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_spinner_item, userSmoke);
            userSmokeAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            smok.setAdapter(userSmokeAdapter);
            spinSmoke = smok.getSelectedItem().toString();
        }, token);

        FetchSpinnerValues.getSpinnerValues().fetchDrink(userDrink ->  {
            ArrayAdapter<String> userDrinkAdapter = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_spinner_item, userDrink);
            userDrinkAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            drink.setAdapter(userDrinkAdapter);
            spinDrink = drink.getSelectedItem().toString();
        }, token);

        FetchSpinnerValues.getSpinnerValues().fetchSalay(userSalary ->  {
            ArrayAdapter<String> userSalaryAdapter = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_spinner_item, userSalary);
            userSalaryAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            sala.setAdapter(userSalaryAdapter);
            spinsala = sala.getSelectedItem().toString();
        }, token);
        return view;
    }
}