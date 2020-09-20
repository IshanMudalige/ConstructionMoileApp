package com.aim.procurementapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.aim.procurementapp.adapter.OrderAdapter;
import com.aim.procurementapp.adapter.SupplierAdapter;
import com.aim.procurementapp.model.PurchaseReq;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.jetbrains.annotations.NotNull;

import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class ViewOrderActivity extends AppCompatActivity {

    ListView orderListView;
    OrderAdapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_order);

        orderListView = findViewById(R.id.orderListView);

        OkHttpClient client = new OkHttpClient();
        String url = "https://procure-api.herokuapp.com/getAllRequisition";

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

                    ViewOrderActivity.this.runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            Gson gson = new Gson();
                            Type listType = new TypeToken<List<PurchaseReq>>() {}.getType();
                            List<PurchaseReq> prs = gson.fromJson(json, listType);
                            adapter = new OrderAdapter(ViewOrderActivity.this,R.layout.layout_order,prs);
                            orderListView.setAdapter(adapter);
                        }
                    });

                }
            }
        });
    }
}
