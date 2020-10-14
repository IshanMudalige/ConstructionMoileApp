package com.aim.procurementapp;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.os.Bundle;
import android.widget.ListView;

import com.aim.procurementapp.adapter.OrderAdapter;
import com.aim.procurementapp.model.PurchaseReq;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.jetbrains.annotations.NotNull;

import java.io.IOException;
import java.lang.reflect.Type;

import java.util.Collections;
import java.util.List;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
/*
    View previous orders activity
 */
public class ViewOrderActivity extends AppCompatActivity {

    ListView orderListView;
    OrderAdapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_order);

        orderListView = findViewById(R.id.orderListView);

        //display loading dialog
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setView(R.layout.layout_loading);
        final Dialog dialog = builder.create();
        dialog.show();

        //get data from the api
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
                            Collections.reverse(prs);
                            adapter = new OrderAdapter(ViewOrderActivity.this,R.layout.layout_order,prs);
                            orderListView.setAdapter(adapter);
                            dialog.dismiss();
                        }
                    });

                }
            }
        });
    }
}
