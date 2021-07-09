package com.example.waam;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "Post_table")
public class Post {
    @PrimaryKey(autoGenerate = true)
    private int id;
    private final String text;
   // private long date;

    public Post(String text) {
        this.text = text;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public String getText() {
        return text;
    }

   //public long getDate(){
     //   return date;
    //}
}
