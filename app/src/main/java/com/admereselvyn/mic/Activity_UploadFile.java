package com.admereselvyn.mic;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.Manifest;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.provider.OpenableColumns;
import android.util.Base64;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.admereselvyn.mic.api.models.get_all_contests.Contest;
import com.karumi.dexter.Dexter;
import com.karumi.dexter.PermissionToken;
import com.karumi.dexter.listener.PermissionDeniedResponse;
import com.karumi.dexter.listener.PermissionGrantedResponse;
import com.karumi.dexter.listener.PermissionRequest;
import com.karumi.dexter.listener.single.PermissionListener;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;

public class Activity_UploadFile extends AppCompatActivity {

    private TextView contestName, startDate, endDate, fee;
    private final static int VIDEO_REQUEST_CODE = 1;
    private Button uploadFile_btn;
    private ImageView bannerImage;
    private Contest contest;
    private Bitmap bitmap;
    private Uri videoUri;
    private String videoPath;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity__upload_file);

        contestName = findViewById(R.id.uploadFile_contestName);
        startDate = findViewById(R.id.uploadFile_startDate);
        endDate = findViewById(R.id.uploadFile_endDate);
        fee = findViewById(R.id.uploadFile_fees);
        uploadFile_btn = findViewById(R.id.btn_upload_file);
        bannerImage = findViewById(R.id.uploadFile_bannerImg);

        contest = (Contest) getIntent().getSerializableExtra("ContestDetails");

        contestName.setText(contest.getName());
        startDate.setText(contest.getStartContest());
        endDate.setText(contest.getEndContest());
        fee.setText(contest.getFees()+"");

        uploadFile_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Dexter.withActivity(Activity_UploadFile.this)
                        .withPermission(Manifest.permission.READ_EXTERNAL_STORAGE)
                        .withListener(new PermissionListener() {
                            @Override
                            public void onPermissionGranted(PermissionGrantedResponse response)
                            {
                                Intent intent=new Intent(Intent.ACTION_PICK);
                                intent.setType("video/*");
                                startActivityForResult(Intent.createChooser(intent,"Browse Video"),VIDEO_REQUEST_CODE);
                            }

                            @Override
                            public void onPermissionDenied(PermissionDeniedResponse response) {

                            }

                            @Override
                            public void onPermissionRationaleShouldBeShown(PermissionRequest permission, PermissionToken token) {
                                token.continuePermissionRequest();
                            }
                        }).check();
            }
        });

    }

    private void openRegistrationActivity(){
        Intent i = new Intent(this, Activity_SubmitFile.class);
        i.putExtra("ContestDetails", contest);
        i.putExtra("videoUri", videoUri.toString());
        i.putExtra("videoPath",videoPath);
        startActivity(i);
        overridePendingTransition(0, 0);

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data)
    {
        if (resultCode == RESULT_OK) {

            if (requestCode == VIDEO_REQUEST_CODE) {
                if (data != null) {
                    Toast.makeText(this, "Video content URI: " + data.getData(),
                            Toast.LENGTH_LONG).show();
                    videoUri = data.getData();
                    videoPath = getPath(videoUri);
                }
            }
        }

        else if (resultCode != RESULT_CANCELED) {
            Toast.makeText(this, "Sorry, there was an error!", Toast.LENGTH_LONG).show();
        }
        super.onActivityResult(requestCode, resultCode, data);

        openRegistrationActivity();
    }

    public String getPath(Uri uri) {
        String[] projection = { MediaStore.Video.Media.DATA };
        Cursor cursor = getContentResolver().query(uri, projection, null, null, null);
        if (cursor != null) {

            int column_index = cursor
                    .getColumnIndexOrThrow(MediaStore.Video.Media.DATA);
            cursor.moveToFirst();
            return cursor.getString(column_index);
        } else
            return null;
    }
}