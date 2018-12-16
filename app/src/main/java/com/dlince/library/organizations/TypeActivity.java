package com.dlince.library.organizations;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.view.View.OnClickListener;


import com.dlince.library.organizations.model.Crud;
import com.dlince.library.organizations.model.Type;
import com.dlince.library.organizations.remote.APIUtils;
import com.dlince.library.organizations.remote.CrudService;
import com.dlince.library.organizations.remote.TypeService;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class TypeActivity extends AppCompatActivity {

    CrudService crudService;
    TypeService typeService;
    EditText edtTyId;
    EditText edtName;
    Button btnSave;
    Button btnDel;
    TextView txtTyId;
    CheckBox checkbox1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_type);

        setTitle("Types");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        txtTyId = (TextView) findViewById(R.id.txtTyId);
        edtTyId = (EditText) findViewById(R.id.edtTyId);
        edtName = (EditText) findViewById(R.id.edtName);
        checkbox1 = (CheckBox) findViewById(R.id.checkbox1);
        btnSave = (Button) findViewById(R.id.btnSave);
        btnDel = (Button) findViewById(R.id.btnDel);

        /**/
        typeService = APIUtils.getTypeService();
        crudService = APIUtils.getCrudService();
        /**/

        Bundle extras = getIntent().getExtras();
        final String typeId = extras.getString("type_id");
        String typeName = extras.getString("type_name");
        String typeStatus = extras.getString("type_status");
        /**/
        edtTyId.setText(typeId);
        edtName.setText(typeName);

        if(typeId != null && typeId.trim().length() > 0 ){
            edtTyId.setFocusable(false);
            /**/
            if( Integer.parseInt(typeStatus)==1 ) {
                checkbox1.setChecked(true);
            }else{
                checkbox1.setChecked(false);
            }
            /**/
        } else {
            txtTyId.setVisibility(View.INVISIBLE);
            edtTyId.setVisibility(View.INVISIBLE);
            btnDel.setVisibility(View.INVISIBLE);
            checkbox1.setChecked(true);
        }

        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                /**/
                if (edtName.getText().toString() == null || edtName.getText().toString().trim().length() == 0) {
                    Toast.makeText(TypeActivity.this, "Type name is required!", Toast.LENGTH_SHORT).show();
                    edtName.setText("");
                    edtName.requestFocus();
                }else {
                    Type u = new Type();
                    Crud z = new Crud();
                    //Toast.makeText(OrganizationActivity.this, edtUsername.getText().toString(), Toast.LENGTH_SHORT).show();
                    u.setName(edtName.getText().toString());

                    z.setUser_id(2);
                    z.setEntity("types");
                    z.setBatch(1);
                    /**/
                    if( checkbox1.isChecked() ) {
                        //Toast.makeText(OrganizationActivity.this, "Organization enabled", Toast.LENGTH_SHORT).show();
                        u.setStatus(1);
                    }else{
                        //Toast.makeText(OrganizationActivity.this, "Organization disabled", Toast.LENGTH_SHORT).show();
                        u.setStatus(0);
                    }
                    /**/
                    if(typeId != null && typeId.trim().length() > 0){
                        //update organization
                        z.setType("put");
                        updateType(Integer.parseInt(typeId), u);
                        createCrud(z);
                    }else {
                        //add organization
                        z.setType("post");
                        addType(u);
                        createCrud(z);
                    }
                }
                /**/
            }
        });




        btnDel.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                DialogInterface.OnClickListener dialogClickListener = new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        switch (which) {
                            case DialogInterface.BUTTON_POSITIVE:

                                Crud z = new Crud();
                                z.setUser_id(2);
                                z.setType("delete");
                                z.setEntity("Type");
                                z.setBatch(1);
                                createCrud(z);
                                deleteType(Integer.parseInt(typeId));
                                Toast.makeText(TypeActivity.this, "Type deleted successfully!", Toast.LENGTH_SHORT).show();
                                /**/
                                Intent intent = new Intent(TypeActivity.this, MainActivity.class);
                                startActivity(intent);

                                break;

                            case DialogInterface.BUTTON_NEGATIVE:

                                Toast.makeText(TypeActivity.this, "No Clicked",
                                        Toast.LENGTH_LONG).show();
                                break;
                        }
                    }
                };

                AlertDialog.Builder builder = new AlertDialog.Builder(TypeActivity.this);
                builder.setMessage("Are you sure to delete this Type?")
                        .setPositiveButton("Yes", dialogClickListener)
                        .setNegativeButton("No", dialogClickListener).show();
            }
        });


    }

    public void addType(Type u){
        Call<Type> call = typeService.addType(u);
        call.enqueue(new Callback<Type>() {
            @Override
            public void onResponse(Call<Type> call, Response<Type> response) {
                if(response.isSuccessful()){
                    /**/
                    Toast.makeText(TypeActivity.this, "Type created successfully!", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(TypeActivity.this, MainActivity.class);
                    startActivity(intent);
                    /**/
                }
            }

            @Override
            public void onFailure(Call<Type> call, Throwable t) {
                Log.e("ERROR: ", t.getMessage());
            }
        });
    }

    public void updateType(int id, Type u){
        Call<Type> call = typeService.updateType(id, u);
        call.enqueue(new Callback<Type>() {
            @Override
            public void onResponse(Call<Type> call, Response<Type> response) {
                if(response.isSuccessful()){
                    /**/
                    Toast.makeText(TypeActivity.this, "Type updated successfully!", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(TypeActivity.this, MainActivity.class);
                    startActivity(intent);
                    /**/
                }
            }

            @Override
            public void onFailure(Call<Type> call, Throwable t) {
                Log.e("ERROR: ", t.getMessage());
            }
        });
    }

    public void deleteType(int id){
        Call<Type> call = typeService.deleteType(id);
        call.enqueue(new Callback<Type>() {
            @Override
            public void onResponse(Call<Type> call, Response<Type> response) {
                if(response.isSuccessful()){
                    /**/
                    Toast.makeText(TypeActivity.this, "Type deleted successfully!", Toast.LENGTH_SHORT).show();
                    /**/
                }
            }

            @Override
            public void onFailure(Call<Type> call, Throwable t) {
                Log.e("ERROR: ", t.getMessage());
            }
        });
    }

    public void createCrud(Crud z){
        Call<Crud> call = crudService.createCrud(z);
        call.enqueue(new Callback<Crud>() {
            @Override
            public void onResponse(Call<Crud> call, Response<Crud> response) {
                if(response.isSuccessful()){
                    /**/
                    Toast.makeText(TypeActivity.this, "Crud created successfully!", Toast.LENGTH_SHORT).show();
                    /**/
                }
            }

            @Override
            public void onFailure(Call<Crud> call, Throwable t) {
                Log.e("ERROR: ", t.getMessage());
            }
        });
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
                return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
