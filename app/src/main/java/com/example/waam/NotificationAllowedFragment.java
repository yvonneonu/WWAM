package com.example.waam;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.connectycube.auth.session.ConnectycubeSettings;
import com.connectycube.chat.ConnectycubeRestChatService;
import com.connectycube.core.EntityCallback;
import com.connectycube.core.exception.ResponseException;
import com.google.firebase.auth.FirebaseAuth;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link NotificationAllowedFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class NotificationAllowedFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    private GeneralFactory generalFactory;
    private NotificationViewAdapter notificationViewAdapter;
    private RecyclerView recyclerView;
    private TextView textView;
    private ProgressBar bar;

    private List<NotificationActions> notificationActionsList;
    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public NotificationAllowedFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment NotificationAllowedFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static NotificationAllowedFragment newInstance(String param1, String param2) {
        NotificationAllowedFragment fragment = new NotificationAllowedFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {

        String dialogId =  "61160a812889a70012e4ed2d";
        boolean enabled = false;


        //ConnectycubeSettings.getInstance().setEnablePushNotification(true); // default is 'true'

        //boolean isEnabled = ConnectycubeSettings.getInstance().isEnablePushNotification();



        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }

        ConnectycubeRestChatService.updateDialogNotificationSending(dialogId, enabled).performAsync(new EntityCallback<Boolean>() {
            @Override
            public void onSuccess(Boolean result, Bundle params) {

                Log.d("PushNotif","Not allowed");
                //if result == false - push notifications was disabled, otherwise - enabled
            }

            @Override
            public void onError(ResponseException e) {
                Log.d("PushNotificationb",e.getMessage());

            }
        });


        notificationActionsList = new ArrayList<>();
        generalFactory = GeneralFactory.getGeneralFactory(getActivity());





        if(notificationViewAdapter != null){
            notificationViewAdapter.acceptOrReject(new NotificationViewAdapter.AcceptOrDeny() {
                @Override
                public void accept(int pos) {
                    String myId = FirebaseAuth.getInstance().getUid();
                    NotificationActions notificationActions = notificationActionsList.get(pos);
                    WaamUser user = notificationActions.getWaamUser();
                    generalFactory.addToFriend(user,myId);
                }

                @Override
                public void deny(int pos) {

                }
            });
        }

        generalFactory.loadNotificationList(new GeneralFactory.NotificationsListener() {
            @Override
            public void notification(List<NotificationActions> notificationActions) {
                notificationViewAdapter = new NotificationViewAdapter(notificationActions, getActivity());
                if(notificationActions.size() > 0){
                    notificationActionsList = notificationActions;
                    recyclerView.setVisibility(View.VISIBLE);
                    recyclerView.setAdapter(notificationViewAdapter);
                    recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
                    bar.setVisibility(View.GONE);
                    textView.setVisibility(View.GONE);
                }else{
                    bar.setVisibility(View.GONE);
                    recyclerView.setVisibility(View.GONE);
                    textView.setVisibility(View.VISIBLE);
                    textView.setText("You have no notifications");
                }

            }
        });

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_notification_allowed, container, false);
        recyclerView = view.findViewById(R.id.notrec);
        bar = view.findViewById(R.id.progressBar5);
        textView = view.findViewById(R.id.nonote);
        return view;
    }




}