package com.example.waam;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.List;

public class eventmodelAdapter extends RecyclerView.Adapter<eventmodelAdapter.ViewHolder> {
    List<eventdesignmodel> eventdesignmodels;
    Context context;

    public eventmodelAdapter(List<eventdesignmodel> eventdesignmodels, Context context){
        this.eventdesignmodels = eventdesignmodels;
        this.context = context;

    }
    @NonNull
    @Override
    public eventmodelAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.eventdesign, parent, false);
        return new  eventmodelAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull eventmodelAdapter.ViewHolder holder, int position) {
        eventdesignmodel eventdesignmodel = eventdesignmodels.get(position);
        Glide.with(context)
                .asBitmap()
                .load(eventdesignmodel.getImage())
                .into(holder.imageView);
        holder.descrip.setText(eventdesignmodel.getInfor());
        holder.amount.setText(eventdesignmodel.getTag());
        holder.price.setText(eventdesignmodel.getNewtage());
        holder.rate.setText(eventdesignmodel.getRate());
        holder.rate2.setText(eventdesignmodel.getNewrate());

    }

    @Override
    public int getItemCount() {
        return eventdesignmodels.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView imageView;
        TextView descrip, amount, price, rate, rate2;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.imageView30);
            descrip = itemView.findViewById(R.id.textView52);
            amount = itemView.findViewById(R.id.imageView37);
            price = itemView.findViewById(R.id.textView53);
            rate = itemView.findViewById(R.id.ratee);
            rate2 = itemView.findViewById(R.id.textView98);
        }
    }
}
