package com.example.waam;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class GeneralFactory {
    private static GeneralFactory generalFactory;
    private final List<EventModel> eventModelList;
    private EventModel[] eventModelsArrays;


    private GeneralFactory(){
        eventModelList = new ArrayList<>();
    }

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
}
