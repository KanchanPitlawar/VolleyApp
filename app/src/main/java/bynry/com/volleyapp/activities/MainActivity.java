package bynry.com.volleyapp.activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.bumptech.glide.Glide;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import bynry.com.volleyapp.adapters.ActorsAdapter;
import bynry.com.volleyapp.R;
import bynry.com.volleyapp.models.Actors;

public class MainActivity extends AppCompatActivity {

    private String name = "Kanchan";
    private static final String JSON_URL = "https://simplifiedcoding.net/demos/view-flipper/heroes.php";
    private RecyclerView recyclerView;
    private ArrayList<Actors> mActors;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = findViewById(R.id.recycler_view);
        mActors = new ArrayList<>();
        loadActors();
    }

    private void loadActors() {

        StringRequest stringRequest = new StringRequest(Request.Method.GET, JSON_URL, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

                try {
                    JSONObject jsonObject = new JSONObject(response);

                    JSONArray jsonArray = jsonObject.getJSONArray("heroes");

                    for (int i = 0; i < jsonArray.length(); i++) {

                        JSONObject actorObject = jsonArray.getJSONObject(i);

                        Actors actors = new Actors(actorObject.getString("name"), actorObject.getString("imageurl"));
                        mActors.add(actors);
                    }

                    ActorsAdapter actorsAdapter = new ActorsAdapter(MainActivity.this, mActors, Glide.with(MainActivity.this));
                    LinearLayoutManager layoutManager = new LinearLayoutManager(MainActivity.this);
                    recyclerView.setLayoutManager(layoutManager);
                    recyclerView.setAdapter(actorsAdapter);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {

                        Toast.makeText(getApplicationContext(),error.getMessage(),Toast.LENGTH_SHORT).show();

                    }
                });

        RequestQueue requestQueue = Volley.newRequestQueue(MainActivity.this);
        requestQueue.add(stringRequest);
    }
}
