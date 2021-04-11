package com.example.waam;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link MessagesFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class MessagesFragment extends Fragment implements View.OnClickListener{
    private RecyclerView recyclerView;
    private RecyclerView recyclerView1;

    private FriendAdapter friendAdapter;
    private ChatAdapter chatAdapter;

    private List<ModelImages> imageList = new ArrayList<>();
    private List<ModelChat> chatList = new ArrayList<>();

    FrameLayout fragment;
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";


    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public MessagesFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment MessagesFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static MessagesFragment newInstance(String param1, String param2) {
        MessagesFragment fragment = new MessagesFragment();
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

        View view = inflater.inflate(R.layout.fragment_messages, container, false);
        AppCompatActivity activity = (AppCompatActivity) getActivity();
        addImagenText();
        addChatText();
        fragment = view.findViewById(R.id.frameLayout);
        recyclerView = view.findViewById(R.id.recyclerView2);
        recyclerView1 = view.findViewById(R.id.recyclerView4);

        friendAdapter  = new FriendAdapter(imageList,getActivity());
        chatAdapter = new ChatAdapter(chatList,getActivity());

        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false);
        LinearLayoutManager layoutManager1 = new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false);

        recyclerView.setAdapter(friendAdapter);
        recyclerView1.setAdapter(chatAdapter);

        recyclerView.setLayoutManager((layoutManager));
        recyclerView1.setLayoutManager(layoutManager1);


        assert activity != null;

        Objects.requireNonNull(activity.getSupportActionBar()).setTitle("Messages");
        return  view;

    }

    private void addChatText() {
        int[] display = {
                R.drawable.topnav_profile,
                R.drawable.top_scroll_profile_img,
                R.drawable.profile_img_user,
                R.drawable.group_img_2,
                R.drawable.topnav_profile,
                R.drawable.top_scroll_profile_img,
                R.drawable.profile_img_user

        };

        String[] dispChat = {"Ebuka Obi", "Blessing Obi", "Brown White", "Alexander White", "Chris Paul", "Peter Mac", "LordBroke huhge"

        };
        String[] message = {"Hey, how are you today?", "Hey, how are you today?", "Hey, how are you today?", "Hey, how are you today?", "Hey, how are you today?",
                "Hey, how are you today?", "Hey, how are you today?"

        };
        String[] time = {"1:30 PM", "2:00 PM", "12:30 PM", "3:30 PM", "9:30 AM", "12:30 PM", "10:30 PM"

        };
        for (int i = 0; i < display.length; i++){
           chatList.add(new ModelChat(display[i], dispChat[i], message[i], time[i]));

        }
    }

    private void addImagenText() {
        int[] image = {R.drawable.topnav_profile,
                R.drawable.top_scroll_profile_img,
                R.drawable.profile_img_user,
                R.drawable.group_img_2,
                R.drawable.topnav_profile,
                R.drawable.top_scroll_profile_img,
                R.drawable.profile_img_user
        };

        String[] name = {"Adrea", "Dorathy", "Kiyomi", "David", "Don", "Kira","Alicia"};

        for (int i = 0; i < image.length ; i++) {
            imageList.add(new ModelImages(image[i], name[i]));
        }

    }

    @Override
    public void onClick(View v) {

    }


}