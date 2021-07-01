package com.example.tareanv;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import com.example.tareanv.models.UserLogin;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    EditText txtCorreo;
    EditText txtClave;
    Button btnLogin;
    private RequestQueue rq;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        txtCorreo = findViewById(R.id.txtCorreo);
        txtClave = findViewById(R.id.txtClave);
        btnLogin=findViewById(R.id.btnLogin);
        rq= Volley.newRequestQueue(this);

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                verificarLogin(txtCorreo.getText().toString(), txtClave.getText().toString());
            }
        });

    }



    public void verificarLogin(String correo, String clave){

        String dir="https://api.jsonbin.io/b/60dc2d76fe016b59dd589c62";

        JsonArrayRequest requerimiento=new JsonArrayRequest
                (Request.Method.GET,dir,null,
                        new com.android.volley.Response.Listener<JSONArray>() {
                            @Override
                            public void onResponse(JSONArray response) {
                               UserLogin userLogin;
                                for(int f=0;f<response.length();f++)
                                {
                                    try {
                                        JSONObject userObject=new JSONObject(response.get(f).toString());
                                        userLogin=(new UserLogin(userObject.get("username").toString(),
                                                userObject.get("email").toString(),
                                                userObject.get("password").toString(),
                                                userObject.get("foto").toString()));

                                        if(userLogin.getEmail().equals(correo) && userLogin.getPassword().equals(clave)){
                                            Intent intent=new Intent(MainActivity.this, Acceso.class);
                                            Bundle bundle =new Bundle();
                                            bundle.putString("username",userLogin.getUsername());
                                            bundle.putString("email",userLogin.getEmail());
                                            bundle.putString("foto",userLogin.getFoto());
                                            intent.putExtras(bundle);
                                            startActivity(intent);
                                        }
                                    } catch (JSONException e) {
                                        e.printStackTrace();
                                    }
                                }

                            }
                        },
                        new com.android.volley.Response.ErrorListener() {
                            @Override
                            public void onErrorResponse(VolleyError error) {

                            }
                        });
        rq.add(requerimiento);
    }
}