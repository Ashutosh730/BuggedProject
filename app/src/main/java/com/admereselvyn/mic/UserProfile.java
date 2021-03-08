package com.admereselvyn.mic;

import android.Manifest;
import android.app.ProgressDialog;
import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;
import com.admereselvyn.mic.api.Api;
import com.admereselvyn.mic.api.core.CoreApiDetails;
import com.admereselvyn.mic.api.models.login.LoginResponse;
import com.admereselvyn.mic.api.models.user_model.UserData;
import com.android.volley.NetworkResponse;
import com.android.volley.NoConnectionError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.Volley;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.tabs.TabLayout;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;
import com.google.gson.Gson;
import com.squareup.picasso.Picasso;
import org.json.JSONException;
import org.json.JSONObject;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import de.hdodenhof.circleimageview.CircleImageView;
import static android.app.Activity.RESULT_OK;
import static android.content.Context.MODE_PRIVATE;


public class UserProfile extends Fragment  {

    private static final int REQUEST_PERMISSIONS = 100 ;
    private static final int PICK_IMAGE_REQUEST =1 ;
    private UserData userLoginData;
    private ProgressDialog progressDialog;
    private CircleImageView dp;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.activity_user_profile, container, false);
        TextView userName=view.findViewById(R.id.userName);
        dp=view.findViewById(R.id.dp);
        FloatingActionButton upload_dp=view.findViewById(R.id.upload_dp);
        TabLayout tabLayout =view. findViewById(R.id.tabLayout);
        ViewPager viewPager = view.findViewById(R.id.vPager);

        progressDialog=new ProgressDialog(getActivity());

        //Showing userdata using shared preferences
        SharedPreferences sharedpreferences = Objects.requireNonNull(this.getActivity()).getSharedPreferences("userdata", MODE_PRIVATE);
        Gson gson = new Gson();
        String data =sharedpreferences.getString("userdata", null);
        if(data != null) {
            userLoginData = gson.fromJson(data,UserData.class);
            userName.setText(userLoginData.getName());  //Set name
            if(userLoginData.getLinkToProfileImg()!=null)
            {
                Picasso.with(getContext()).load(userLoginData.getLinkToProfileImg()).placeholder(R.drawable.ic_account_circle)
                        .resize(90,90).into(dp);  //set profile photo
            }
        }

        // Implementing tabLayout and viewPager and its swipe gesture and onClick action...
        PageAdapter pageAdapter = new PageAdapter(getChildFragmentManager(), tabLayout.getTabCount());
        viewPager.setAdapter(pageAdapter);

        // onClick action...
        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {

                viewPager.setCurrentItem(tab.getPosition());

                if(tab.getPosition() == 0 || tab.getPosition() == 1 || tab.getPosition() == 2)
                    pageAdapter.notifyDataSetChanged();
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });

        // swipe gesture action...
        viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));

        upload_dp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //Checking Permission
                permissionHandle(Manifest.permission.READ_EXTERNAL_STORAGE, REQUEST_PERMISSIONS );

            }
        });

        return view;
    }
