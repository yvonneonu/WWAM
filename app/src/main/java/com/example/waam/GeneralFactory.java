package com.example.waam;

import android.content.Context;
import android.content.res.AssetManager;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class GeneralFactory {
    private static GeneralFactory generalFactory;
    private final List<EventModel> eventModelList;
    private EventModel[] eventModelsArrays;
    private final List<Location> locationList;
    private List<FriendModel> friendModelList;
    private List<Chat> chatList;

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
        friendModelList = new ArrayList<>();
        chatList = new ArrayList<>();
    }


    String[] locationNames = new String[]{"Las Vegas","Los Angeles","Minneapolis","Mississipi","Atlanta","Florida","Miami","Kansas"};
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

}
