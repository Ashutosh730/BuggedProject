package com.admereselvyn.mic;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import com.admereselvyn.mic.api.Api;
import com.admereselvyn.mic.api.core.CoreApiDetails;
import com.admereselvyn.mic.api.models.edit_profile.EditResponse;
import com.admereselvyn.mic.api.models.user_model.UserData;
import com.android.volley.NoConnectionError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.google.gson.Gson;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.Objects;

public class ProfileFragment extends Fragment {

    ProgressDialog progressDialog;
    RequestQueue queue;
    UserData userLoginData;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_profile, container, false);
        TextView phNumber=view.findViewById(R.id.phNumber);
        TextView aboutMe=view.findViewById(R.id.aboutMe);
        TextView contestParticipated=view.findViewById(R.id.contestParticipated);
        TextView micCoins=view.findViewById(R.id.micCoins);
        TextView email=view.findViewById(R.id.email);
        Button edit=view.findViewById(R.id.edit);
        progressDialog=new ProgressDialog(getContext());

        queue = Volley.newRequestQueue(Objects.requireNonNull(getContext()));  //For Volley

        // Showing userdata using shared preferences
        SharedPreferences sharedpreferences = Objects.requireNonNull(this.getActivity()).getSharedPreferences("userdata", Context.MODE_PRIVATE);
        Gson gson = new Gson();
        String data =sharedpreferences.getString("userdata", null);
        if(data != null) {
            userLoginData = gson.fromJson(data, UserData.class);
            phNumber.setText(userLoginData.getPhoneNo());
            aboutMe.setText(userLoginData.getAboutMe());
            contestParticipated.setText(String.valueOf(userLoginData.getContests().size()));
            micCoins.setText(String.valueOf(userLoginData.getCoins()));
            email.setText(userLoginData.getUsername());
        }

        edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //A dialog box will appear
                AlertDialog.Builder alertDialog= new AlertDialog.Builder(Objects.requireNonNull(getActivity()));
                LayoutInflater inflater=LayoutInflater.from(Objects.requireNonNull(getActivity()));
                View myView= inflater.inflate(R.layout.edit_dialogbox,null);
                alertDialog.setView(myView);
                AlertDialog dialog=alertDialog.create();
                dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                dialog.show();

                //After clicking save button
                Button save=myView.findViewById(R.id.save);
                save.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        EditText edit_name=myView.findViewById(R.id.edit_name);
                        EditText edit_phNo=myView.findViewById(R.id.edit_phNo);
                        EditText edit_age=myView.findViewById(R.id.edit_age);
                        EditText edit_address=myView.findViewById(R.id.edit_address);
                        EditText edit_city=myView.findViewById(R.id.edit_city);
                        EditText edit_state=myView.findViewById(R.id.edit_state);
                        EditText edit_aboutMe=myView.findViewById(R.id.edit_aboutMe);
                        EditText edit_whatsAppNo=myView.findViewById(R.id.edit_whatsAppNo);
                        EditText edit_fbLink=myView.findViewById(R.id.edit_fbLink);
                        EditText edit_instaLink=myView.findViewById(R.id.edit_instaLink);
                        EditText edit_twitterLink=myView.findViewById(R.id.edit_twitterLink);

                        String mName,mPhNo,mAge,mAddress,mCity,mState,mAbout,mWhatsApp,mFb,mInsta,mTwitter;
                        mName=edit_name.getText().toString().trim();
                        mPhNo=edit_phNo.getText().toString().trim();
                        mAge=edit_age.getText().toString().trim();
                        mAddress=edit_address.getText().toString().trim();
                        mCity=edit_city.getText().toString().trim();
                        mState=edit_state.getText().toString().trim();
                        mAbout=edit_aboutMe.getText().toString().trim();
                        mWhatsApp=edit_whatsAppNo.getText().toString().trim();
                        mFb=edit_fbLink.getText().toString().trim();
                        mInsta=edit_instaLink.getText().toString().trim();
                        mTwitter=edit_twitterLink.getText().toString().trim();

                        if(TextUtils.isEmpty(mName))
                        {
                            edit_name.setError("Required Field");
                            return;
                        }
                        if(TextUtils.isEmpty(mPhNo))
                        {
                            edit_phNo.setError("Required Field");
                            return;
                        }
                        if(TextUtils.isEmpty(mAge))
                        {
                            edit_age.setError("Required Field");
                            return;
                        }
                        if(TextUtils.isEmpty(mAddress))
                        {
                            edit_address.setError("Required Field");
                            return;
                        }
                        if(TextUtils.isEmpty(mCity))
                        {
                            mCity="";
                        }
                        if(TextUtils.isEmpty(mState))
                        {
                            mState="";
                        }
                        if(TextUtils.isEmpty(mAbout))
                        {
                            mAbout="";
                        }
                        if(TextUtils.isEmpty(mWhatsApp))
                        {
                            mWhatsApp="";
                        }
                        if(TextUtils.isEmpty(mFb))
                        {
                            mFb="";
                        }
                        if(TextUtils.isEmpty(mInsta))
                        {
                            mInsta="";
                        }
                        if(TextUtils.isEmpty(mTwitter))
                        {
                            mTwitter="";
                        }
                        progressDialog.setMessage("Processing...");
                        progressDialog.show();

                        //Calling performEdit method using volley
                        performEdit(dialog,userLoginData.getId(),mName,mPhNo,mAge,mAddress,mCity,mState,mAbout,mWhatsApp,mFb,mInsta,mTwitter);
                    }
                });

            }
        });

        return  view;
    }
    public void performEdit(AlertDialog dialog,String id,String name,String phNo,String age,String address,String city,String state,String about,String whatsApp,String fb,String insta,String twitter){

        JSONObject requestBody = new JSONObject();

        try {
            requestBody.put("name",name);
            requestBody.put("phone_no",phNo);
            requestBody.put("age",age);
            requestBody.put("address",address);
            requestBody.put("city",city);
            requestBody.put("state",state);
            requestBody.put("aboutMe",about);
            requestBody.put("whatsapp_no",whatsApp);
            requestBody.put("link_to_facebook_profile",fb);
            requestBody.put("link_to_instagram_profile",insta);
            requestBody.put("link_to_twitter_profile",twitter);
        } catch (JSONException e) {
            e.printStackTrace();
        }

        //Getting API
        Api api = new CoreApiDetails();
        String url = api.getEditProfileUrl(id);

        Gson gson = new Gson();

        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(
                Request.Method.POST, url, requestBody,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        EditResponse editResponse = gson.fromJson(response.toString(), EditResponse.class);
                        Log.e("response",editResponse.toString());

                        //TODO: all code for success will go here

                        if(editResponse.getStatus().equals("success"))
                        {
                              progressDialog.dismiss();
                              dialog.dismiss();
                              Toast.makeText(getContext(), "Profile Updated Successfully", Toast.LENGTH_SHORT).show();
                              Intent  intent=new Intent(getContext(),Login.class);

                              intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);//Clearing stack
                              startActivity(intent);
                        }
                        else
                        {
                            progressDialog.dismiss();
                            Toast.makeText(getContext(), "Something Wrong Happened", Toast.LENGTH_SHORT).show();
                        }

                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
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
                }
        );
        queue.add(jsonObjectRequest);

    }
}