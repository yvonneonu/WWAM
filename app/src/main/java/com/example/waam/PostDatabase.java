package com.example.waam;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

@Database(entities = Post.class,version = 1)
abstract public class PostDatabase extends RoomDatabase {

    private static PostDatabase getInstance;

    public abstract PostDao PostDao();

    public static synchronized PostDatabase getPostDatabaseInstance(Context context){
        if(getInstance == null){
            getInstance = Room.databaseBuilder(context.getApplicationContext(),PostDatabase.class,"post-database")
                    .fallbackToDestructiveMigration()
                    .build();
        }
        return getInstance;
    }
}
