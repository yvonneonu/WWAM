package com.example.waam;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link MessagesFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class MessagesFragment extends Fragment implements View.OnClickListener{
    private RecyclerView recyclerView;
    private FriendAdapter friendAdapter;
    private List<ModelImages> imageList = new ArrayList<>();

    FrameLayout fragment;
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";


    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public MessagesFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment MessagesFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static MessagesFragment newInstance(String param1, String param2) {
        MessagesFragment fragment = new MessagesFragment();
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

        View view = inflater.inflate(R.layout.fragment_messages, container, false);
        AppCompatActivity activity = (AppCompatActivity) getActivity();
        fragment = view.findViewById(R.id.frameLayout);
        recyclerView = view.findViewById(R.id.recyclerView2);
        friendAdapter  = new FriendAdapter();
        recyclerView.setAdapter(friendAdapter);
        addImagenText();
        //assert activity != null;

        Objects.requireNonNull(activity.getSupportActionBar()).setTitle("Messages");
        return  view;

    }

    private void addImagenText() {

        int[] image = {R.drawable.discovermatchesbox,
                R.drawable.travel,
                R.drawable.eventcardimg,
                R.drawable.diningout,
                R.drawable.nightclubsdancing,
                R.drawable.coffeeconversation,
                R.drawable.discovereventsbox
        };
        String[] name = {"Adrea", "Dorathy", "Kiyomi", "David", "Don", "Kira"

        };
        for (int i = 0; i < 6; i++) {
            imageList.add(new ModelImages(image[i], name[i]));
        }

    }

    @Override
    public void onClick(View v) {

    }

    private class FriendAdapter extends RecyclerView.Adapter<FriendAdapter.Viewholder>{
        @NonNull
        @Override
        public FriendAdapter.Viewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.newfriendslide, parent, false);
            return new FriendAdapter.Viewholder(view);

        }

        @Override
        public void onBindViewHolder(@NonNull FriendAdapter.Viewholder holder, int position) {

            ModelImages images = imageList.get(position);
            holder.imageView.setImageResource(images.getImage());
            holder.textView.setText(images.getName());
        }

        @Override
        public int getItemCount() {
            return imageList.size();
        }

        public class Viewholder extends RecyclerView.ViewHolder{
            ImageView imageView;
            TextView textView;


            public Viewholder(@NonNull View itemView) {
                super(itemView);
                imageView = itemView.findViewById(R.id.imageView27);
                textView = itemView.findViewById(R.id.namemessa);

            }
        }
    }
}