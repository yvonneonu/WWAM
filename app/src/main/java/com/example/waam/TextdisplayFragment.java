package com.example.waam;

import android.annotation.SuppressLint;
import android.content.ContentResolver;
import android.content.ContextWrapper;
import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.MimeTypeMap;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.bumptech.glide.Glide;
import com.example.waam.utils.TextPostFragment;
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

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link TextdisplayFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class TextdisplayFragment extends Fragment {
    private TextView mediaPost, textPost, textDisplay, addImage, showcase;
    ConstraintLayout showText;
    private StorageTask<UploadTask.TaskSnapshot> mUploads;
    private Task<Uri> uriTask;
    private DatabaseReference mDatabaseRef;
    private ProgressBar progressBar;
    private static final String VIDEOPIC = "videoPic";





    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;


    public TextdisplayFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment TextdisplayFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static TextdisplayFragment newInstance(String param1, String param2) {
        TextdisplayFragment fragment = new TextdisplayFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @SuppressLint("SetTextI18n")
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View view = inflater.inflate(R.layout.fragment_textdisplay, container, false);

        mediaPost = view.findViewById(R.id.mediaPost);
        textPost = view.findViewById(R.id.textPost);
        textDisplay = view.findViewById(R.id.addText);
        showText = view.findViewById(R.id.showText);
        addImage = view.findViewById(R.id.textView121);
        showcase = view.findViewById(R.id.texpost);


        Fragment fragment = new TextPostFragment();
        textDisplay.setText("Add New Text Post");
        addImage.setText("My Text Post");

        showcase.setText("Share your thoughts");

        textPost.setBackgroundColor(Color.BLUE);
        mediaPost.setBackgroundResource(R.drawable.drawerborder);
        getChildFragmentManager().beginTransaction()
                .replace(R.id.textdiscontainer, fragment)
                .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE)
                .commit();



        //if statemnt needs to be here for the text just like wat we did in event

        mediaPost.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("SetTextI18n")
            @Override
            public void onClick(View v) {
                if (v.getId() == mediaPost.getId()){

                    mediaPost.setBackgroundColor(Color.BLUE);
                    textPost.setBackgroundResource(R.drawable.drawerborder);


                    textDisplay.setText("Upload New Image");
                    addImage.setText("My Media Post");

                    showcase.setText("Showcase who you are");

                    Fragment fragment = new MediaPostFragment();
                    getChildFragmentManager().beginTransaction()
                            .replace(R.id.textdiscontainer, fragment)
                            .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE)
                            .commit();
                }


            }
        });


        textPost.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (v.getId() == textPost.getId()){
                    textPost.setBackgroundColor(Color.BLUE);
                    mediaPost.setBackgroundResource(R.drawable.drawerborder);
                    textDisplay.setText("Add New Text Post");
                    addImage.setText("My Text Post");

                    showcase.setText("Share your thoughts");

                    Fragment fragment = new TextPostFragment();
                    FragmentTransaction fragmentTransaction = getChildFragmentManager().beginTransaction();
                    fragmentTransaction.replace(R.id.textdiscontainer, fragment);
                    fragmentTransaction.commit();
                }
            }
        });

        textDisplay.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                if (textDisplay.getText() == "Add New Text Post"){

                    Intent intent = new Intent(getActivity(), ShareTot.class);
                    startActivity(intent);
                }else {

                    BottomSheet bottom = new BottomSheet();
                    bottom.show(getChildFragmentManager(), "MED");
                    bottom.onSelectedImageListener(new BottomSheet.SelectedImage() {
                        @Override
                        public void selectedImageListener(Uri uri) {
                            if (mUploads != null || uriTask != null) {
                                Toast.makeText(getActivity(), "An upload is ongoing", Toast.LENGTH_LONG)
                                        .show();
                            } else {
                                uploadPicOrVid(getFileExtension(uri), uri);
                                Glide.with(getActivity())
                                        .asBitmap()
                                        .load(uri)
                                        .into(imagethird);
                                bottom.dismiss();
                            }

                        }
                    });

                }
            }
        });



       /* textDisplay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (v.getId() == textDisplay.getId()){
                    Intent intent = new Intent(getActivity(), Media.class);
                    startActivity(intent);
                }
            }
        });*/
        return view;
    }



    private String getFileExtension(Uri uri) {
        // This was just a test
        ContextWrapper rapper = new ContextWrapper(getActivity());
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

            if (filetype.equals("jpg") || filetype.equals("jpeg") || filetype.equals("png") ) {
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
                                    }
                                });

                            }
                        })
                        .addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception e) {
                                progressBar.setVisibility(View.GONE);
                                Toast.makeText(getActivity(), e.toString(), Toast.LENGTH_LONG).show();
                                //This might crash it;
                                mUploads = null;
                            }
                        });

            } else {
                uriTask = fileref.putFile(uri).continueWithTask((Continuation<UploadTask.TaskSnapshot, Task<Uri>>) task -> {
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
                                    uriTask = null;
                                }
                            }
                        })
                        .addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception e) {
                                progressBar.setVisibility(View.GONE);
                                //This might crash it;
                                uriTask = null;
                            }
                        });

            }


        } else {
            Log.d("CompleteProfile", "No image or video was selected");
        }

    }

}