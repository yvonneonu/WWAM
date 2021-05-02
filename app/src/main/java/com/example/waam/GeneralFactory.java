package com.example.waam;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.annotation.NonNull;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class GeneralFactory {
    private static GeneralFactory generalFactory;
    private final List<EventModel> eventModelList;
    private final FirebaseAuth mAuth;
    private EventModel[] eventModelsArrays;
    private final List<Location> locationList;
    private static final String WAAMBASE = "waamuser_base";
    private List<FriendModel> friendModelList;
    private final FirebaseDatabase firebaseDatabase;
    private List<Chat> chatList;
    private List<AgentModel> agentModelList;
    private List<WaamUser> allFriends;

    private final int[] images = new int[]{R.drawable.eventcardimg,
            R.drawable.event_img,
            R.drawable.city_img,
            R.drawable.discover_featured_img,
            R.drawable.event_img,
            R.drawable.city_img,
            R.drawable.discover_featured_img,
            R.drawable.event_img};
    private final int[] frimage = new int[]{
            R.drawable.friend_profie,
            R.drawable.friend_profie_2,
            R.drawable.friend_profie_3,
            R.drawable.friend_profie_4,
            R.drawable.friend_profie_5,
            R.drawable.friend_profie_6,
    };

    private final String[] firstname = new String[]{
            "John",
            "Stephen",
            "Thomas",
            "Albert",
            "Charles",
            "Bamidele",
    };

    private final String[] lastname = new String[]{
            "Doe",
            "Hawkings",
            "Edison",
            "Einstein",
            "Darwin",
            "Omonayin",
    };

    private String[] messagesArray = new String[]{"RecyclerView, while powerful and capable","RecyclerView, while powerful and capable","As you may have noticed, RecyclerView"};
    private String senderId = "yvonne";
    private String receiverId = "bamidele";
    private String chatId = "";

    private GeneralFactory(){
        eventModelList = new ArrayList<>();
        locationList = new ArrayList<>();
        firebaseDatabase = FirebaseDatabase.getInstance();
        friendModelList = new ArrayList<>();
        chatList = new ArrayList<>();
        agentModelList =  new ArrayList<>();
        mAuth = FirebaseAuth.getInstance();
    }


    String[] locationNames = new String[]{"Las Vegas","Los Angeles","Minneapolis","Mississipi","Atlanta","Florida","Miami","Kansas"};


    int[] display = {
            R.drawable.agent_1_img,
            R.drawable.agent_2_img,
            R.drawable.agent_3_img,
            R.drawable.agent_4_img,
            R.drawable.agent_5_img,
            R.drawable.agent_6_img,
            R.drawable.agent_1_img
    };
    String[] name = {"Ebuka Obi", "Blessing Peter", "Brown White", "Alexander Helger", "Chris Paul", "Peter Mac", "LordBroke Saint"};

    String[] rating = {"3.5", "5.0", "4.2", "5.1", "4.1", "7.0", "5.8"};
    String[] rating2 = {"(101 Ratings)", "(109 Ratings)", "(115 Ratings)", "(209 Ratings)", "(159 Ratings)", "(100 Ratings)", "(119 Ratings)"

    };

    int[] display1 = {
            R.drawable.agent_6_img,
            R.drawable.agent_5_img,
            R.drawable.agent_4_img,
            R.drawable.agent_3_img,
            R.drawable.agent_2_img,
            R.drawable.agent_1_img,
            R.drawable.agent_6_img
    };
    String[] name1 = {"LordBroke Saint", "Brown White", "Ebuka Obi", "Blessing Peter", "Peter Mac", "Alexander Helger", "Chris Paul"};

    String[] rating1 = {"4.5", "3.0", "3.2", "4.1", "3.1", "6.0", "4.8"};
    String[] rating3 = {"(102 Ratings)", "(105 Ratings)", "(103 Ratings)", "(109 Ratings)", "(150 Ratings)", "(101 Ratings)", "(115 Ratings)"};
    public static GeneralFactory getGeneralFactory(){
        if(generalFactory == null){
            generalFactory = new GeneralFactory();
        }
        return generalFactory;
    }

    public void makeEvent(){
        EventModel eventModelone = new EventModel("As you may have noticed," +
                " RecyclerView, while powerful and capable",R.drawable.discover_card_img,3,799,750);
        EventModel eventModeltwo = new EventModel("As you may have noticed, RecyclerView," +
                " while powerful and capable",R.drawable.discover_card_img,2,809,750);
        EventModel eventModelthree = new EventModel("As you may have noticed, RecyclerView," +
                " while powerful and capable",R.drawable.trending_deal_img,4,879,750);
        EventModel eventModelfour = new EventModel("As you may have noticed, RecyclerView," +
                " while powerful and capable",R.drawable.trending_deal_img,1,799,750);
        eventModelsArrays = new EventModel[]{eventModelone,eventModeltwo,eventModelthree,eventModelfour};
    }

    public List<EventModel> getEventModelList(){
        eventModelList.clear();
        makeEvent();
        eventModelList.addAll(Arrays.asList(eventModelsArrays));
        return  eventModelList;
    }


    public void makeLocation(){
        for(int i = 0 ; i < images.length ; i++){
            locationList.add(new Location(images[i],locationNames[i]));
        }
    }

    public List<Location> getLocationList(){
        locationList.clear();
        makeLocation();
        return locationList;
    }


    public void makeFriends(){

        for(int i = 0 ; i < frimage.length ; i++){
            friendModelList.add(new FriendModel(firstname[i],lastname[i],frimage[i]));
        }
    }

    public List<FriendModel> getFriendModelList(){
        friendModelList.clear();
        makeFriends();
        return friendModelList;
    }

    private void makeMessage(){
        chatList = new ArrayList<>();
        for(int i = 0 ; i < messagesArray.length ; i++){
            Chat chat = new Chat(messagesArray[i],senderId,receiverId, chatId);
            chatList.add(chat);
        }
    }

    public List<Chat> getChatList(){
        makeMessage();
        return chatList;
    }

    public List<AgentModel> getAgentModelList() {
        agentModelList.clear();
        addAgent();
        return agentModelList;
    }

    private void addAgent() {
        for (int i = 0; i < display.length; i++){
            agentModelList.add(new AgentModel(display[i], name[i], rating[i], rating2[i]));
        }
    }


    public void signUp(final String email, final String password, final Context context, final ProgressBar bar,  WaamUser waamUser){
        bar.setVisibility(View.VISIBLE);
        mAuth.createUserWithEmailAndPassword(email,password)
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            bar.setVisibility(View.GONE);
                            Toast.makeText(context, "You have signed up", Toast.LENGTH_LONG).show();
                            //This is where we set the values we want our users to have
                            String userId = mAuth.getCurrentUser().getUid();
                            DatabaseReference mDatebaseReference = firebaseDatabase.getReference(WAAMBASE);
                            mDatebaseReference.child(userId)
                                    .setValue(waamUser)
                                    .addOnCompleteListener(new OnCompleteListener<Void>() {
                                        @Override
                                        public void onComplete(@NonNull Task<Void> task) {
                                            if(task.isSuccessful()){
                                                Log.d("Registeration","Registeration was succesful");
                                            }else{
                                                Log.d("Registeration","Registeration was not succesful");
                                            }
                                        }
                                    })
                                    .addOnFailureListener(e -> {
                                        Log.d("Registration",e.getMessage());
                                        Toast.makeText(context,"error"+e.getMessage(),Toast.LENGTH_LONG).show();
                                        bar.setVisibility(View.GONE);
                                    });
                        }
                    }
                });
    }

    public void loginToFireBase(String email, String password,Context context){
        mAuth.signInWithEmailAndPassword(email, password)
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(context,e.getMessage(),Toast.LENGTH_LONG).show();

                    }
                })
                .addOnCompleteListener(task -> Log.d("Login","Login was succesfull"));
    }


    public void addToFriend(String friendId, String branch){

    }


    public void loadFriends(String branch,FetchFriends fetchFriends){
        allFriends = new ArrayList<>();
        DatabaseReference mDatebaseReference = firebaseDatabase.getReference(branch);
        mDatebaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                allFriends.clear();
                for(DataSnapshot dataSnapshot : snapshot.getChildren()){
                    WaamUser user = dataSnapshot.getValue(WaamUser.class);
                    allFriends.add(user);
                }

                fetchFriends.friendsFetcher(allFriends);

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

    public void logOut(Context context){
        mAuth.signOut();
        Intent intent = new Intent(context, Login.class);
        context.startActivity(intent);
    }

    public interface FetchFriends{
        void friendsFetcher(List<WaamUser> friends);
    }
}
