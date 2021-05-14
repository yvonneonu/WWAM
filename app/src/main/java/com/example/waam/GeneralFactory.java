package com.example.waam;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.gson.Gson;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

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
    private final FirebaseDatabase firebaseDatabase;
    private List<Chat> chatContainer;
    private List<WaamUser> allWaamUsers;
    private final List<AgentModel> agentModelList;
    private List<WaamUser> allFriends;
    private final Context context;
    private ValueEventListener valueEventListener;
    private DatabaseReference userForSeen;
    private ArrayList<WaamUser> contactedUser;
    private List<String> usersStringId;
    private String theMessage;

    private final int[] images = new int[]{R.drawable.eventcardimg,
            R.drawable.event_img,
            R.drawable.city_img,
            R.drawable.discover_featured_img,
            R.drawable.event_img,
            R.drawable.city_img,
            R.drawable.discover_featured_img,
            R.drawable.event_img
    };




    private GeneralFactory(Context context){
        eventModelList = new ArrayList<>();
        locationList = new ArrayList<>();
        firebaseDatabase = FirebaseDatabase.getInstance();
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
                .addOnCompleteListener(task -> {
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
                });
    }

    public void loginToFireBase(String email, String password,LoginRequest loginRequest){
        mAuth.signInWithEmailAndPassword(email, password)
                .addOnFailureListener(e -> Toast.makeText(context,e.getMessage(),Toast.LENGTH_LONG).show())
                .addOnCompleteListener(task -> {
                    if(task.isSuccessful()){
                        loginUser(loginRequest);
                    }else{
                        Log.d("Login","Login was succesfull");
                    }

                });
    }


    public void loadNewFriends(String branch,ProgressBar bar,TextView textView, FetchFriends friends){
        List<WaamUser> newFriends = new ArrayList<>();
        if(branch != null){
            DatabaseReference mDatebaseReference = firebaseDatabase.getReference(branch);
            mDatebaseReference.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot snapshot) {
                    newFriends.clear();
                    for(DataSnapshot dataSnapshot : snapshot.getChildren()){
                        FriendAlgo friendAlgo = dataSnapshot.getValue(FriendAlgo.class);
                        Long dateNow = System.currentTimeMillis();
                        assert friendAlgo != null;
                        if((dateNow - friendAlgo.getDateAdded()) <= 30 ){
                            WaamUser waamUser = friendAlgo.getWaamUser();
                            newFriends.add(waamUser);
                        }
                    }

                    friends.friendsFetcher(newFriends);
                }

                @Override
                public void onCancelled(@NonNull DatabaseError error) {
                    bar.setVisibility(View.GONE);
                    textView.setText(error.getMessage());
                }
            });
        }

    }

    public void addToFriend(WaamUser waamUser, String branch){
        DatabaseReference mDatabaseReference = firebaseDatabase.getReference(branch);
        String newsId = mDatabaseReference.push().getKey();
        Long dateAdded = System.currentTimeMillis();
        FriendAlgo friendAlgo = new FriendAlgo();
        friendAlgo.setDateAdded(dateAdded);
        friendAlgo.setWaamUser(waamUser);

        loadFriendForCheck(branch, new FriendChecker() {
            @Override
            public void friendCheck(List<WaamUser> userList) {
                for(WaamUser user : userList){
                    if(user.getUid().equals(waamUser.getUid())){
                        Toast.makeText(context,"You are already friends with"+user.getFullname(),Toast.LENGTH_SHORT)
                                .show();
                        return;
                    }
                }
                assert newsId != null;
                mDatabaseReference.child(newsId).setValue(friendAlgo).addOnCompleteListener(task -> {
                    if(task.isSuccessful()){
                        Toast.makeText(context,"Succesfully added",Toast.LENGTH_LONG).show();
                    }
                })
                        .addOnFailureListener(e -> Log.d("Failure",e.getMessage()));
            }
        });



    }

    public void loadFriendForCheck(String branch,FriendChecker fetchFriends){
        allFriends = new ArrayList<>();
        if(branch != null){
            DatabaseReference mDatebaseReference = firebaseDatabase.getReference(branch);
            mDatebaseReference.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot snapshot) {
                    allFriends.clear();
                    for(DataSnapshot dataSnapshot : snapshot.getChildren()){
                        FriendAlgo friendAlgo = dataSnapshot.getValue(FriendAlgo.class);
                        assert friendAlgo != null;
                        WaamUser user = friendAlgo.getWaamUser();
                        allFriends.add(user);
                    }

                    fetchFriends.friendCheck(allFriends);
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

    public void loadFriends(String branch,FetchFriends fetchFriends){
        allFriends = new ArrayList<>();
        if(branch != null){
            DatabaseReference mDatebaseReference = firebaseDatabase.getReference(branch);
            mDatebaseReference.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot snapshot) {
                    allFriends.clear();
                    for(DataSnapshot dataSnapshot : snapshot.getChildren()){
                        FriendAlgo friendAlgo = dataSnapshot.getValue(FriendAlgo.class);
                        assert friendAlgo != null;
                        WaamUser user = friendAlgo.getWaamUser();
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

            String sender = Objects.requireNonNull(mAuth.getCurrentUser()).getUid();
            String timeStamp = String.valueOf(System.currentTimeMillis());
            DatabaseReference reference = firebaseDatabase.getReference("CHAT");
            String chatId = reference.push().getKey();
            Chat chat = new Chat(message,chatId,sender,receiverId);
            chat.setTimeStamp(timeStamp);
            chat.setIsSeen(false);
            assert chatId != null;
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


    public void setOnlineStatus(String status){
        String userId = mAuth.getCurrentUser().getUid();
        DatabaseReference database = FirebaseDatabase.getInstance().getReference(WAAMBASE).child(userId);
        HashMap<String, Object> hashMap = new HashMap<>();
        hashMap.put("onlineStatus",status);
        database.updateChildren(hashMap);
    }


    public void setTimeStamp(){
        String userId = mAuth.getCurrentUser().getUid();
        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
        Date date = new Date();
        String dateToString = formatter.format(date);
        DatabaseReference database = FirebaseDatabase.getInstance().getReference(WAAMBASE).child(userId);
        HashMap<String, Object> hashMap = new HashMap<>();
        hashMap.put("timeStamp",dateToString);
        database.updateChildren(hashMap);
    }

    public void seenMessage(String senderId, String receiverId){
        userForSeen = firebaseDatabase.getReference("CHAT");
        valueEventListener = userForSeen.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for(DataSnapshot ds : snapshot.getChildren()){
                    Chat chat = ds.getValue(Chat.class);
                    if(chat.getReceiverId().equals(senderId) && chat.getSenderId().equals(receiverId)){
                        chat.setIsSeen(true);
                        Map<String,Object> map = new HashMap<>();
                        map.put("isSeen",true);
                        userForSeen.child(chat.getChatId()).updateChildren(map);
                    }
                }
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


    public void loadLastMessage(String senderId, String receiverId,TextView last_msg){
        theMessage = "default";
        DatabaseReference databaseChats = FirebaseDatabase.getInstance().getReference("CHAT");
        databaseChats.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for(DataSnapshot dataSnapshot : snapshot.getChildren()){
                    Chat chat = dataSnapshot.getValue(Chat.class);
                    if(chat != null){
                        if(chat.getSenderId().equals(senderId) && chat.getReceiverId().equals(receiverId)
                                || chat.getSenderId().equals(receiverId) && chat.getReceiverId().equals(senderId)){
                           //This keeps loading the message and keeps changing it till the last message
                            theMessage = chat.getMessage();
                            last_msg.setText(theMessage);
                        }
                    }


                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
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



    public void loadContact(ProgressBar bar,TextView textView,FetchFriends fetchContacts){
        contactedUser = new ArrayList<>();
        usersStringId = new ArrayList<>();
        String userId = FirebaseAuth.getInstance().getUid();
        DatabaseReference databaseReference = firebaseDatabase.getReference("CHAT");

        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                usersStringId.clear();
                for(DataSnapshot data : snapshot.getChildren()){
                    Chat chat = data.getValue(Chat.class);
                    if(chat.getReceiverId().equals(userId)){
                        usersStringId.add(chat.getSenderId());
                    }

                    if(chat.getSenderId().equals(userId)){
                        usersStringId.add(chat.getReceiverId());
                    }

                }

                DatabaseReference usersBaseReference = firebaseDatabase.getReference(WAAMBASE);
                usersBaseReference.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        contactedUser.clear();
                        for(DataSnapshot dataSnapshot : snapshot.getChildren()){
                            WaamUser user = dataSnapshot.getValue(WaamUser.class);
                            for(String id : usersStringId){
                                if(user.getUid().equals(id)){
                                    if(contactedUser.size() > 0){
                                        for(int i = 0 ; i < contactedUser.size() ; i++){
                                            String useroneid = contactedUser.get(i).getUid();
                                            if(!user.getUid().equals(useroneid) ){
                                                contactedUser.add(user);
                                            }
                                        }
                                    }else{
                                        Log.d("Useme",user.getUid());
                                        contactedUser.add(user);
                                    }
                                }
                            }
                        }


                        fetchContacts.friendsFetcher(contactedUser);
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {
                        bar.setVisibility(View.GONE);
                        textView.setVisibility(View.VISIBLE);
                        textView.setText(error.getMessage());

                    }
                });

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }


    public void checkTypingStatus(String receiverId){
        String myId = mAuth.getUid();
        DatabaseReference mDatebaseReference = firebaseDatabase.getReference(WAAMBASE).child(myId);
        HashMap<String,Object> hashMap = new HashMap<>();
        hashMap.put("typingTo",receiverId);
        mDatebaseReference.updateChildren(hashMap);
    }




    public interface FetchFriends{
        void friendsFetcher(List<WaamUser> friends);
    }


    public interface SpecificUser{
        void loadSpecUse(WaamUser user);
    }


    public interface FireBaseMessages{
        void firebaseMessages(List<Chat> chatCont);
    }

    public interface FriendChecker{
        void friendCheck(List<WaamUser> userList);
    }
}
