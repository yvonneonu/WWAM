package com.example.waam;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.List;

public class CustomAdapter extends RecyclerView.Adapter<CustomAdapter.Viewholder> {
    Context context;
    List<itemModel> arrayList;
    OnCustomListener onCustomListener;

    public CustomAdapter(List<itemModel> arrayList, Context context) {
        this.arrayList = arrayList;
        this.context = context;

    }

    @NonNull
    @Override
    public CustomAdapter.Viewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.groupmessage, parent, false);

        return new Viewholder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CustomAdapter.Viewholder holder, int position) {

        Glide.with(context)
                .asBitmap()
                .circleCrop()
                .load(arrayList.get(position).getImage())
                //.centerCrop()
                .into(holder.image);
        Glide.with(context)
                .asBitmap()
                .circleCrop()
                .load(arrayList.get(position).getImage())
                //.centerCrop()
                .into(holder.download);
    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }


    public class Viewholder extends RecyclerView.ViewHolder {
        ImageView image, download;
        TextView UserTile, MessageChat, UserTime;
        ConstraintLayout constraintLayout;

        public Viewholder(@NonNull View itemView) {
            super(itemView);
            image = itemView.findViewById(R.id.image);
            download = itemView.findViewById(R.id.download);
            UserTile = itemView.findViewById(R.id.textView54);
            MessageChat = itemView.findViewById(R.id.messagehere);
            UserTile = itemView.findViewById(R.id.tv_ime);
            constraintLayout = itemView.findViewById(R.id.custom);

            constraintLayout.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (onCustomListener != null) {
                        int position = getAdapterPosition();
                        if (position != RecyclerView.NO_POSITION) {
                            onCustomListener.OnCustomClick(position);
                        }
                    }
                }
            });
        }
    }

    public interface OnCustomListener {
        void OnCustomClick(int positon);
    }

    public void CusomMethod(OnCustomListener onCustomListener) {
        this.onCustomListener = onCustomListener;
    }
}
