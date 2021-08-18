package com.example.loginusingserver;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONObject;

public class MainActivity extends AppCompatActivity {

    EditText username,password;
    Button btnlogin;
    String web_url="https://shitalkawale.000webhostapp.com/connectionToServer/connect.php";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        username=findViewById(R.id.loginusername);
        password=findViewById(R.id.loginpassword);
        btnlogin=findViewById(R.id.loginbtn);

        btnlogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getConnection();
            }
        });

    }
    public  void getConnection(){

        StringRequest stringRequest=new StringRequest(Request.Method.POST, web_url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response)
                    {
                        try {
                            JSONObject jsonObject=new JSONObject(response);
                            int sucess=jsonObject.getInt("sucess");
                            String user=jsonObject.getString("Username");
                            String pass=jsonObject.getString("Password");

                            if (sucess==1)
                            {
                                if (username.getText().toString().equals(user) && password.getText().toString().equals(pass))
                                {
                                    Toast.makeText(MainActivity.this,"Login Successful...",Toast.LENGTH_LONG).show();
                                }
                                else
                                {
                                    Toast.makeText(MainActivity.this,"Invalid Data..",Toast.LENGTH_LONG).show();
                                }

                            }
                            else {

                            }
                        }
                        catch (Exception e)
                        {
                            //username.setText(e.getMessage());

                        }

                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error)
            {
                //username.setText(error.getMessage());
            }
        });

        RequestQueue requestQueue= Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);
    }

}