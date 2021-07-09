package com.example.waam;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.waam.utils.TextPostFragment;

import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link TextdisplayFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class TextdisplayFragment extends Fragment {
    private TextView mediaPost, textPost, textDisplay;
    ConstraintLayout showText;

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    private PostAdapter postAdapter;
    private PostViewModel postViewModel;
    private TextView textView;
    private RecyclerView recyclerView;

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

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View view = inflater.inflate(R.layout.fragment_textdisplay, container, false);

        textView = view.findViewById(R.id.nopost);

        mediaPost = view.findViewById(R.id.mediaPost);
        textPost = view.findViewById(R.id.textPost);
        textDisplay = view.findViewById(R.id.addText);
        showText = view.findViewById(R.id.showText);
        postAdapter = new PostAdapter();

        recyclerView = view.findViewById(R.id.postShow);

        postViewModel = new ViewModelProvider.AndroidViewModelFactory(getActivity().getApplication()).create(PostViewModel.class);
        postViewModel.getAllPosts().observe(getViewLifecycleOwner(), new Observer<List<Post>>() {
            @Override
            public void onChanged(List<Post> posts) {
                //update recycler view for the post
                if(posts.size() < 1){
                    Toast.makeText(getActivity(),"You have no posts",Toast.LENGTH_LONG).show();
                    recyclerView.setVisibility(View.GONE);
                    textView.setVisibility(View.VISIBLE);
                    textView.setText("You have no posts");
                }else{
                    postAdapter.setPost(posts);
                    recyclerView.setVisibility(View.VISIBLE);
                    textView.setVisibility(View.GONE);
                    GridLayoutManager gridLayoutManager= new GridLayoutManager(getActivity(),2);
                    recyclerView.setAdapter(postAdapter);
                    recyclerView.setLayoutManager(gridLayoutManager);
                }
                Toast.makeText(getActivity(),"Method triggered",Toast.LENGTH_LONG).show();
            }
        });
        //if statemnt needs to be here for the text just like wat we did in event

        textPost.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (v.getId() == textPost.getId()){
                    textPost.setBackgroundColor(Color.BLUE);
                    mediaPost.setBackgroundResource(R.drawable.drawerborder);
                    Fragment fragment = new TextPostFragment();
                    FragmentTransaction fragmentTransaction = getChildFragmentManager().beginTransaction();
                    fragmentTransaction.replace(R.id.frame1, fragment);
                    fragmentTransaction.commit();
                }
            }
        });

        textDisplay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (v.getId() == textDisplay.getId()){
                    Intent intent = new Intent(getActivity(), ShareTot.class);
                    startActivity(intent);

                }
            }
        });
        return view;
    }

}