package com.example.waam;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link NotificationNotAllowedFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class NotificationNotAllowedFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public NotificationNotAllowedFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment NotificationNotAllowedFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static NotificationNotAllowedFragment newInstance(String param1, String param2) {
        NotificationNotAllowedFragment fragment = new NotificationNotAllowedFragment();
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
        return inflater.inflate(R.layout.fragment_notification_not_allowed, container, false);


    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        Button button = view.findViewById(R.id.button18);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Bundle bundle = new Bundle();

                bundle.putString("android.provider.extra.APP_PACKAGE", getContext().getPackageName());
             //   bundle.putString("android.provider.extra.APP_PACKAGE", getContext().getApplicationInfo().uid);

              Intent intent = new Intent(getActivity(), NotificationListener.class);


              intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                Log.d("because i no get money", ""+getContext().getPackageName());
                getActivity().startService(intent.putExtras(bundle));
                //getActivity().startService(new Intent(getActivity(), NotificationListener.class));


            }
        });
    }
}