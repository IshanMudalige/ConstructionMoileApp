package com.aim.procurementapp;

import androidx.appcompat.app.AppCompatActivity;
import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import com.aim.procurementapp.model.Material;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import org.jetbrains.annotations.NotNull;
import java.io.IOException;
import java.lang.reflect.Type;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/*
  adding order first step choose material,delivery date,supplier and name.
 */
public class AddOrderActivity extends AppCompatActivity {

    EditText etAdrs,etDate,etReq;
    Spinner spinner;
    Button btnNext;
    final Calendar myCalendar = Calendar.getInstance();
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

        //get all the material categories from system using api
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


        //date picker dialog handler
        final DatePickerDialog.OnDateSetListener date = new DatePickerDialog.OnDateSetListener() {

            @Override
            public void onDateSet(DatePicker view, int year, int monthOfYear,
                                  int dayOfMonth) {
                myCalendar.set(Calendar.YEAR, year);
                myCalendar.set(Calendar.MONTH, monthOfYear);
                myCalendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);
                updateLabel();
            }

        };

        //date field handler
        etDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new DatePickerDialog(AddOrderActivity.this, date, myCalendar
                        .get(Calendar.YEAR), myCalendar.get(Calendar.MONTH),
                        myCalendar.get(Calendar.DAY_OF_MONTH)).show();
            }
        });


        //next button click method
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

    //set date field value
    private void updateLabel() {
        String myFormat = "MM/dd/yy"; //In which you need put here
        SimpleDateFormat sdf = new SimpleDateFormat(myFormat, Locale.US);

        etDate.setText(sdf.format(myCalendar.getTime()));
    }


}
