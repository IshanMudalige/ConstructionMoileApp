package com.aim.procurementapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.view.textclassifier.ConversationActions;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.aim.procurementapp.model.Material;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.jetbrains.annotations.NotNull;

import java.io.IOException;
import java.lang.reflect.Type;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class AddOrderActivity extends AppCompatActivity {

    EditText etAdrs,etDate,etReq;
    Spinner spinner;
    Button btnNext;
    public static String REQ ="com.aim.procurementapp.REQ";
    public static String DATE ="com.aim.procurementapp.DATE";
    public static String MAT ="com.aim.procurementapp.MAT";
    public static String ADRS ="com.aim.procurementapp.ADRS";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_order);

        etAdrs = findViewById(R.id.etAdrs);
        etDate = findViewById(R.id.etDate);
        etReq = findViewById(R.id.etReq);
        spinner = findViewById(R.id.spinner);
        btnNext = findViewById(R.id.btnNext);

        OkHttpClient client = new OkHttpClient();
        String url = "https://procure-api.herokuapp.com/getAllMaterials";

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

                    AddOrderActivity.this.runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            Gson gson = new Gson();
                            Type listType = new TypeToken<List<Material>>() {}.getType();
                            List<Material> materials = gson.fromJson(json, listType);
                            ArrayList<String> list = new ArrayList<>();
                            for(Material x:materials){
                                list.add(x.getName());
                            }
                            ArrayAdapter<String> adapter = new ArrayAdapter(AddOrderActivity.this,android.R.layout.simple_spinner_dropdown_item,list);
                            spinner.setAdapter(adapter);

                        }
                    });

                }
            }
        });


        btnNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String adr = etAdrs.getText().toString();
                String date = etDate.getText().toString();
                String req = etReq.getText().toString();
                    Intent intent = new Intent(AddOrderActivity.this,ChooseSupActivity.class);
                    intent.putExtra(ADRS,adr);
                    intent.putExtra(DATE,date);
                    intent.putExtra(REQ,req);
                    intent.putExtra(MAT,spinner.getSelectedItem().toString());
                    startActivity(intent);

            }
        });

    }


}
