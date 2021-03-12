package com.admereselvyn.mic;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.admereselvyn.mic.api.Api;
import com.admereselvyn.mic.api.core.CoreApiDetails;
import com.admereselvyn.mic.api.models.get_all_contests.Contest;
import com.admereselvyn.mic.api.models.user_model.UserData;
import com.google.gson.Gson;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Objects;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class Activity_SubmitFile extends AppCompatActivity {
    private static final String TAG = "Activity_SubmitFile";
    private TextView contestName, startDate, endDate, fee;
    private Button btn_submitFile;
    private ImageView bannerImage;
    private Contest contest;
    private String videoUri;
    private String videoPath;
    private UserData userData;
    private String displayName;
    private ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity__submit_file);

        contestName = findViewById(R.id.submitFile_contestName);
        startDate = findViewById(R.id.submitFile_startDate);
        endDate = findViewById(R.id.submitFile_endDate);
        fee = findViewById(R.id.submitFile_fees);
        btn_submitFile = findViewById(R.id.btn_submit_file);
        bannerImage = findViewById(R.id.submitFile_bannerImg);
        progressBar = findViewById(R.id.submitFile_progressBar);
        displayName = String.valueOf(Calendar.getInstance().getTimeInMillis()+".mp4");

        SharedPreferences sharedpreferences = Objects.requireNonNull(this).getSharedPreferences("userdata", Context.MODE_PRIVATE);
        Gson gson = new Gson();
        String data =sharedpreferences.getString("userdata", null);
        userData = gson.fromJson(data, UserData.class);

        contest = (Contest) getIntent().getSerializableExtra("ContestDetails");
        videoUri = (String) getIntent().getSerializableExtra("videoUri");
        videoPath = (String) getIntent().getSerializableExtra("videoPath");


        contestName.setText(contest.getName());
        startDate.setText(contest.getStartContest());
        endDate.setText(contest.getEndContest());
        fee.setText(contest.getFees()+"");


        btn_submitFile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                uploadFile();

            }
        });

    }


    private void openRegistrationActivity(){
        Intent i = new Intent(this, PaymentDetail.class);
        i.putExtra("ContestDetails", contest);
        startActivity(i);
        overridePendingTransition(0, 0);
    }

    private void uploadFile(){

        Api api = new CoreApiDetails();
        String upload_URL = api.getUploadVideoToContest(contest.getId(),userData.getId());
        String fileName = String.valueOf(Calendar.getInstance().getTimeInMillis()+".mp4");

        OkHttpClient client = new OkHttpClient().newBuilder()
                .build();
        MediaType mediaType = MediaType.parse("application/json");
        RequestBody body = new MultipartBody.Builder().setType(MultipartBody.FORM)
                .addFormDataPart("contestVideo",fileName,
                        RequestBody.create(MediaType.parse("application/octet-stream"),
                                new File(videoPath)))
                .build();
        Request request = new Request.Builder()
                .url(upload_URL)
                .method("POST", body)
                .addHeader("Content-Type", "application/json")
                .build();

        new Thread() {
            @Override
            public void run() {
                Log.d(TAG, "uploadFile: "+ upload_URL + " " + userData.getId() + " " + contest.getId());
                Response response = null;
                try {
                    response = client.newCall(request).execute();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                assert response != null;
                if(response.isSuccessful()){
                    Toast.makeText(getApplicationContext(),"Upload Successful",Toast.LENGTH_SHORT).show();
                }
                else{
                    Toast.makeText(getApplicationContext(),"Error " + response.code(),Toast.LENGTH_SHORT).show();
                }
                openRegistrationActivity();
            }
        }.start();

    }

}