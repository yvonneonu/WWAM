package com.example.waam;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.List;

public class FriendAdapt extends RecyclerView.Adapter<FriendAdapt.FriendHolder> implements Filterable {
    private final List<WaamUser> friendsContainer;
    private final Context context;
    private final List<WaamUser> FullfriendModelList;
    private FriendAptListener friendAptListener;

    public FriendAdapt(List<WaamUser> friendsContainer, Context context) {
        this.friendsContainer = friendsContainer;
        FullfriendModelList = new ArrayList<>(friendsContainer);
        this.context = context;
    }

    @NonNull
    @Override
    public FriendHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.friendholderview,parent,false);
        return new FriendHolder(view,friendAptListener);
    }

    @Override
    public void onBindViewHolder(@NonNull FriendHolder holder, int position) {
        WaamUser friendModel = friendsContainer.get(position);
        holder.firstname.setText(friendModel.getFullname());
        holder.lastname.setText(friendModel.getFullname());
        Glide.with(context)
                .asBitmap()
                .circleCrop()
                .load(friendModel.getImageUrl())
                .into(holder.imageView);

    }



    public void friendMover(FriendAptListener friendAptListener){
        this.friendAptListener = friendAptListener;
    }

    @Override
    public int getItemCount() {
        return friendsContainer.size();
    }

    @Override
    public Filter getFilter() {
        return friendsFilter;
    }


    private final Filter friendsFilter = new Filter() {
        @Override
        protected FilterResults performFiltering(CharSequence constraint) {
            List<WaamUser> filteredFriendList = new ArrayList<>();

            if(constraint == null || constraint.length() == 0 ){
                filteredFriendList.addAll(FullfriendModelList);
            }else{
                String filteredPattern = constraint.toString().toLowerCase().trim();

                for(WaamUser friendModel : FullfriendModelList){
                    if(friendModel.getFullname().toLowerCase().contains(filteredPattern)
                            || friendModel.getFullname().toLowerCase().contains(filteredPattern)){
                        filteredFriendList.add(friendModel);
                    }
                }

            }

            FilterResults results = new FilterResults();
            results.values = filteredFriendList;
            return results;
        }

        @Override
        protected void publishResults(CharSequence constraint, FilterResults results) {
            friendsContainer.clear();
            friendsContainer.addAll((List)results.values);
            notifyDataSetChanged();
        }
    };

    public static class FriendHolder extends RecyclerView.ViewHolder {
        ImageView imageView;
        TextView firstname, lastname;
        public FriendHolder(@NonNull View itemView,FriendAptListener friendAptListener) {
            super(itemView);
            imageView = itemView.findViewById(R.id.imageView35);
            firstname = itemView.findViewById(R.id.textView84);
            lastname = itemView.findViewById(R.id.textView87);
            itemView.setOnClickListener(v -> {
                if(friendAptListener != null){
                    int position = getAdapterPosition();
                    if(position != RecyclerView.NO_POSITION){
                        friendAptListener.friendResponder(position);
                    }
                }
            });
        }
    }


    interface FriendAptListener{
        void friendResponder(int position);
    }
}
