package com.aim.procurementapp.adapter;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.core.content.ContextCompat;

import com.aim.procurementapp.R;
import com.aim.procurementapp.model.Warehouse;

import java.util.List;

public class WarehouseAdapter extends ArrayAdapter {
    List<Warehouse> list;
    Activity context;
    int resource;

    public WarehouseAdapter(Activity context, int resource, List<Warehouse> list) {
        super(context, resource,list);
        this.context = context;
        this.list = list;
        this.resource = resource;
    }


    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        Warehouse wh = (Warehouse) getItem(position);

        LayoutInflater inflater = context.getLayoutInflater();
        View rowView = inflater.inflate(R.layout.layout_warehouse,null,true);

        TextView tvName = rowView.findViewById(R.id.tvWName);
        TextView tvMat = rowView.findViewById(R.id.tvWMat);
        TextView tvQty = rowView.findViewById(R.id.tvWQty);
        TextView tvStatus = rowView.findViewById(R.id.tvWStatus);

        tvMat.setText(wh.getMaterial());
        tvQty.setText("Qty- "+String.valueOf(wh.getQuantity()));
        tvStatus.setText(wh.getStatus());
        tvName.setText(wh.getWarehouse());

        if(wh.getStatus().equals("Full"))
            tvStatus.setTextColor(ContextCompat.getColor(context, R.color.orange));

        return rowView;
    }
}
