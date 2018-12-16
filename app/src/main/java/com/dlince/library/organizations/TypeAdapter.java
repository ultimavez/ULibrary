package com.dlince.library.organizations;

import android.content.Context;
import android.content.Intent;
import android.widget.ArrayAdapter;
import android.graphics.Color;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;
import com.dlince.library.organizations.model.Type;

public class TypeAdapter extends ArrayAdapter<Type> {
    private Context context;
    private List<Type> types;

    public TypeAdapter(@NonNull Context context, @LayoutRes int resource, @NonNull List<Type> objects) {
        super(context, resource, objects);
        this.context = context;
        this.types = objects;
    }

    @Override
    public View getView(final int pos, View convertView, ViewGroup parent){
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View rowView = inflater.inflate(R.layout.list_type, parent, false);

        TextView txtTyId = (TextView) rowView.findViewById(R.id.txtTyId);
        TextView txtName = (TextView) rowView.findViewById(R.id.txtName);
        TextView  txtTypeCreated = (TextView) rowView.findViewById(R.id.txtTypeCreated);

        if( types.get(pos).getStatus()==1 ){
            txtTyId.setTextColor(Color.BLACK);
            txtName.setTextColor(Color.BLACK);
            txtTypeCreated.setTextColor(Color.BLACK);
        }
        txtTyId.setText(String.format("Code: %d", types.get(pos).getId()));
        txtName.setText(String.format("Type: %s", types.get(pos).getName()));
        if( types.get(pos).getModified()==null ) {
            txtTypeCreated.setText(String.format("Created: %s", types.get(pos).getCreated()));
        }else{
            txtTypeCreated.setText(String.format("Modified: %s", types.get(pos).getModified()));
        }

        rowView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //start Activity User Form
                Intent intent = new Intent(context, TypeActivity.class);
                intent.putExtra("type_id", String.valueOf(types.get(pos).getId()));
                intent.putExtra("type_name", types.get(pos).getName());
                intent.putExtra("type_status", String.valueOf(types.get(pos).getStatus()));
                context.startActivity(intent);
            }
        });

        return rowView;
    }
}
