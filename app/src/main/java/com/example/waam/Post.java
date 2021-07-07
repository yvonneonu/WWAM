package com.example.waam;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.util.Date;

@Entity(tableName = "Post_table")
public class Post {
    @PrimaryKey(autoGenerate = true)
    private int id;
    private String text;
    private Date date;

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

   public Date getDate(){
        return date;
    }
}
