package com.example.waam;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class AgentAdapter extends RecyclerView.Adapter<AgentAdapter.Viewholder> {
    List<AgentModel> agentModelList;
    Context context;
    OnAgentListener onAgentListener;
   // private Object OnAgentListener;
//    Viewholder.OnAgentListener onAgentListener;
   // OnAgentListener onAgentListener;




    public AgentAdapter(List<AgentModel> agentModelList, Context context){
        this.agentModelList = agentModelList;
        this.context = context;

    }

    @NonNull
    @Override
    public AgentAdapter.Viewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.agentbodydesign, parent, false);
        return new Viewholder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull AgentAdapter.Viewholder holder, int position) {
        AgentModel agentModel = agentModelList.get(position);
        holder.firstAgent.setImageResource(agentModel.getImage());
        holder.secondAgent.setImageResource(agentModel.getImage1());
        holder.firstName.setText(agentModel.getName());
        holder.secondName.setText(agentModel.getName1());
        holder.firstRate.setText(agentModel.getRating());
        holder.secondRate.setText(agentModel.getRating2());
        holder.number1Rate.setText(agentModel.getRating1());
        holder.number2Rate.setText(agentModel.getRating3());

    }

    @Override
    public int getItemCount() {
        return agentModelList.size();
    }

    public class Viewholder extends RecyclerView.ViewHolder {
        ImageView firstAgent, secondAgent;
        TextView firstName, secondName, firstRate, secondRate, number1Rate, number2Rate;
       // private Object OnAgentListener;
        //private Object OnAgentListener;
        CardView cardView, cardView1;
       // CardView ;


        public Viewholder(@NonNull View itemView) {
            super(itemView);
            firstAgent = itemView.findViewById(R.id.agentimage);
            secondAgent = itemView.findViewById(R.id.secondImage);
            firstName = itemView.findViewById(R.id.agentName);
            secondName = itemView.findViewById(R.id.secondAg);
            firstRate = itemView.findViewById(R.id.rateone);
            secondRate = itemView.findViewById(R.id.ratetwo);
            number1Rate = itemView.findViewById(R.id.numb);
            number2Rate = itemView.findViewById(R.id.numb2);
            cardView = itemView.findViewById(R.id.card1);
            cardView1 = itemView.findViewById(R.id.cardView10);

            cardView1.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (onAgentListener != null){
                        int position = getAdapterPosition();
                        if (position != RecyclerView.NO_POSITION){
                            onAgentListener.onAgentCick(position);
                        }
                    }
                }
            });

            cardView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (onAgentListener != null){
                        int position = getAdapterPosition();
                        if (position != RecyclerView.NO_POSITION){
                            onAgentListener.onAgentCick(position);
                        }
                    }
                }
            });

        }

    }
    public interface OnAgentListener{
        void onAgentCick(int position);
    }

    public void AgentMethod(OnAgentListener onAgentListener){
        this.onAgentListener = onAgentListener;

    }
}
