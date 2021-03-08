package com.admereselvyn.mic;

import android.content.SharedPreferences;
import android.os.Bundle;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;
import com.admereselvyn.mic.api.Api;
import com.admereselvyn.mic.api.core.CoreApiDetails;
import com.admereselvyn.mic.api.models.user_model.UserData;
import com.android.volley.NoConnectionError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.google.gson.Gson;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import java.util.ArrayList;


import static android.content.Context.MODE_PRIVATE;

public class MySubmissionVideoFragment extends Fragment {
    RecyclerView recyclerView;
    RequestQueue queue;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_my_submission_video, container, false);

        recyclerView=view.findViewById(R.id.MySubmissionRecyclerViewVideo);

        //Fetching all user uploaded video
        SharedPreferences sharedpreferences = getActivity().getSharedPreferences("userdata", MODE_PRIVATE);
        Gson gson = new Gson();
        String data =sharedpreferences.getString("userdata", null);
        if(data != null) {
            UserData userLoginData = new UserData();
            userLoginData = gson.fromJson(data, UserData.class);
            String id = userLoginData.getId();
            if (id != null) {
                ArrayList<String> userUploadedVideoUrl=new ArrayList<>();
                JSONObject requestBody = new JSONObject();
                Api api = new CoreApiDetails();
                String url = api.getAllContestVideosUploadedByUser(id);

                JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(
                        Request.Method.GET, url, requestBody,
                        new Response.Listener<JSONObject>() {
                            @Override
                            public void onResponse(JSONObject response) {
                                try {
                                    String status=response.getString("status");
                                    if(status.equals("success"))
                                    {
                                        JSONArray jsonArray=response.getJSONArray("data");
                                        if(jsonArray.length()!=0) {
                                            for (int i = 0; i < jsonArray.length(); i++) {
                                                userUploadedVideoUrl.add(jsonArray.getString(i));
                                            }
                                            recyclerView.setLayoutManager(new GridLayoutManager(getActivity(), 2));
                                            MySubmissionVideoCustomAdapter customAdapter = new MySubmissionVideoCustomAdapter(getActivity(),userUploadedVideoUrl);
                                            recyclerView.setAdapter(customAdapter);
                                        }
                                    }
                                    else
                                    {
                                        Toast.makeText(getActivity(), response.getString("message"), Toast.LENGTH_SHORT).show();
                                    }
                                } catch (JSONException e) {
                                    e.printStackTrace();
                                }

                            }
                        },
                        new Response.ErrorListener() {
                            @Override
                            public void onErrorResponse(VolleyError error) {
                                try{
                                    //TODO: all code for error will go here
                                    if(error instanceof NoConnectionError){
                                        Toast.makeText(getActivity(), "Check Your Internet Connection", Toast.LENGTH_SHORT).show();
                                    }
                                }catch (Exception e){
                                    Log.e("Exception",e.getMessage());
                                }
                            }
                        }
                );
                queue = Volley.newRequestQueue(getActivity());
                queue.add(jsonObjectRequest);

            }
        }

            return view;
    }
}