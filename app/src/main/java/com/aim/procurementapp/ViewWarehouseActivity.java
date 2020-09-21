package com.aim.procurementapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ListView;

import com.aim.procurementapp.adapter.WarehouseAdapter;
import com.aim.procurementapp.model.Warehouse;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.jetbrains.annotations.NotNull;

import java.io.IOException;
import java.lang.reflect.Type;
import java.util.List;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class ViewWarehouseActivity extends AppCompatActivity {

    ListView listview;
    WarehouseAdapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_warehouse);

        listview = findViewById(R.id.wareListView);

        OkHttpClient client = new OkHttpClient();
        String url = "https://procure-api.herokuapp.com/getAllInventory";

        Request request = new Request.Builder()
                .url(url)
                .build();

        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(@NotNull Call call, @NotNull IOException e) {
                e.printStackTrace();
            }

            @Override
            public void onResponse(@NotNull Call call, @NotNull Response response) throws IOException {
                if (response.isSuccessful()) {
                    final String json = response.body().string();

                    ViewWarehouseActivity.this.runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            Gson gson = new Gson();
                            System.out.println("---------"+json);
                            Type listType = new TypeToken<List<Warehouse>>() {}.getType();
                            List<Warehouse> whs = gson.fromJson(json, listType);
                            System.out.println("==============="+whs.get(0).getMaterial());
                            adapter = new WarehouseAdapter(ViewWarehouseActivity.this,R.layout.layout_warehouse,whs);
                            listview.setAdapter(adapter);
                        }
                    });

                }
            }
        });

    }
}
