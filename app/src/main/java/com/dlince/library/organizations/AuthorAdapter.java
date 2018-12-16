package com.dlince.library.organizations;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.dlince.library.organizations.model.Author;

import java.util.List;

public class AuthorAdapter extends ArrayAdapter<Author> {

    private Context context;
    private List<Author> authors;

    public AuthorAdapter(@NonNull Context context, @LayoutRes int resource, @NonNull List<Author> objects) {
        super(context, resource, objects);
        this.context = context;
        this.authors = objects;
    }

    @Override
    public View getView(final int pos, View convertView, ViewGroup parent){
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View rowView = inflater.inflate(R.layout.list_organization, parent, false);

        TextView txtUserId = (TextView) rowView.findViewById(R.id.txtUserId);
        TextView txtUsername = (TextView) rowView.findViewById(R.id.txtUsername);
        TextView txtEmail = (TextView) rowView.findViewById(R.id.txtEmail);
        TextView txtGender = (TextView) rowView.findViewById(R.id.txtGender);
        TextView txtBirth = (TextView) rowView.findViewById(R.id.txtBirth);
        TextView txtAddress = (TextView) rowView.findViewById(R.id.txtAddress);
        TextView txtPweb = (TextView) rowView.findViewById(R.id.txtPweb);
        TextView txtStatus = (TextView) rowView.findViewById(R.id.txtStatus);
        TextView  txtOrganizationCreated = (TextView) rowView.findViewById(R.id.txtOrganizationCreated);

        if( authors.get(pos).getStatus()==1 ){
            txtUserId.setTextColor(Color.BLACK);
            txtUsername.setTextColor(Color.BLACK);
            txtEmail.setTextColor(Color.BLACK);
            txtGender.setTextColor(Color.BLACK);
            txtBirth.setTextColor(Color.BLACK);
            txtAddress.setTextColor(Color.BLACK);
            txtPweb.setTextColor(Color.BLACK);
            txtStatus.setTextColor(Color.BLACK);
            txtOrganizationCreated.setTextColor(Color.BLACK);
        }
        txtUserId.setText(String.format("Code: %d", authors.get(pos).getId()));
        txtUsername.setText(String.format("Author Name: %s", authors.get(pos).getNames()));
        txtEmail.setText(String.format("Mail: %s", authors.get(pos).getEmail()));
        txtGender.setText(String.format("Gender: %s", authors.get(pos).getGender()));
        txtBirth.setText(String.format("Birthday: %s", authors.get(pos).getBirth()));
        txtAddress.setText(String.format("Address: %s", authors.get(pos).getAddress()));
        txtPweb.setText(String.format("Website: %s", authors.get(pos).getPweb()));
        txtStatus.setText(String.format("Status: %s", authors.get(pos).getStatus()));
        if( authors.get(pos).getModified()==null ) {
            txtOrganizationCreated.setText(String.format("Created: %s", authors.get(pos).getCreated()));
        }else{
            txtOrganizationCreated.setText(String.format("Modified: %s", authors.get(pos).getModified()));
        }

        rowView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //start Activity User Form
                Intent intent = new Intent(context, AuthorActivity.class);
                intent.putExtra("organization_id", String.valueOf(authors.get(pos).getId()));
                intent.putExtra("organization_name", authors.get(pos).getNames());
                intent.putExtra("organization_email", authors.get(pos).getEmail());
                intent.putExtra("organization_gender", authors.get(pos).getGender());
                intent.putExtra("organization_birth", authors.get(pos).getBirth());
                intent.putExtra("organization_address", authors.get(pos).getAddress());
                intent.putExtra("organization_pweb", authors.get(pos).getPweb());
                intent.putExtra("organization_status", String.valueOf(authors.get(pos).getStatus()));
                context.startActivity(intent);
            }
        });

        return rowView;
    }
}