package com.admereselvyn.mic;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.admereselvyn.mic.api.Api;
import com.admereselvyn.mic.api.core.CoreApiDetails;
import com.admereselvyn.mic.api.models.get_all_contests.Contest;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class UpComingContestFragment extends Fragment {

    // Declaration of variables...
    private RecyclerView all_contest_recyclerView;
    private List<Contest> contestList;
    private AllUpcomingContestAdapter adapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_up_coming_contest, container, false);

        // Initialization of variables...
        all_contest_recyclerView = view.findViewById(R.id.all_upcoming_contest_recyclerView);
        contestList = new ArrayList<>();

        return  view;
    }

    private void extractAllContest(View view) {

        Api api = new CoreApiDetails();
        String url = api.getAllContestsUrl();

        RequestQueue queue = Volley.newRequestQueue(Objects.requireNonNull(getContext()));

        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, url, null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {

                        try {

                            String status = response.getString("status");
                            if (status.equals("success")) {
                                JSONArray jsonContestData = response.getJSONArray("data");

                                for (int i = 0; i < jsonContestData.length(); i++) {
                                    Contest contest = new Contest(  );
                                    JSONObject jsonObject = jsonContestData.getJSONObject(i);
                                    JSONArray jsonVideos = jsonObject.getJSONArray("videos");
                                    List<String> list = new ArrayList<>();

                                    for (int j = 0; j < jsonVideos.length(); j++) {
                                        list.add(jsonVideos.get(j).toString());
                                    }

                                    contest.setVideos(list);
                                    contest.setName(jsonObject.getString("name"));
                                    contest.setStartContest(jsonObject.getString("start_contest"));
                                    contest.setEndContest(jsonObject.getString("end_contest"));
                                    contest.setFees(jsonObject.getInt("fees"));
//                                contest.setV(jsonObject.getInt("_v"));

                                    // Adding fetched data to the contestList variable
                                    contestList.add(contest);

                                }

                                // Inflating the recyclerView
                                all_contest_recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
                                adapter = new AllUpcomingContestAdapter(getActivity(), contestList);
                                all_contest_recyclerView.setAdapter(adapter);
                                adapter.setOnItemClickListener(new AllUpcomingContestAdapter.OnItemClickListener() {
                                    @Override
                                    public void onItemClick(int position) {
                                        openRegistrationActivity(position, contestList);
                                    }
                                });

                            }
                            else{
                                Toast.makeText(getActivity(), response.getString("message"), Toast.LENGTH_SHORT).show();

                            }
                        } catch(JSONException e){
                            e.printStackTrace();
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.d("tag", "onErrorResponse: " + error.getMessage());
            }
        });

        queue.add(jsonObjectRequest);

    }

    private void openRegistrationActivity(int position, List<Contest> contestList){
        Intent i = new Intent(getActivity(), Activity_RegisterNow.class);
        i.putExtra("ContestDetails", contestList.get(position));
        startActivity(i);
        ((Activity) getActivity()).overridePendingTransition(0, 0);

    }
}