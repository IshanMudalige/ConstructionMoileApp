package com.aim.procurementapp;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.aim.procurementapp.model.Supplier;

import org.jetbrains.annotations.NotNull;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;


import static com.aim.procurementapp.ChooseSupActivity.REQ2;
import static com.aim.procurementapp.ChooseSupActivity.SUP;
import static com.aim.procurementapp.ChooseSupActivity.MAT2;
import static com.aim.procurementapp.ChooseSupActivity.DATE2;
import static com.aim.procurementapp.ChooseSupActivity.ADRS2;

public class SaveOrderActivity extends AppCompatActivity {

    EditText etQty,etPrice,etTotal;
    Button btnSave;
    double total;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_save_order);

        btnSave = findViewById(R.id.btnSave);
        etQty = findViewById(R.id.etQty);
        etPrice = findViewById(R.id.etUnitPrice);
        etTotal = findViewById(R.id.etTotal);
        etPrice.setEnabled(false);
        etTotal.setEnabled(false);

        Intent intent = getIntent();
        final Supplier sup = (Supplier) intent.getSerializableExtra(SUP);
        final String req = intent.getStringExtra(REQ2);
        final String mat = intent.getStringExtra(MAT2);
        final String date = intent.getStringExtra(DATE2);
        final String adrs = intent.getStringExtra(ADRS2);

        etPrice.setText(String.valueOf(sup.getUnitPrice()));

        etQty.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if(s.length()!=0) {
                    total = sup.getUnitPrice() * Double.parseDouble(String.valueOf(s));
                    etTotal.setText(String.valueOf(total));
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                System.out.println("================="+req+date+mat+adrs);
                OkHttpClient client = new OkHttpClient();
                String url = "https://procure-api.herokuapp.com/saveRequisition";

                RequestBody formBody = new FormBody.Builder()
                        .add("requisitionerName", req)
                        .add("orderDate", date)
                        .add("material", mat)
                        .add("quantity", String.valueOf(etQty.getText()))
                        .add("supplier", sup.getName())
                        .add("address", adrs)
                        .add("total", String.valueOf(etTotal.getText()))
                        .build();

                Request request = new Request.Builder()
                        .url(url)
                        .post(formBody)
                        .build();

                client.newCall(request).enqueue(new Callback() {
                    @Override
                    public void onFailure(@NotNull Call call, @NotNull IOException e) {

                    }

                    @Override
                    public void onResponse(@NotNull Call call, @NotNull Response response) throws IOException {
                        if (response.isSuccessful()) {
                            SaveOrderActivity.this.runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    AlertDialog.Builder adb = new AlertDialog.Builder(SaveOrderActivity.this);
                                    adb.setMessage("Order saved successfully");
                                    adb.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                                        @Override
                                        public void onClick(DialogInterface dialog, int which) {
                                            startActivity(new Intent(SaveOrderActivity.this,HomeActivity.class));
                                        }
                                    });
                                    AlertDialog alert = adb.create();
                                    alert.show();
                                }
                            });
                        }
                    }
                });
            }
        });

    }

}
