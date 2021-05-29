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

public class machModelAdapter extends RecyclerView.Adapter<machModelAdapter.ViewHolder> {
    List<matchModel> matchModelList;
    Context context;
    onMatchListener onMatchListener;

    public machModelAdapter(List<matchModel> matchModelList, Context context){
        this.matchModelList = matchModelList;
        this.context = context;

    }
    @NonNull
    @Override
    public machModelAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.macthdesign, parent, false);
        return new machModelAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull machModelAdapter.ViewHolder holder, int position) {
        matchModel model = matchModelList.get(position);
        Glide.with(context)
                .asBitmap()
                .circleCrop()
                .load(model.getImagematch())
                .into(holder.imageView);
        holder.textView.setText(model.getName1());
        holder.textView1.setText(model.getState());


    }

    @Override
    public int getItemCount() {
        return matchModelList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView imageView;
        TextView textView;
        TextView textView1;
        ConstraintLayout constraintLayout;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.imageView30);
            textView = itemView.findViewById(R.id.textView52);
            textView1 = itemView.findViewById(R.id.textView53);
            constraintLayout = itemView.findViewById(R.id.all1);

            constraintLayout.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (onMatchListener != null){
                        int position = getAdapterPosition();
                        if (position != RecyclerView.NO_POSITION){
                            onMatchListener.OnMatchClick(position);
                        }
                    }

                }
            });
        }
    }
    public interface onMatchListener{
        void OnMatchClick(int position);

    }
    public void MatchMethod(onMatchListener onMatchListener){
        this.onMatchListener = onMatchListener;

    }
}
