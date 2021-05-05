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
import com.google.firebase.ktx.Firebase;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class GeneralFactory {
    private static GeneralFactory generalFactory;
    private final List<EventModel> eventModelList;
    private final FirebaseAuth mAuth;
    private EventModel[] eventModelsArrays;
    private final List<Location> locationList;
    private static final String WAAMBASE = "waamuser_base";
    private List<FriendModel> friendModelList;
    private final FirebaseDatabase firebaseDatabase;
    private List<Chat> chatContainer;
    private List<Chat> chatList;
    private List<WaamUser> allWaamUsers;
    private List<AgentModel> agentModelList;
    private List<WaamUser> allFriends;
    private Context context;

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



    private GeneralFactory(Context context){
        eventModelList = new ArrayList<>();
        locationList = new ArrayList<>();
        firebaseDatabase = FirebaseDatabase.getInstance();
        friendModelList = new ArrayList<>();
        chatList = new ArrayList<>();
        agentModelList =  new ArrayList<>();
        this.context = context;
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
    public static GeneralFactory getGeneralFactory(Context context){
        if(generalFactory == null){
            generalFactory = new GeneralFactory(context);
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


    public void makeWaamUser(){

        for(int i = 0 ; i < frimage.length ; i++){
            friendModelList.add(new FriendModel(firstname[i],lastname[i],frimage[i]));
        }
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


    public void signUpForBase(final String email, final String password, final ProgressBar bar,  WaamUser waamUser){
        bar.setVisibility(View.VISIBLE);
        mAuth.createUserWithEmailAndPassword(email,password)
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            bar.setVisibility(View.GONE);
                            //This is where the uid of the user is set
                            waamUser.setUid(mAuth.getUid());
                            Toast.makeText(context, "You have signed up", Toast.LENGTH_LONG).show();
                            //This is where we set the values we want our users to have
                            String userId = mAuth.getCurrentUser().getUid();
                            DatabaseReference mDatebaseReference = FirebaseDatabase.getInstance().getReference(WAAMBASE);
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

    public void loginToFireBase(String email, String password,LoginRequest loginRequest){
        mAuth.signInWithEmailAndPassword(email, password)
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(context,e.getMessage(),Toast.LENGTH_LONG).show();

                    }
                })
                .addOnCompleteListener(task -> {
                    if(task.isSuccessful()){
                        loginUser(loginRequest);
                    }else{
                        Log.d("Login","Login was succesfull");
                    }

                });
    }


    public void addToFriend(WaamUser waamUser, String branch){
        DatabaseReference mDatabaseReference = firebaseDatabase.getReference(branch);
        String newsId = mDatabaseReference.push().getKey();
        mDatabaseReference.child(newsId).setValue(waamUser).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                if(task.isSuccessful()){
                    Toast.makeText(context,"Succesfully added",Toast.LENGTH_LONG).show();
                }
            }
        })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Log.d("Failure",e.getMessage());
                    }
                });

    }


    public void loadFriends(String branch,FetchFriends fetchFriends){
        allFriends = new ArrayList<>();
        if(branch != null){
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
                    Log.d("Allfriends",""+allFriends.size());

                }

                @Override
                public void onCancelled(@NonNull DatabaseError error) {

                    Log.d("Cancel",error.getMessage());
                }
            });
        }else{
            Toast.makeText(context,"Branch is null",Toast.LENGTH_SHORT)
                    .show();

        }

    }


    public void sendMessage(final String message, final String receiverId, final Context context){
        if(!message.equals("")){
            String sender = mAuth.getCurrentUser().getUid();
            DatabaseReference reference = firebaseDatabase.getReference("CHAT");
            String chatId = reference.push().getKey();
            Chat chat = new Chat(message,chatId,sender,receiverId);
            reference.child(chatId).setValue(chat)
                    .addOnFailureListener(e -> Toast.makeText(context,"Message cant be sent",Toast.LENGTH_LONG).show())
                    .addOnCompleteListener(task -> {
                        if(task.isSuccessful()){
                            Toast.makeText(context,"Message was sent",Toast.LENGTH_LONG).show();
                        }
                    });
        }else{
            Toast.makeText(context,"Message cant be sent",Toast.LENGTH_LONG).show();
        }

    }


    public void checkOnlineStatus(String status,WaamUser waamUser){
        String userId = mAuth.getCurrentUser().getUid();
        DatabaseReference database = FirebaseDatabase.getInstance().getReference(WAAMBASE).child(userId);
        waamUser.setOnlineStatus(status);
        database.setValue(waamUser);

    }

    public void logOut(Context context){
        mAuth.signOut();
        Intent intent = new Intent(context, Login.class);
        context.startActivity(intent);
    }




    public void loadSpecUser(String userdId, final SpecificUser userCallback){
        DatabaseReference databaseSpecUser = FirebaseDatabase.getInstance().getReference(WAAMBASE);
        databaseSpecUser.child(userdId).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                WaamUser user = snapshot.getValue(WaamUser.class);
                userCallback.loadSpecUse(user);
                Log.d("Userc",""+user);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });


    }


    public void loadMessages(final FireBaseMessages fireBaseMessages, final String receiverId,Context context){
        chatContainer = new ArrayList<>();
        if(FirebaseAuth.getInstance().getCurrentUser() != null){
            final String senderId = FirebaseAuth.getInstance().getCurrentUser().getUid();
            Log.d("SenderId",senderId);
            DatabaseReference databaseChats = FirebaseDatabase.getInstance().getReference("CHAT");
            databaseChats.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot snapshot) {
                    chatContainer.clear();
                    Log.d("FirebaseMessages","I am in ondatachanged");
                    for(DataSnapshot dataSnapshot : snapshot.getChildren()){
                        Chat chat = dataSnapshot.getValue(Chat.class);
                        Log.d("SenderId",chat.getSenderId()+"="+senderId);
                        Log.d("ReceiverId",chat.getReceiverId()+"="+receiverId);
                        if(chat.getSenderId().equals(senderId) && chat.getReceiverId().equals(receiverId)
                                || chat.getSenderId().equals(receiverId) && chat.getReceiverId().equals(senderId)){
                            Log.d("FirebaseMessages","I am in the loop and passed the conditions");
                            chatContainer.add(chat);
                        }
                    }
                    Log.d("FirebaseMessages",""+chatContainer.size());
                    fireBaseMessages.firebaseMessages(chatContainer);
                }

                @Override
                public void onCancelled(@NonNull DatabaseError error) {

                }
            });
        }else{
            Toast.makeText(context,"Not logged in",Toast.LENGTH_SHORT)
                    .show();
            return;
        }

    }

    public void loginUser(LoginRequest loginRequest){

        Call<LoginResponse> loginResponseCall = ApiClient.getService().loginUser(loginRequest);

        loginResponseCall.enqueue(new Callback<LoginResponse>() {
            @Override
            public void onResponse(Call<LoginResponse> call, Response<LoginResponse> response) {

                if (response.isSuccessful()){
                    String loginToken = response.body().getToken();
                    //SessionManager.getSessionManager(Login.this).setTOKEN(loginToken);
                    Intent intent = new Intent(context, DiscoverDrawerLayerout.class);
                    Log.d("LoginTOken",loginToken);
                    //  Intent intent = new Intent(Login.this, Profile.class);
                    //Intent intent = new Intent(Login.this, DrawelayoutActivity.class);
                    //  Intent intent = new Intent(Login.this,finalProfile.class);
                    intent.putExtra("toking",loginToken);
                    context.startActivity(intent);
                    //startActivity(new Intent(Login.this, MainActivity.class).putExtra("name", loginResponse));

                }else {
                    String message = "An error occured please try again";
                    Toast.makeText(context,message,Toast.LENGTH_LONG).show();
                    Toast.makeText(context,"Email or Password mismatch!", Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onFailure(Call<LoginResponse> call, Throwable t) {

                //String message = t.getLocalizedMessage();
                Toast.makeText(context, t.getMessage(),Toast.LENGTH_LONG).show();

            }
        });
    }

    public void fetchAllUser(FetchFriends fetchAllWaamUsers){
        allWaamUsers = new ArrayList<>();
        DatabaseReference mDatebaseReference = firebaseDatabase.getReference(WAAMBASE);
        mDatebaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                allWaamUsers.clear();
                for(DataSnapshot dataSnapshot : snapshot.getChildren()){
                    WaamUser user = dataSnapshot.getValue(WaamUser.class);
                    if(!user.getUid().equals(mAuth.getUid())){
                        allWaamUsers.add(user);
                        Gson gson = new Gson();
                        Log.d("WaamUser",gson.toJson(gson));
                    }
                }
                fetchAllWaamUsers.friendsFetcher(allWaamUsers);
                Log.d("AllUsers",""+allWaamUsers.size());
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }


    public interface FetchFriends{
        void friendsFetcher(List<WaamUser> friends);
    }

    public interface FireBaseCallbackUser {
        void fireBaseUser(WaamUser basuser);
    }

    public interface SpecificUser{
        void loadSpecUse(WaamUser user);
    }


    public interface FireBaseMessages{
        void firebaseMessages(List<Chat> chatCont);
    }
}
