package com.example.waam;

import android.content.ContentResolver;
import android.content.ContextWrapper;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.webkit.MimeTypeMap;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;
import com.google.android.gms.tasks.Continuation;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.StorageTask;
import com.google.firebase.storage.UploadTask;

public class shareMedia extends AppCompatActivity {
    private ImageView imageView;
    private String image;
    private TextView goback;
    private Button button;
    private DatabaseReference mDatabaseRef;
    private StorageTask<UploadTask.TaskSnapshot> mUploads;
    private ProgressBar progressBar;
    private static final String PROFILEPIC = "profilePic";
    private static final String VIDEOPIC = "videoPic";
    public static  final String FROMMEDIA = "fromMedia";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_share_media);

        imageView = findViewById(R.id.textView12);
        goback = findViewById(R.id.go);
        button = findViewById(R.id.button19);
        String uri = getIntent().getStringExtra("image");
        progressBar = findViewById(R.id.progressBar6);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                uploadPicOrVid(getFileExtension(Uri.parse(uri)),Uri.parse(uri));



            }
        });


        Glide.with(shareMedia.this)
                .asBitmap()
                .load(Uri.parse(uri))
                .into(imageView);

        goback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });


    }

    private String getFileExtension(Uri uri) {
        // This was just a test
        ContextWrapper rapper = new ContextWrapper(this);
        ContentResolver resolver = rapper.getContentResolver();
        MimeTypeMap mime = MimeTypeMap.getSingleton();
        return mime.getExtensionFromMimeType(resolver.getType(uri));
    }

    public void uploadPicOrVid(String filetype, Uri uri) {
        String uid = FirebaseAuth.getInstance().getUid();
        StorageReference mStorageRef = FirebaseStorage.getInstance().getReference(VIDEOPIC).child(uid);
        mDatabaseRef = FirebaseDatabase.getInstance().getReference(VIDEOPIC).child(uid);
        progressBar.setVisibility(View.VISIBLE);

        if (uri != null) {
            final StorageReference fileref = mStorageRef.child(System.currentTimeMillis() + "." + filetype);
            VideoPicModel videoPicModel = new VideoPicModel();

            if (filetype.equals("jpg") || filetype.equals("jpeg") || filetype.equals("png")) {
                mUploads = fileref.putFile(uri)
                        .addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                            @Override
                            public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                                String uploadId = mDatabaseRef.push().getKey();
                                fileref.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                                    @Override
                                    public void onSuccess(Uri uri) {
                                        videoPicModel.setVideo(false);
                                        videoPicModel.setVideoPicUrl(uri.toString());
                                        mDatabaseRef.child(uploadId).setValue(videoPicModel);
                                        progressBar.setVisibility(View.GONE);
                                        //This might crash it;
                                        mUploads = null;

                                        Intent intent = new Intent(shareMedia.this,DiscoverDrawerLayerout.class);
                                        intent.putExtra("media",FROMMEDIA);
                                        startActivity(intent);
                                    }
                                });

                            }
                        })
                        .addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception e) {
                                progressBar.setVisibility(View.GONE);
                                Toast.makeText(shareMedia.this, e.toString(), Toast.LENGTH_LONG).show();
                                //This might crash it;
                                mUploads = null;
                            }
                        });

            } else {
                fileref.putFile(uri).continueWithTask((Continuation<UploadTask.TaskSnapshot, Task<Uri>>) task -> {
                    if (!task.isSuccessful()) {
                        throw task.getException();
                    }
                    return fileref.getDownloadUrl();
                })
                        .addOnCompleteListener(new OnCompleteListener<Uri>() {
                            @Override
                            public void onComplete(@NonNull Task<Uri> task) {
                                if (task.isSuccessful()) {
                                    Uri downloadUrl = task.getResult();
                                    String uploadId = mDatabaseRef.push().getKey();
                                    videoPicModel.setVideo(true);
                                    videoPicModel.setVideoPicUrl(downloadUrl.toString());
                                    mDatabaseRef.child(uploadId).setValue(videoPicModel);
                                    progressBar.setVisibility(View.GONE);
                                    //This might crash it;
                                    //Intent intent = new Intent(shareMedia.this,);
                                    //intent.putExtra("urivid",uri);
                                    //startActivity(intent);
                                    Intent intent = new Intent(shareMedia.this,DiscoverDrawerLayerout.class);
                                    intent.putExtra("media",FROMMEDIA);
                                    startActivity(intent);
                                }
                            }
                        })
                        .addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception e) {
                                progressBar.setVisibility(View.GONE);
                                //This might crash it;
                            }
                        });

            }


        } else {
            Log.d("CompleteProfile", "No image or video was selected");
        }

    }


    public void uploadPicOrVid(String filetype, Uri uri, Boolean video) {
        String uid = FirebaseAuth.getInstance().getUid();
        StorageReference mStorageRef = FirebaseStorage.getInstance().getReference(VIDEOPIC).child(uid);
        mDatabaseRef = FirebaseDatabase.getInstance().getReference(VIDEOPIC).child(uid);
        progressBar.setVisibility(View.VISIBLE);

        if (uri != null) {
            final StorageReference fileref = mStorageRef.child(System.currentTimeMillis() + "." + filetype);
            VideoPicModel videoPicModel = new VideoPicModel();

            if (filetype.equals("jpg") || filetype.equals("jpeg") || filetype.equals("png")) {
                mUploads = fileref.putFile(uri)
                        .addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                            @Override
                            public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                                String uploadId = mDatabaseRef.push().getKey();
                                fileref.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                                    @Override
                                    public void onSuccess(Uri uri) {
                                        videoPicModel.setVideo(false);
                                        videoPicModel.setVideoPicUrl(uri.toString());
                                        mDatabaseRef.child(uploadId).setValue(videoPicModel);
                                        progressBar.setVisibility(View.GONE);
                                        //This might crash it;
                                        mUploads = null;
                                        if (video ){

                                        }
                                    }
                                });

                            }
                        })
                        .addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception e) {
                                progressBar.setVisibility(View.GONE);
                                Toast.makeText(shareMedia.this, e.toString(), Toast.LENGTH_LONG).show();
                                //This might crash it;
                                mUploads = null;
                            }
                        });

            } else {
                fileref.putFile(uri).continueWithTask((Continuation<UploadTask.TaskSnapshot, Task<Uri>>) task -> {
                    if (!task.isSuccessful()) {
                        throw task.getException();
                    }
                    return fileref.getDownloadUrl();
                })
                        .addOnCompleteListener(new OnCompleteListener<Uri>() {
                            @Override
                            public void onComplete(@NonNull Task<Uri> task) {
                                if (task.isSuccessful()) {
                                    Uri downloadUrl = task.getResult();
                                    String uploadId = mDatabaseRef.push().getKey();
                                    videoPicModel.setVideo(true);
                                    videoPicModel.setVideoPicUrl(downloadUrl.toString());
                                    mDatabaseRef.child(uploadId).setValue(videoPicModel);
                                    progressBar.setVisibility(View.GONE);
                                    //This might crash it;
                                }
                            }
                        })
                        .addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception e) {
                                progressBar.setVisibility(View.GONE);
                                //This might crash it;
                            }
                        });

            }


        } else {
            Log.d("CompleteProfile", "No image or video was selected");
        }

    }

}