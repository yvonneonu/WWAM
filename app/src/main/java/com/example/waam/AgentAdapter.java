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
        holder.firstName.setText(agentModel.getName());
        holder.firstRate.setText(agentModel.getRating());
        holder.number1Rate.setText(agentModel.getRating1());
    }

    @Override
    public int getItemCount() {
        return agentModelList.size();
    }

    public class Viewholder extends RecyclerView.ViewHolder {
        ImageView firstAgent, secondAgent;
        TextView firstName, secondName, firstRate, secondRate, number1Rate, number2Rate;
        CardView cardView, cardView1;
       // CardView ;


        public Viewholder(@NonNull View itemView) {
            super(itemView);
            firstAgent = itemView.findViewById(R.id.agentimage);
            firstName = itemView.findViewById(R.id.agentName);
            firstRate = itemView.findViewById(R.id.rateone);
            number1Rate = itemView.findViewById(R.id.numb);
            cardView = itemView.findViewById(R.id.card1);

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
