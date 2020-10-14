package com.aim.procurementapp;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import com.aim.procurementapp.adapter.SupplierAdapter;
import com.aim.procurementapp.model.Supplier;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import org.jetbrains.annotations.NotNull;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.List;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import static com.aim.procurementapp.AddOrderActivity.ADRS;
import static com.aim.procurementapp.AddOrderActivity.MAT;
import static com.aim.procurementapp.AddOrderActivity.REQ;
import static com.aim.procurementapp.AddOrderActivity.DATE;

/*
   *add order second step display suppliers and material availability
   *user select one of the supplier
 */
public class ChooseSupActivity extends AppCompatActivity {

    ImageView imgAvb;
    ListView supListView;
    TextView tvMat;
    SupplierAdapter adapter;
    public static String SUP ="com.aim.procurementapp.SUP";
    public static String REQ2 ="com.aim.procurementapp.REQ2";
    public static String DATE2 ="com.aim.procurementapp.DATE2";
    public static String MAT2 ="com.aim.procurementapp.MAT2";
    public static String ADRS2 ="com.aim.procurementapp.ADRS2";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choose_sup);

        supListView = findViewById(R.id.listViewSupplier);
        tvMat = findViewById(R.id.txMat);
        imgAvb = findViewById(R.id.imgAvb);

        Intent intent = getIntent();
        final String req = intent.getStringExtra(REQ);
        final String mat = intent.getStringExtra(MAT);
        final String date = intent.getStringExtra(DATE);
        final String adrs = intent.getStringExtra(ADRS);

        //get supplier from the api
        OkHttpClient client = new OkHttpClient();
        String url = "https://procure-api.herokuapp.com/getSuppliers";

        RequestBody formBody = new FormBody.Builder()
                .add("material", mat)
                .build();

        Request request = new Request.Builder()
                .url(url)
                .post(formBody)
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

                    ChooseSupActivity.this.runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            Gson gson = new Gson();
                            Type listType = new TypeToken<List<Supplier>>() {}.getType();
                            List<Supplier> suppliers = gson.fromJson(json, listType);

                            //display message material not available
                            if (suppliers.isEmpty()){
                                tvMat.setText("Material Not Available");
                                imgAvb.setImageResource(R.drawable.ic_close_black_24dp);

                            } else{// display message material available
                                tvMat.setText("Material Available");
                                imgAvb.setImageResource(R.drawable.ic_check_black_24dp);
                                adapter = new SupplierAdapter(ChooseSupActivity.this,R.layout.layout_supplier,suppliers);
                                supListView.setAdapter(adapter);
                            }

                        }
                    });

                }
            }
        });

        //get selected supplier from the list
        supListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Supplier sup = (Supplier) adapter.getItem(position);
                Intent intent = new Intent(ChooseSupActivity.this,SaveOrderActivity.class);
                intent.putExtra(ADRS2,adrs);
                intent.putExtra(DATE2,date);
                intent.putExtra(REQ2,req);
                intent.putExtra(MAT2,mat);
                intent.putExtra(SUP,sup);
                startActivity(intent);

            }
        });
    }
}
