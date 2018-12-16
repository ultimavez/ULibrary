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
import com.dlince.library.organizations.model.Author;
import com.dlince.library.organizations.remote.APIUtils;
import com.dlince.library.organizations.remote.CrudService;
import com.dlince.library.organizations.remote.AuthorService;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AuthorActivity extends AppCompatActivity {

    CrudService crudService;
    AuthorService authorService;
    EditText edtUId;
    EditText edtUsername;
    EditText edtEmail;
    EditText edtGender;
    EditText edtBirth;
    EditText edtAddress;
    EditText edtPweb;
    Button btnSave;
    Button btnDel;
    TextView txtUId;
    CheckBox checkbox1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_author);

        setTitle("Authors");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        txtUId = (TextView) findViewById(R.id.txtUId);
        edtUId = (EditText) findViewById(R.id.edtUId);
        edtUsername = (EditText) findViewById(R.id.edtUsername);
        edtEmail = (EditText) findViewById(R.id.edtEmail);
        edtGender = (EditText) findViewById(R.id.edtGender);
        edtBirth = (EditText) findViewById(R.id.edtBirth);
        edtAddress = (EditText) findViewById(R.id.edtAddress);
        edtPweb = (EditText) findViewById(R.id.edtPweb);
        checkbox1 = (CheckBox) findViewById(R.id.checkbox1);
        btnSave = (Button) findViewById(R.id.btnSave);
        btnDel = (Button) findViewById(R.id.btnDel);

        /**/
        authorService = APIUtils.getOrganizationService();
        crudService = APIUtils.getCrudService();
        /**/

        Bundle extras = getIntent().getExtras();
        final String organizationId = extras.getString("organization_id");
        String organizationName = extras.getString("organization_name");
        String organizationEmail = extras.getString("organization_email");
        String organizationGender = extras.getString("organization_gender");
        String organitazionBirth = extras.getString("organization_birth");
        String organitazionAddress = extras.getString("organization_address");
        String organitazionPweb = extras.getString("organization_pweb");
        String organizationStatus = extras.getString("organization_status");
        /**/
        edtUId.setText(organizationId);
        edtUsername.setText(organizationName);
        edtEmail.setText(organizationEmail);
        edtGender.setText(organizationGender);
        edtBirth.setText(organitazionBirth);
        edtAddress.setText(organitazionAddress);
        edtPweb.setText(organitazionPweb);

        if(organizationId != null && organizationId.trim().length() > 0 ){
            edtUId.setFocusable(false);
            /**/
            if( Integer.parseInt(organizationStatus)==1 ) {
                checkbox1.setChecked(true);
            }else{
                checkbox1.setChecked(false);
            }
            /**/
        } else {
            txtUId.setVisibility(View.INVISIBLE);
            edtUId.setVisibility(View.INVISIBLE);
            btnDel.setVisibility(View.INVISIBLE);
            checkbox1.setChecked(true);
        }

        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                /**/
                if (edtUsername.getText().toString() == null || edtUsername.getText().toString().trim().length() == 0) {
                    Toast.makeText(AuthorActivity.this, "Organization name is required!", Toast.LENGTH_SHORT).show();
                    edtUsername.setText("");
                    edtUsername.requestFocus();
                    edtEmail.setText("");
                    edtEmail.requestFocus();
                    edtGender.setText("");
                    edtGender.requestFocus();
                    edtBirth.setText("");
                    edtBirth.requestFocus();
                    edtAddress.setText("");
                    edtAddress.requestFocus();
                    edtPweb.setText("");
                    edtPweb.requestFocus();
                }else {
                    Author u = new Author();
                    Crud z = new Crud();
                    //Toast.makeText(OrganizationActivity.this, edtUsername.getText().toString(), Toast.LENGTH_SHORT).show();
                    u.setNames(edtUsername.getText().toString());
                    u.setEmail(edtEmail.getText().toString());
                    u.setGender(edtGender.getText().toString());
                    u.setBirth(edtBirth.getText().toString());
                    u.setAddress(edtAddress.getText().toString());
                    u.setPweb(edtPweb.getText().toString());
                    z.setUser_id(2);
                    z.setEntity("authors");
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
                    if(organizationId != null && organizationId.trim().length() > 0){
                        //update organization
                        z.setType("put");
                        updateOrganization(Integer.parseInt(organizationId), u);
                        createCrud(z);
                    }else {
                        //add organization
                        z.setType("post");
                        addOrganization(u);
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
                                z.setEntity("authors");
                                z.setBatch(1);
                                createCrud(z);
                                deleteOrganization(Integer.parseInt(organizationId));
                                Toast.makeText(AuthorActivity.this, "Author deleted successfully!", Toast.LENGTH_SHORT).show();
                                /**/
                                Intent intent = new Intent(AuthorActivity.this, MainActivity.class);
                                startActivity(intent);

                                break;

                            case DialogInterface.BUTTON_NEGATIVE:

                                Toast.makeText(AuthorActivity.this, "No Clicked",
                                        Toast.LENGTH_LONG).show();
                                break;
                        }
                    }
                };

                AlertDialog.Builder builder = new AlertDialog.Builder(AuthorActivity.this);
                builder.setMessage("Are you sure to delete this Author?")
                        .setPositiveButton("Yes", dialogClickListener)
                        .setNegativeButton("No", dialogClickListener).show();
            }
        });


    }

    public void addOrganization(Author u){
        Call<Author> call = authorService.addOrganization(u);
        call.enqueue(new Callback<Author>() {
            @Override
            public void onResponse(Call<Author> call, Response<Author> response) {
                if(response.isSuccessful()){
                    /**/
                    Toast.makeText(AuthorActivity.this, "Organization created successfully!", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(AuthorActivity.this, MainActivity.class);
                    startActivity(intent);
                    /**/
                }
            }

            @Override
            public void onFailure(Call<Author> call, Throwable t) {
                Log.e("ERROR: ", t.getMessage());
            }
        });
    }

    public void updateOrganization(int id, Author u){
        Call<Author> call = authorService.updateOrganization(id, u);
        call.enqueue(new Callback<Author>() {
            @Override
            public void onResponse(Call<Author> call, Response<Author> response) {
                if(response.isSuccessful()){
                    /**/
                    Toast.makeText(AuthorActivity.this, "Organization updated successfully!", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(AuthorActivity.this, MainActivity.class);
                    startActivity(intent);
                    /**/
                }
            }

            @Override
            public void onFailure(Call<Author> call, Throwable t) {
                Log.e("ERROR: ", t.getMessage());
            }
        });
    }

    public void deleteOrganization(int id){
        Call<Author> call = authorService.deleteOrganization(id);
        call.enqueue(new Callback<Author>() {
            @Override
            public void onResponse(Call<Author> call, Response<Author> response) {
                if(response.isSuccessful()){
                    /**/
                    Toast.makeText(AuthorActivity.this, "Organization deleted successfully!", Toast.LENGTH_SHORT).show();
                    /**/
                }
            }

            @Override
            public void onFailure(Call<Author> call, Throwable t) {
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
                    Toast.makeText(AuthorActivity.this, "Crud created successfully!", Toast.LENGTH_SHORT).show();
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