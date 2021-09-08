package com.example.volley_demo;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import com.example.models.User;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private RequestQueue requestQueue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // initializre coada pentru request-uri
        requestQueue = Volley.newRequestQueue(this);
        this.getUsers();
    }

    private void getUsers() {
        // facem call-ul HTTP de GET
        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(
                "https://jsonplaceholder.typicode.com/users",
                response -> {
                    // In response ai lista de obiecte simple. le poti accesa folosind
                    //response.getJSONObject(i); unde i reprezeinta index-ul obiectului din array. Tot ce vine aici in response e obiect plain
                    // Aici dfinim un obiect Java de un Type custom. Avem nevoie de clasle POJO
                    // Se aplica doar pentru liste

                    Type listType = new TypeToken<List<User>>(){}.getType();
                    List<User> users = new Gson().fromJson(response.toString(), listType);
                },
                null
        );
        requestQueue.add(jsonArrayRequest);
    }
}