//Handling permission
    void permissionHandle(String permission, int requestCode)
    {
        if (ContextCompat.checkSelfPermission(Objects.requireNonNull(getContext()), permission)
                == PackageManager.PERMISSION_DENIED) {
            requestPermissions(
                    new String[]{permission},
                    requestCode);
        } else {
            try {
                Intent intent = new Intent();
                intent.setType("image/*");
                intent.setAction(Intent.ACTION_GET_CONTENT);
                startActivityForResult(Intent.createChooser(intent, "Select Picture"), PICK_IMAGE_REQUEST);
            }
            catch (ActivityNotFoundException e)
            {
                Toast.makeText(getActivity(), "You do not have any app to perform this action", Toast.LENGTH_SHORT).show();
            }
        }
    }
    //Request Permission Result
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode ==  REQUEST_PERMISSIONS) {
            if (grantResults.length > 0
                    && grantResults[0] == PackageManager.PERMISSION_GRANTED) {

                try {
                    Intent intent = new Intent();
                    intent.setType("image/*");
                    intent.setAction(Intent.ACTION_GET_CONTENT);
                    startActivityForResult(Intent.createChooser(intent, "Select Picture"), PICK_IMAGE_REQUEST);
                }
                catch (ActivityNotFoundException e)
                {
                    Toast.makeText(getActivity(), "You do not have any app to perform this action", Toast.LENGTH_SHORT).show();
                }
            }
            else {
                Toast.makeText(getActivity(),
                        "Storage Permission Denied,Give storage permission manually by going to app setting ",
                        Toast.LENGTH_LONG)
                        .show();
            }
        }
    }
    //Fire up after getting result
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == PICK_IMAGE_REQUEST && resultCode == RESULT_OK && data != null && data.getData() != null) {
            Uri picUri = data.getData();

            //Getting actual path of the file
            String filePath = getPath(picUri);
            if (filePath != null) {
                try {
                    Log.d("filePath", String.valueOf(filePath));
                    Bitmap bitmap = MediaStore.Images.Media.getBitmap(Objects.requireNonNull(getActivity()).getContentResolver(), picUri);

                    //Uploading image using volley
                    uploadBitmap(bitmap,userLoginData.getId());
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            else
            {
                Toast.makeText(getActivity(),"No image selected", Toast.LENGTH_LONG).show();
            }
        }

    }
    //Getting Image path
    public String getPath(Uri uri) {
        Cursor cursor = Objects.requireNonNull(getActivity()).getContentResolver().query(uri, null, null, null, null);
        cursor.moveToFirst();
        String document_id = cursor.getString(0);
        document_id = document_id.substring(document_id.lastIndexOf(":") + 1);
        cursor.close();

        cursor = getActivity().getContentResolver().query(
                android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI,
                null, MediaStore.Images.Media._ID + " = ? ", new String[]{document_id}, null);
        cursor.moveToFirst();
        String path = cursor.getString(cursor.getColumnIndex(MediaStore.Images.Media.DATA));
        cursor.close();
        return path;
    }

    //Compressing Image
    public byte[] getFileDataFromDrawable(Bitmap bitmap) {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.PNG, 50, byteArrayOutputStream);
        return byteArrayOutputStream.toByteArray();
    }

    //Uploading Image
    private void uploadBitmap(final Bitmap bitmap,String id) {
        progressDialog.setMessage("Processing...");
        progressDialog.show();
        Api api = new CoreApiDetails();
        String ROOT_URL = api.getUploadUserProfileUrl(id);
        VolleyMultipartRequest volleyMultipartRequest = new VolleyMultipartRequest(Request.Method.POST, ROOT_URL,
                new Response.Listener<NetworkResponse>() {
                    @Override
                    public void onResponse(NetworkResponse response) {
                        try {
                            JSONObject parent = new JSONObject(new String(response.data));
                            if(parent.getString("status").equals("success"))
                            {
                                Gson gson = new Gson();
                                LoginResponse loginResponse = gson.fromJson(parent.toString(), LoginResponse.class);
                                SharedPreferences sharedpreferences = Objects.requireNonNull(getActivity()).getSharedPreferences("userdata", MODE_PRIVATE);
                                SharedPreferences.Editor editor = sharedpreferences.edit();
                                String json = gson.toJson(loginResponse.getData());
                                editor.putString("userdata", json);
                                editor.apply();
                                progressDialog.dismiss();
                                dp.setImageBitmap(bitmap);
                            }
                            Toast.makeText(getActivity(),parent.getString("message"), Toast.LENGTH_SHORT).show();

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        progressDialog.dismiss();
                        try{
                            //TODO: all code for error will go here
                            progressDialog.dismiss();
                            if(error instanceof NoConnectionError){
                                Toast.makeText(getActivity(), "Check Your Internet Connection", Toast.LENGTH_SHORT).show();
                            }
                            else{
                                String responseBody = new String(error.networkResponse.data, "utf-8");
                                JSONObject response = new JSONObject(responseBody);
                                Toast.makeText(getActivity(), response.getString("message"), Toast.LENGTH_SHORT).show();
                            }
                        }catch (Exception e){
                            Log.e("Exception",e.getMessage());
                        }
                    }
                }) {

            @Override
            protected Map<String, DataPart> getByteData() {
                Map<String, DataPart> params = new HashMap<>();
                long imagename = System.currentTimeMillis();
                params.put("profileImageUpload", new DataPart(imagename + ".png", getFileDataFromDrawable(bitmap)));
                return params;
            }
        };

        //adding the request to volley
        Volley.newRequestQueue(Objects.requireNonNull(getActivity())).add(volleyMultipartRequest);
    }

}
