package com.example.waam;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.waam.model.User;
import com.google.firebase.auth.FirebaseAuth;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**K
 * A simple {@link Fragment} subclass.
 * Use the {@link MessagesFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class MessagesFragment extends Fragment {
    private RecyclerView recyclerView;
    private RecyclerView recyclerView1;
    private RecyclerView recyclerView2;
    private LinearLayoutManager layoutManager;
    private LinearLayoutManager layoutManager1;
    private ProgressBar barone;
    private ProgressBar bartwo;

   //private String DEFAULT_SPAN_COUNT = "2";

    private FriendAdapter friendAdapter;
    //private ChatAdapter chatAdapter;
    private RecentChatsAdapt recentChatsAdapt;
    private List<WaamUser> waamUserList;
    private List<WaamUser> newFriends;
    private TextView textView;
    private TextView textViewNewFriends;

    //private List<ModelImages> imageList = new ArrayList<>();
    //private List<ModelChat> chatList = new ArrayList<>();
    private List<itemModel> arrayList = new ArrayList<>();

    FrameLayout fragment;
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";


    // TODO: Rename and change types of parameters


    public MessagesFragment() {
        // Required empty public constructor
    }

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
        setHasOptionsMenu(true);
        GeneralFactory generalFactory = GeneralFactory.getGeneralFactory(getActivity());
        String branchName = FirebaseAuth.getInstance().getUid()+"FRIENDS";
        User userReal = getActivity().getIntent().getParcelableExtra(VideoCallActivity.AGOREUSER);

        generalFactory.loadNewFriends(branchName, barone,textViewNewFriends, friends -> {
            newFriends = friends;
            friendAdapter  = new FriendAdapter(newFriends,getActivity());
            if(newFriends.size() != 0){
                recyclerView.setAdapter(friendAdapter);
                recyclerView.setLayoutManager((layoutManager));
                textViewNewFriends.setVisibility(View.GONE);
                barone.setVisibility(View.GONE);
            }else {
                recyclerView.setVisibility(View.GONE);
                barone.setVisibility(View.GONE);
                textViewNewFriends.setVisibility(View.VISIBLE);
                textViewNewFriends.setText("You have no new friends");
                //You have no new friends;
            }



            friendAdapter.onFriendMethod(position -> {
                WaamUser user = newFriends.get(position);
                Intent intent = new Intent(getActivity(), ChatMessage.class);
                intent.putExtra("",user);
                intent.putExtra(VideoCallActivity.AGOREUSER,userReal);
                Log.d("Here",user.getUid());
                startActivity(intent);
            });

        });

        generalFactory.loadContact(bartwo,textView,friends -> {
            waamUserList = friends;
            recentChatsAdapt = new RecentChatsAdapt(waamUserList,getActivity());
            int si = waamUserList.size();
            Log.d("Sizeooo",""+si);
            if(waamUserList.size() != 0){
                //if error should happen here it could be because of this views which are possibly null
                recyclerView1.setVisibility(View.VISIBLE);
                textView.setVisibility(View.GONE);
                bartwo.setVisibility(View.GONE);
                recyclerView1.setAdapter(recentChatsAdapt);
                recyclerView1.setLayoutManager(layoutManager1);
            }else{
                //No Recent Chat
                bartwo.setVisibility(View.GONE);
                textView.setVisibility(View.VISIBLE);
                recyclerView1.setVisibility(View.GONE);
                textView.setText(R.string.nochathistory);
            }

            recentChatsAdapt.chatMethod(position -> {
                Intent intent = new Intent(getActivity(), ChatMessage.class);
                intent.putExtra("agorauser",userReal);
                intent.putExtra(ChatMessage.NEW_FRIENDS,friends.get(position));
                startActivity(intent);
            });
        });

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View view = inflater.inflate(R.layout.fragment_messages, container, false);
        AppCompatActivity activity = (AppCompatActivity) getActivity();
        groupImage();
        fragment = view.findViewById(R.id.frameLayout);
        recyclerView = view.findViewById(R.id.recyclerView2);
        recyclerView1 = view.findViewById(R.id.recyclerView4);
        recyclerView2 = view.findViewById(R.id.recyclerView5);
        textView = view.findViewById(R.id.textView102);
        textViewNewFriends = view.findViewById(R.id.textView103);
        barone = view.findViewById(R.id.progressBarnewFriend);
        bartwo = view.findViewById(R.id.progressBarContact);

        CustomAdapter customAdapter = new CustomAdapter(arrayList, getActivity());
        layoutManager = new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false);
        layoutManager1 = new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false);
        LinearLayoutManager linearLayoutManager3 = new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false);
        recyclerView2.setAdapter(customAdapter);
        recyclerView2.setLayoutManager(linearLayoutManager3);

        customAdapter.CusomMethod(positon -> {
            Intent intent = new Intent(getActivity(), ChatMessage.class);
            startActivity(intent);
        });


        assert activity != null;

        Objects.requireNonNull(activity.getSupportActionBar()).setTitle("Messages");
        return  view;



    }

    private void groupImage() {
        int[] diip = { R.drawable.topnav_profile,
                R.drawable.top_scroll_profile_img,
                R.drawable.profile_img_user,

        };
        String[] GroupNme = {
                "Group Chat", "Group Chat", "Group Chat"

        };

        String[] MessageChat = {
                "Hey, how are you today?",
                "Hey, here we are here",
                "Hey lets all meet here"

        };
        String[] timeschat = {
                "12:30 PM", "1:45 PM", "9;20 PM"

        };

        for (int i = 0; i < diip.length; i++){
            arrayList.add(new itemModel(diip[i], GroupNme[i], MessageChat[i], timeschat[i]));
        }
    }





    @Override
    public void onCreateOptionsMenu(@NonNull Menu menu, @NonNull MenuInflater inflater) {

        super.onCreateOptionsMenu(menu, inflater);
        inflater.inflate(R.menu.searchforchat, menu);

    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
       int id = item.getItemId();
       if (id == R.id.Invite){
           Intent intent = new Intent(getActivity(), SearcMessage.class);
           startActivity(intent);
       }
        return super.onOptionsItemSelected(item);
    }

}