package com.admereselvyn.mic;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.TextUtils;
import android.text.method.PasswordTransformationMethod;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.admereselvyn.mic.api.Api;
import com.admereselvyn.mic.api.core.CoreApiDetails;
import com.admereselvyn.mic.api.models.login.LoginResponse;
import com.android.volley.NetworkResponse;
import com.android.volley.NoConnectionError;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.Volley;
import org.json.JSONException;
import org.json.JSONObject;



import com.android.volley.Request;
import com.android.volley.toolbox.JsonObjectRequest;
import com.google.gson.Gson;

public class Login extends AppCompatActivity {
    private EditText mail,pass;
    private Button login;
    private TextView signUp,forgotPassword;
    private ImageView password_hide;
    private boolean isPasswordVisible=false;
    private SharedPreferences sp;
    ProgressDialog progressDialog;
    
    RequestQueue queue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        progressDialog=new ProgressDialog(Login.this);
        mail=findViewById(R.id.mail);
        pass=findViewById(R.id.pass);
        signUp=findViewById(R.id.signUp);
        login=findViewById(R.id.login);
        forgotPassword=findViewById(R.id.forgotPassword);
        password_hide=findViewById(R.id.password_hide);

        queue = Volley.newRequestQueue(this);

        signUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Login.this, Registration.class);
                startActivity(intent);
            }
        });

        // Checking is the user logged in or not
        sp = getSharedPreferences("logged",MODE_PRIVATE);

        if(sp.getBoolean("logged",false)){
            Intent i = new Intent(this, MainScreen.class);
            startActivity(i);
            finish();
        }

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String mpass,memail;
                mpass=pass.getText().toString().trim();
                memail=mail.getText().toString().trim();
                if(TextUtils.isEmpty(mpass))
                {
                    pass.setError("Required Field");
                    return;
                }
                if(TextUtils.isEmpty(memail))
                {
                    mail.setError("Required Field");
                    return;

                }
                progressDialog.setMessage("Processing...");
                progressDialog.show();
//                call to login API
                performLogin(memail,mpass);

            }
        });

        forgotPassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(Login.this,ForgotPassword.class);
                startActivity(intent);
            }
        });

        password_hide.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(isPasswordVisible){
                    ((ImageView)(v)).setImageResource(R.drawable.password_toggle_hide);

                    //Show Password
                    pass.setTransformationMethod(new PasswordTransformationMethod());
                    isPasswordVisible=false;
                }
                else{
                    ((ImageView)(v)).setImageResource(R.drawable.password_toggle_show);

                    //Hide Password
                    pass.setTransformationMethod(null);
                    isPasswordVisible=true;
                }
            }
        });
    }


    public void performLogin(String username,String password){
        JSONObject requestBody = new JSONObject();

        try {
            requestBody.put("username",username);
            requestBody.put("password",password);
        } catch (JSONException e) {
            e.printStackTrace();
        }

        Api api = new CoreApiDetails();
        String url = api.getLoginUrl();

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
                            mail.setText("");
                            pass.setText("");
                            progressDialog.dismiss();
                            Intent intent;
                            if (loginResponse.getData().getVerified()){
                                //user account is verified
                                intent=new Intent(Login.this,MainScreen.class);

                                //Storing userdata in shared preferences
                                SharedPreferences sharedpreferences = getSharedPreferences("userdata", MODE_PRIVATE);
                                SharedPreferences.Editor editor = sharedpreferences.edit();
                                Gson gson = new Gson();
                                String json = gson.toJson(loginResponse.getData());
                                editor.putString("userdata", json);
 //                               editor.putBoolean("isLoggedIn",true);
                                editor.apply();
                                SharedPreferences sharedpreferences1 = getSharedPreferences("logged", MODE_PRIVATE);
                                SharedPreferences.Editor editor1 = sharedpreferences1.edit();
                                editor1.putBoolean("isLoggedIn",true);
                                editor1.apply();

                            }else{
                                //user account is not verified
                                intent = new Intent(Login.this,EmailVerification.class);
                            }

                            startActivity(intent);
                        }
                        else
                        {
                            progressDialog.dismiss();
                            Toast.makeText(Login.this, "Something Wrong Happened", Toast.LENGTH_SHORT).show();
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
                                Toast.makeText(Login.this, "Check Your Internet Connection", Toast.LENGTH_SHORT).show();
                            }
                            else{
                                String responseBody = new String(error.networkResponse.data, "utf-8");
                                JSONObject response = new JSONObject(responseBody);
                                Toast.makeText(Login.this, response.getString("message"), Toast.LENGTH_SHORT).show();
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