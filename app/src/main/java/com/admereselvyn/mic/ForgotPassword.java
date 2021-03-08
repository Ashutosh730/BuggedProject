package com.admereselvyn.mic;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.admereselvyn.mic.api.Api;
import com.admereselvyn.mic.api.core.CoreApiDetails;
import com.admereselvyn.mic.api.models.login.LoginResponse;
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

public class ForgotPassword extends AppCompatActivity {
    EditText registered_email;
    Button sendResetLink;
    RequestQueue queue;
    ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgot_password);

        registered_email=findViewById(R.id.registered_email);
        sendResetLink=findViewById(R.id.sendResetLink);

        queue = Volley.newRequestQueue(this);
        progressDialog=new ProgressDialog(ForgotPassword.this);

        sendResetLink.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String memail;
                memail=registered_email.getText().toString().trim();
                if(TextUtils.isEmpty(memail))
                {
                   registered_email.setError("Required Field");
                    return;
                }
                progressDialog.setMessage("Processing...");
                progressDialog.show();
//                call to login API
                performSendingEmail(memail);
            }
        });
    }
    void performSendingEmail(String email)
    {
        JSONObject requestBody = new JSONObject();

        try {
            requestBody.put("username",email);
        } catch (JSONException e) {
            e.printStackTrace();
        }

        Api api = new CoreApiDetails();
        String url = api.getForgetPasswordUrl();

        Gson gson = new Gson();

        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(
                Request.Method.POST, url, requestBody,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        LoginResponse loginResponse = gson.fromJson(response.toString(), LoginResponse.class);
                        Log.e("response",loginResponse.toString());

                        //TODO: all code for success will go here

                        if(loginResponse.getStatus().equals("success"))
                        {
                            progressDialog.dismiss();
                            registered_email.setText("");
                            Toast.makeText(ForgotPassword.this, "An email has been sent to "+email, Toast.LENGTH_LONG).show();
                            Intent  intent=new Intent(ForgotPassword.this,Login.class);
                            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);//Clearing stack
                            startActivity(intent);
                        }
                        else
                        {
                            Toast.makeText(ForgotPassword.this, loginResponse.getMessage(), Toast.LENGTH_SHORT).show();
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
                                Toast.makeText(ForgotPassword.this, "Check Your Internet Connection", Toast.LENGTH_SHORT).show();
                            }
                            else{
                                String responseBody = new String(error.networkResponse.data, "utf-8");
                                JSONObject response = new JSONObject(responseBody);
                                Toast.makeText(ForgotPassword.this, response.getString("message"), Toast.LENGTH_SHORT).show();
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