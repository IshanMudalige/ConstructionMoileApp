package com.aim.procurementapp.adapter;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import com.aim.procurementapp.R;
import com.aim.procurementapp.model.Supplier;

import java.util.List;

public class SupplierAdapter  extends ArrayAdapter {
    List<Supplier> list;
    Activity context;
    int resource;

    public SupplierAdapter(Activity context, int resource, List<Supplier> list) {

        super(context, resource,list);
        this.context = context;
        this.list = list;
        this.resource = resource;
    }


    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        Supplier sp = (Supplier) getItem(position);

        LayoutInflater inflater = context.getLayoutInflater();
        View rowView = inflater.inflate(R.layout.layout_supplier,null,true);

        TextView txSup = rowView.findViewById(R.id.txSupplier);

        txSup.setText(sp.getName());

        return rowView;
    }
}
