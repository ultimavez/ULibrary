package com.dlince.library.organizations;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.icu.lang.UScript;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.dlince.library.organizations.model.Article;
import com.dlince.library.organizations.model.Crud;
import com.dlince.library.organizations.remote.APIUtils;
import com.dlince.library.organizations.remote.ArticleService;
import com.dlince.library.organizations.remote.CrudService;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginActivity extends AppCompatActivity {

    private EditText Name;
    private EditText Password;
    private TextView Info;
    private Button Login;
    private int counter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        setTitle("Login");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        Name = (EditText) findViewById(R.id.etName);
        Password = (EditText) findViewById(R.id.etPassword);
        Info = (TextView)findViewById(R.id.tvInfo);
        Login=(Button)findViewById(R.id.btnLogin);

        Info.setText("Qty of Attemps 5");

        Login.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                validate(Name.getText().toString(),Password.getText().toString());
            }
        });



    }

    private void validate(String userName,String userPassword){
    if ((userName.equals("Admin"))  && (userPassword.equals("12345678"))){
        Intent intent = new Intent(LoginActivity.this,MainActivity.class);
        startActivity(intent);
    }
    else {
        counter--;
        Info.setText("Qty of Attemps:"+ String.valueOf(counter));
        if(counter == 0){
            Login.setEnabled(false);
        }
    }
}
}