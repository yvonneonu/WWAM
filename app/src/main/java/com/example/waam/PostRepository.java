package com.example.waam;

import android.app.Application;

import androidx.lifecycle.LiveData;

import java.util.List;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.core.Single;
import io.reactivex.rxjava3.core.SingleObserver;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.schedulers.Schedulers;

public class PostRepository {
    private final PostDao postDao;
    private LiveData<List<Post>> allPosts;
    public PostRepository(Application application){
        PostDatabase database = PostDatabase.getPostDatabaseInstance(application);
        postDao = database.PostDao();
        allPosts = postDao.getAllNotes();
    }


    public void updatePost(Post post){
        update(post).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new SingleObserver<LiveData<List<Post>>>() {
                    @Override
                    public void onSubscribe(@io.reactivex.rxjava3.annotations.NonNull Disposable d) {

                    }

                    @Override
                    public void onSuccess(@io.reactivex.rxjava3.annotations.NonNull LiveData<List<Post>> listLiveData) {
                        allPosts = listLiveData;
                    }

                    @Override
                    public void onError(@io.reactivex.rxjava3.annotations.NonNull Throwable e) {

                    }
                });
    }


    private Single<LiveData<List<Post>>> update(Post post) {
        return Single.fromCallable(() -> {
            postDao.update(post);
            return allPosts;
        });

    }
    public void deleteAllPosts(Post post){

        deletePosts(post).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new SingleObserver<LiveData<List<Post>>>() {
                    @Override
                    public void onSubscribe(@io.reactivex.rxjava3.annotations.NonNull Disposable d) {

                    }

                    @Override
                    public void onSuccess(@io.reactivex.rxjava3.annotations.NonNull LiveData<List<Post>> listLiveData) {
                        allPosts = listLiveData;
                    }

                    @Override
                    public void onError(@io.reactivex.rxjava3.annotations.NonNull Throwable e) {

                    }
                });
    }




    private Single<LiveData<List<Post>>> deletePosts(Post post) {
        return Single.fromCallable(() -> {
            postDao.delete(post);
            return allPosts;
        });

    }

    public LiveData<List<Post>> getAllPosts(){
        return allPosts;
    }

    public void insertPost(Post post){
        addPosts(post).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new SingleObserver<LiveData<List<Post>>>() {
                    @Override
                    public void onSubscribe(@io.reactivex.rxjava3.annotations.NonNull Disposable d) {

                    }

                    @Override
                    public void onSuccess(@io.reactivex.rxjava3.annotations.NonNull LiveData<List<Post>> listLiveData) {
                        allPosts = listLiveData;

                    }

                    @Override
                    public void onError(@io.reactivex.rxjava3.annotations.NonNull Throwable e) {

                    }
                });
    }

    //The task we want to add is being added as a parameter to the Single addtask
    public Single<LiveData<List<Post>>> addPosts(final Post post){
        return Single.fromCallable(() -> {
            postDao.insert(post);
            return allPosts;
        });

    }
}
