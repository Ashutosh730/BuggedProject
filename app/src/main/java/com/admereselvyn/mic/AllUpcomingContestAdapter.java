package com.admereselvyn.mic;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.admereselvyn.mic.api.models.get_all_contests.Contest;
import com.squareup.picasso.Picasso;

import java.util.List;

public class AllUpcomingContestAdapter extends RecyclerView.Adapter<AllUpcomingContestAdapter.ViewHolder> {

    private Context context;
    private List<Contest> contestList;
    private OnItemClickListener mListener;

    // Interface for OnItemClickListener...
    public interface OnItemClickListener {
        void onItemClick(int position);
    }

    public void setOnItemClickListener(OnItemClickListener listener) {
        mListener = listener;
    }


    public AllUpcomingContestAdapter(Context context, List<Contest> contestList) {
        this.contestList = contestList;
        this.context = context;
    }


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(context).inflate(R.layout.all_contest_item, parent, false);
        return new ViewHolder(view, mListener);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.item_contestName.setText(contestList.get(position).getName());
        holder.item_contestStartDate.setText(contestList.get(position).getStartContest());
        holder.item_contestEndDate.setText(contestList.get(position).getEndContest());
        holder.item_contestFee.setText(contestList.get(position).getFees()+"");
        holder.item_contestSlot.setText(contestList.get(position).getV()+"");
        Picasso.with(context).load(contestList.get(position).getLinkToContestImg()).into(holder.item_contestImage);

//        holder.parentHolder.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent i = new Intent(context, Activity_RegisterNow.class);
//                i.putExtra("ContestDetails", contestList.get(position));
//                context.startActivity(i);
//            }
//        });

    }

    @Override
    public int getItemCount() {
        return contestList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        TextView item_contestName,item_contestStartDate,item_contestEndDate,item_contestSlot,item_contestFee;
        ImageView item_contestImage;
        ConstraintLayout parentHolder;

        public ViewHolder(@NonNull View itemView, final OnItemClickListener listener) {
            super(itemView);
            Context ViewHolderContext= itemView.getContext();

            item_contestName = itemView.findViewById(R.id.item_contestName);
            item_contestStartDate = itemView.findViewById(R.id.item_contestStartDate);
            item_contestEndDate = itemView.findViewById(R.id.item_contestEndDate);
            item_contestSlot = itemView.findViewById(R.id.item_contestSlot);
            item_contestFee = itemView.findViewById(R.id.item_contestFee);
            item_contestImage = itemView.findViewById(R.id.item_contestImage);
            parentHolder = itemView.findViewById(R.id.item_contestParentHolder);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (listener != null) {
                        int position = getAdapterPosition();
                        if (position != RecyclerView.NO_POSITION) {
                            listener.onItemClick(position);
                        }
                    }
                }
            });

        }
    }
}
