package com.aim.procurementapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
/*
    Home activity display add order, view orders and warehouse details
 */
public class HomeActivity extends AppCompatActivity {

    Button btnView,btnCreate,btnDetail;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        btnCreate = findViewById(R.id.btnCreate);
        btnView = findViewById(R.id.btnView);
        btnDetail = findViewById(R.id.btnDetail);

        //add order button handler
        btnCreate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(HomeActivity.this,AddOrderActivity.class));
            }
        });

        //view orders button handler
        btnView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(HomeActivity.this,ViewOrderActivity.class));
            }
        });

        //view warehouse details button handler
        btnDetail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(HomeActivity.this,ViewWarehouseActivity.class));
            }
        });
    }
}
