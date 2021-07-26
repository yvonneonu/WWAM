package com.example.waam;

import android.graphics.Color;
import android.os.Bundle;
import android.text.Spannable;
import android.text.SpannableStringBuilder;
import android.text.style.ForegroundColorSpan;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link NewAgentProfileFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class NewAgentProfileFragment extends Fragment implements View.OnClickListener {
    private ConstraintLayout constraintLayout;
    private ImageView imageVi, aboutsefl, friend;
    TextView textView;

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public NewAgentProfileFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment NewAgentProfileFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static NewAgentProfileFragment newInstance(String param1, String param2) {
        NewAgentProfileFragment fragment = new NewAgentProfileFragment();
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
        ((AppCompatActivity) getActivity()).getSupportActionBar().show();

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_new_agent_profile, container, false);
        view.findViewById(R.id.bookme).setOnClickListener(this);
        imageVi = view.findViewById(R.id.videopic);
        textView = view.findViewById(R.id.textdisplay);
        aboutsefl = view.findViewById(R.id.aboutsef);
        friend = view.findViewById(R.id.friends);


        imageVi.setOnClickListener(this);
        aboutsefl.setOnClickListener(this);
        friend.setOnClickListener(this);


        return view;
    }

    @Override
    public void onClick(View v) {
        final int vid = R.id.videopic;
        final int aboutse = R.id.aboutsef;
        final int frien = R.id.friends;

        switch (v.getId()){
            case R.id.bookme:
                AlertDialog.Builder alertDialog = new AlertDialog.Builder(getActivity());
                LayoutInflater factory = LayoutInflater.from(getActivity());
                final View view = factory.inflate(R.layout.agenttrquest, null);
                TextView text = view.findViewById(R.id.textView70);

                SpannableStringBuilder click = new SpannableStringBuilder("You must book this agent to access the \n chat feature! ");
                click.setSpan(new ForegroundColorSpan(Color.CYAN),40,45, Spannable.SPAN_INCLUSIVE_INCLUSIVE);

                text.setText(click);
                alertDialog.setView(view);

                Button button = view.findViewById(R.id.close);
                Button button1 = view.findViewById(R.id.button10);
                ImageView imageView = view.findViewById(R.id.cancell);

                imageView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        getActivity().finish();
                    }
                });

                button.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                     //   Intent intent = new Intent(getActivity(), )You must book this agent to access the \n chat here
                    }
                });

               /* alertDialog.setNegativeButton("Schließen", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog,int which) {
                        dialog.dismiss();
                    }
                });*/
                alertDialog.show();
                break;
            case vid:
                if (v.getId() == imageVi.getId()){
                    imageVi.setColorFilter(Color.BLUE);
                    aboutsefl.setColorFilter(Color.TRANSPARENT);
                    friend.setColorFilter(Color.TRANSPARENT);
                     textView.setText("You do not have any imgae");

                }else {

                }
                break;
            case aboutse:
                if (v.getId() == aboutsefl.getId()){
                    aboutsefl.setColorFilter(Color.BLUE);
                    friend.setColorFilter(Color.TRANSPARENT);
                    imageVi.setColorFilter(Color.TRANSPARENT);
                    //textView.setText();
                    textView.setVisibility(View.GONE);

                    Fragment fragment = new MoreAgentFragment();
                    getChildFragmentManager().beginTransaction()
                            .replace(R.id.framee, fragment)
                            .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE)
                            .commit();

                }else {

                }
            case frien:
                if (v.getId() == friend.getId()){
                    friend.setColorFilter(Color.BLUE);
                    imageVi.setColorFilter(Color.TRANSPARENT);
                    aboutsefl.setColorFilter(Color.TRANSPARENT);

                    textView.setText("You do not have any images");
                }

        }

    }
}