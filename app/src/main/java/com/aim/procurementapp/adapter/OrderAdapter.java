package com.aim.procurementapp.adapter;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import com.aim.procurementapp.R;
import com.aim.procurementapp.model.PurchaseReq;
import java.util.List;

public class OrderAdapter extends ArrayAdapter {
    List<PurchaseReq> list;
    Activity context;
    int resource;

    public OrderAdapter(Activity context, int resource, List<PurchaseReq> list) {

        super(context, resource,list);
        this.context = context;
        this.list = list;
        this.resource = resource;
    }


    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        PurchaseReq pr = (PurchaseReq) getItem(position);

        LayoutInflater inflater = context.getLayoutInflater();
        View rowView = inflater.inflate(R.layout.layout_order,null,true);

        TextView tvDate = rowView.findViewById(R.id.tvDate);
        TextView tvMat = rowView.findViewById(R.id.tvMat);
        TextView tvSup = rowView.findViewById(R.id.tvSup);
        TextView tvQty = rowView.findViewById(R.id.tvQty);
        TextView tvTotal = rowView.findViewById(R.id.tvPrice);
        TextView tvStatus = rowView.findViewById(R.id.tvStatus);
        TextView tvAdrs = rowView.findViewById(R.id.tvAdrs);

        tvDate.setText(pr.getOrderDate());
        tvMat.setText(pr.getMaterial());
        tvSup.setText(pr.getSupplier());
        tvQty.setText("Qty- "+String.valueOf(pr.getQuantity()));
        tvTotal.setText("Rs. "+String.valueOf(pr.getTotal()));
        tvStatus.setText(pr.getStatus());
        tvAdrs.setText(pr.getAddress());

        return rowView;
    }
}
