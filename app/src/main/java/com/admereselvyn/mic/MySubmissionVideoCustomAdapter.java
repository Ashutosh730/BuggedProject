package com.admereselvyn.mic;

import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;
import android.widget.VideoView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;

public class MySubmissionVideoCustomAdapter extends RecyclerView.Adapter< MySubmissionVideoCustomAdapter.MyHolder>{
    private static final String TAG = "MySubmissionVideoCustom";
    LayoutInflater layoutInflater;
    ArrayList<String> videoLink;
    Context context;

    public MySubmissionVideoCustomAdapter(Context c, ArrayList<String> videoLink) {
    
        context=c;
        this.videoLink=videoLink;
        layoutInflater=LayoutInflater.from(context);
        notifyDataSetChanged();

    }

    @NonNull
    @Override
    public MyHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View myview=layoutInflater.inflate(R.layout.my_submission_item,parent,false);
        RecyclerView.LayoutParams lp = new RecyclerView.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        myview.setLayoutParams(lp);
        Log.d(TAG, "onCreateViewHolder: " + videoLink.get(0));
        MyHolder newholder= new MyHolder(myview);
        return newholder;
    }

    @Override
    public void onBindViewHolder(@NonNull  MySubmissionVideoCustomAdapter.MyHolder holder, int position) {
        Log.d(TAG, "onBindViewHolder: ");
        holder.singleVideo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String currentVideoUrl = videoLink.get(position);
                try {
                    Intent playVideo = new Intent(Intent.ACTION_VIEW);
                    playVideo.setDataAndType(Uri.parse(currentVideoUrl), "video/*");
                    context.startActivity(playVideo);
                }catch (ActivityNotFoundException e) {
                    Toast.makeText(context, "You don't have any app that can open this ", Toast.LENGTH_SHORT).show();
                }
            }
        });


    }

    @Override
    public int getItemCount() {
        return videoLink.size();
    }
    public static class MyHolder extends RecyclerView.ViewHolder {
        ImageView singleVideo;
        public MyHolder(@NonNull View itemView) {
            super(itemView);
            singleVideo=itemView.findViewById(R.id.singleVideo);

        }
    }
}


