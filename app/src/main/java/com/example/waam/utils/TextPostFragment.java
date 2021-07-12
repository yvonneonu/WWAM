package com.example.waam.utils;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.waam.Post;
import com.example.waam.PostAdapter;
import com.example.waam.PostRepository;
import com.example.waam.PostViewModel;
import com.example.waam.R;

import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link TextPostFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class TextPostFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    private static final String DIALOG_DELETE = "DialogDelete";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private PostAdapter postAdapter;
    private TextView textView;
    private RecyclerView recyclerView;

    public TextPostFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment TextPostFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static TextPostFragment newInstance(String param1, String param2) {
        TextPostFragment fragment = new TextPostFragment();
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
        View view = inflater.inflate(R.layout.fragment_text_post, container, false);
        textView = view.findViewById(R.id.nopost);
        postAdapter = new PostAdapter();
        recyclerView = view.findViewById(R.id.postShow);

        PostViewModel postViewModel = new ViewModelProvider.AndroidViewModelFactory(getActivity().getApplication()).create(PostViewModel.class);
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


                postAdapter.deletePost(new PostAdapter.PostListener() {
                    @Override
                    public void onPostListener(int position) {
                        Post post = posts.get(position);
                        FragmentManager fragmentManager = getChildFragmentManager();
                        DeleteFragment deleteFragment = new DeleteFragment(post);
                        deleteFragment.show(fragmentManager,DIALOG_DELETE);

                    }
                });

            }
        });
        return view;
    }



    public static class DeleteFragment extends DialogFragment{

        private final Post post;
        public DeleteFragment(Post post){
            this.post = post;
        }
        @NonNull
        @Override
        public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
            return new AlertDialog.Builder(getActivity())
                    .setTitle(R.string.delete)
                    .setMessage("this item would be deleted")
                    .setPositiveButton(android.R.string.ok, new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            new PostViewModel(getActivity().getApplication())
                            .removePost(post);
                        }
                    })
                    .create();
        }
    }
}