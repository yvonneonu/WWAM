package com.example.waam;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import java.util.List;

public class PostViewModel extends AndroidViewModel {
    private final PostRepository postRepository;
    private final LiveData<List<Post>> allPosts;

    public PostViewModel(@NonNull Application application) {
        super(application);
        postRepository = new PostRepository(application);
        allPosts = postRepository.getAllPosts();
    }

    public void insert(Post post){
        postRepository.insertPost(post);
    }

    public void removePost(Post post){
        postRepository.deletePost(post);
    }

    public void updatePosting(Post post){
        postRepository.updatePost(post);
    }

    public LiveData<List<Post>> getAllPosts(){
        return allPosts;
    }
}
