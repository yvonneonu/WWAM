package com.example.waam;


import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

@Dao
public interface PostDao {
    @Insert
    void insert(Post post);

    @Update
    void update(Post post);

    @Delete
    void delete(Post post);


    @Query("DELETE FROM Post_table")
    void deleteAllPosts();

    //@Query("SELECT * FROM Post_table ORDER BY date DESC")
  //  LiveData<List<Post>> getAllNotes();

}
