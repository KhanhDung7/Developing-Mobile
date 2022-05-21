package com.example.nation;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.squareup.picasso.Picasso;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.math.BigDecimal;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    ListView lvNation;
    ArrayList<Nation> arrayNation;
    NationAdapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setTitle("Thông tin quốc gia");

        AnhXa();

        GetData();

    }

    private void DialogInfo(int index){
        Dialog dialog = new Dialog(this);
        dialog.setContentView(R.layout.dialog_custom);

        // Anh Xa
        TextView txtPopular = (TextView) dialog.findViewById(R.id.textViewPopular);
        TextView txtArea = (TextView) dialog.findViewById(R.id.textViewArea);
        TextView txtCapital = (TextView) dialog.findViewById(R.id.textViewCapital);
        ImageView imgMap = (ImageView) dialog.findViewById(R.id.imageViewMap);

        // Gan gia tri
        Nation currentNation = arrayNation.get(index);
        txtPopular.setText("DÂN SỐ: " + currentNation.getPopulation() + " NGƯỜI");
        txtArea.setText("DIỆN TÍCH: " + currentNation.getArea() + "  km²");
        txtCapital.setText("THỦ ĐÔ: " + currentNation.getCapital());
        Picasso.get().load(currentNation.getUrlMap()).into(imgMap);
        dialog.show();
    }

    private static boolean isNumeric(String str){
        return str != null && str.matches("[0-9.]+");
    }

    private void GetData() {
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        String url = "http://api.geonames.org/countryInfoJSON?username=caoth";
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, url, null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            JSONArray array = response.getJSONArray("geonames");

                            for (int i = 0; i < array.length(); i++) {
                                JSONObject objectNation = array.getJSONObject(i);
                                String countryCode = objectNation.getString("countryCode");
                                String countryName = objectNation.getString("countryName");
                                String capital = objectNation.getString("capital");
                                int area = new BigDecimal(objectNation.getString("areaInSqKm")).intValue();
                                int popular = Integer.valueOf(objectNation.getString("population"));

                                arrayNation.add(new Nation(countryCode, countryName, capital, area, popular));
                                adapter.notifyDataSetChanged();
                            }


                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(MainActivity.this, "Lỗi", Toast.LENGTH_SHORT).show();
                    }
                });
        requestQueue.add(jsonObjectRequest);
    }

    private void AnhXa() {
        lvNation = (ListView) findViewById(R.id.listviewNation);
        arrayNation = new ArrayList<>();
        adapter = new NationAdapter(this, R.layout.nation_item, arrayNation);
        lvNation.setAdapter(adapter);

        lvNation.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                DialogInfo(i);
            }
        });
    }
}