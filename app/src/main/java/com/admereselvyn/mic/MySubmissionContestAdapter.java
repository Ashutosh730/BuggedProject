package com.admereselvyn.mic;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.admereselvyn.mic.api.models.get_all_contests.Contest;

import java.util.ArrayList;

public class MySubmissionContestAdapter extends RecyclerView.Adapter<MySubmissionContestAdapter.ViewHolder> {
    private ArrayList<Contest> contestsList;
    private Context context;
    public MySubmissionContestAdapter(Context context, ArrayList<Contest> contestsList){
        this.context=context;
        this.contestsList = contestsList;
    }
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.my_contest_item,parent,false);
        RecyclerView.LayoutParams lp =new RecyclerView.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        view.setLayoutParams(lp);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Contest contest = contestsList.get(position);
        holder.contestName.setText(contest.getName());
        holder.contestDesc.setText(contest.getDescription());
    }

    @Override
    public int getItemCount() {
        return 0;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private TextView contestName;
        private TextView contestDesc;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            contestName = itemView.findViewById(R.id.tv_contest_name);
            contestDesc = itemView.findViewById(R.id.tv_contest_description);
        }
    }
}
