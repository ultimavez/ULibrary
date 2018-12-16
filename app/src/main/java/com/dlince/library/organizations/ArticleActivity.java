package com.dlince.library.organizations;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
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

import com.dlince.library.organizations.model.Article;
import com.dlince.library.organizations.model.Crud;
import com.dlince.library.organizations.remote.APIUtils;
import com.dlince.library.organizations.remote.ArticleService;
import com.dlince.library.organizations.remote.CrudService;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ArticleActivity extends AppCompatActivity {

    CrudService crudService;
    ArticleService articleService;
    EditText edtArId;
    EditText edtTitle;
    EditText edtPages;
    EditText edtYear;
    EditText edtTypes_id;
    EditText edtEditorial_id;
    Button btnSave;
    Button btnDel;
    TextView txtArId;
    CheckBox checkbox1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_article);

        setTitle("Articles");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        txtArId = (TextView) findViewById(R.id.txtArId);
        edtArId = (EditText) findViewById(R.id.edtArId);
        edtTitle = (EditText) findViewById(R.id.edtTitle);
        edtPages = (EditText) findViewById(R.id.edtPages);
        edtYear = (EditText) findViewById(R.id.edtYear);
        edtTypes_id = (EditText) findViewById(R.id.edtType_id);
        checkbox1 = (CheckBox) findViewById(R.id.checkbox1);
        edtEditorial_id = (EditText) findViewById(R.id.edtEditorial_id);
        btnSave = (Button) findViewById(R.id.btnSave);
        btnDel = (Button) findViewById(R.id.btnDel);


        /**/
        articleService = APIUtils.getArticleService();
        crudService = APIUtils.getCrudService();
        /**/

        Bundle extras = getIntent().getExtras();
        final String articleId = extras.getString("article_id");
        String articleTitle = extras.getString("article_title");
        String articlePages = extras.getString("article_pages");
        String articleYear = extras.getString("article_year");
        String articleType_id = extras.getString("article_type_id");
        String articleStatus = extras.getString("article_status");
        String articleEditorial_id = extras.getString("article_editoral_id");
        /**/
        edtArId.setText(articleId);
        edtTitle.setText(articleTitle);
        edtPages.setText(articlePages);
        edtYear.setText(articleYear);
        edtTypes_id.setText(articleType_id);
        edtEditorial_id.setText(articleEditorial_id);

        if(articleId != null && articleId.trim().length() > 0 ){
            edtArId.setFocusable(false);
            /**/
            if( Integer.parseInt(articleStatus)==1 ) {
                checkbox1.setChecked(true);
            }else{
                checkbox1.setChecked(false);
            }
            /**/
        } else {
            txtArId.setVisibility(View.INVISIBLE);
            edtArId.setVisibility(View.INVISIBLE);
            btnDel.setVisibility(View.INVISIBLE);
            checkbox1.setChecked(true);
        }

        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                /**/
                if (edtTitle.getText().toString() == null || edtTitle.getText().toString().trim().length() == 0) {
                    Toast.makeText(ArticleActivity.this, "Article title is required!", Toast.LENGTH_SHORT).show();
                    edtTitle.setText("");
                    edtTitle.requestFocus();
                    edtPages.setText("");
                    edtPages.requestFocus();
                    edtYear.setText("");
                    edtYear.requestFocus();
                    edtTypes_id.setText("");
                    edtTypes_id.requestFocus();
                    edtEditorial_id.setText("");
                    edtEditorial_id.requestFocus();
                }else {
                    Article u = new Article();
                    Crud z = new  Crud();
                    //Toast.makeText(OrganizationActivity.this, edtUsername.getText().toString(), Toast.LENGTH_SHORT).show();
                    u.setTitle(edtTitle.getText().toString());
                    u.setPages(edtPages.getText().toString());
                    u.setYear(edtYear.getText().toString());
                    u.setTypes_id(Integer.parseInt(edtTypes_id.getText().toString()));
                    u.setEditorial_id(Integer.parseInt(edtEditorial_id.getText().toString()));
                    z.setUser_id(2);
                    z.setEntity("articles");
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
                    if(articleId != null && articleId.trim().length() > 0){
                        //update organization
                        z.setType("put");
                        updateArticle(Integer.parseInt(articleId), u);
                        createCrud(z);
                    }else {
                        //add organization
                        z.setType("post");
                        addArticle(u);
                        createCrud(z);
                    }
                }
                /**/

            }
        });

        btnDel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Crud z = new Crud();
                z.setUser_id(2);
                z.setType("delete");
                z.setEntity("articles");
                z.setBatch(1);
                createCrud(z);
                deleteArticle(Integer.parseInt(articleId));
                Toast.makeText(ArticleActivity.this, "Article deleted successfully!", Toast.LENGTH_SHORT).show();
                /**/
                Intent intent = new Intent(ArticleActivity.this, MainActivity.class);
                startActivity(intent);

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
                                z.setEntity("articles");
                                z.setBatch(1);
                                createCrud(z);
                                deleteArticle(Integer.parseInt(articleId));
                                Toast.makeText(ArticleActivity.this, "Article deleted successfully!", Toast.LENGTH_SHORT).show();
                                /**/
                                Intent intent = new Intent(ArticleActivity.this, MainActivity.class);
                                startActivity(intent);

                                break;

                            case DialogInterface.BUTTON_NEGATIVE:

                                Toast.makeText(ArticleActivity.this, "No Clicked",
                                        Toast.LENGTH_LONG).show();
                                break;
                        }
                    }
                };

                AlertDialog.Builder builder = new AlertDialog.Builder(ArticleActivity.this);
                builder.setMessage("Are you sure to delete this Article?")
                        .setPositiveButton("Yes", dialogClickListener)
                        .setNegativeButton("No", dialogClickListener).show();
            }
        });

    }

    public void addArticle(Article u){
        Call<Article> call = articleService.addArticle(u);
        call.enqueue(new Callback<Article>() {
            @Override
            public void onResponse(Call<Article> call, Response<Article> response) {
                if(response.isSuccessful()){
                    /**/
                    Toast.makeText(ArticleActivity.this, "Article created successfully!", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(ArticleActivity.this, MainActivity.class);
                    startActivity(intent);
                    /**/
                }
            }

            @Override
            public void onFailure(Call<Article> call, Throwable t) {
                Log.e("ERROR: ", t.getMessage());
            }
        });
    }

    public void updateArticle(int id, Article u){
        Call<Article> call = articleService.updateArticle(id, u);
        call.enqueue(new Callback<Article>() {
            @Override
            public void onResponse(Call<Article> call, Response<Article> response) {
                if(response.isSuccessful()){
                    /**/
                    Toast.makeText(ArticleActivity.this, "Article updated successfully!", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(ArticleActivity.this, MainActivity.class);
                    startActivity(intent);
                    /**/
                }
            }

            @Override
            public void onFailure(Call<Article> call, Throwable t) {
                Log.e("ERROR: ", t.getMessage());
            }
        });
    }

    public void deleteArticle(int id){
        Call<Article> call = articleService.deleteArticle(id);
        call.enqueue(new Callback<Article>() {
            @Override
            public void onResponse(Call<Article> call, Response<Article> response) {
                if(response.isSuccessful()){
                    /**/
                    Toast.makeText(ArticleActivity.this, "Article deleted successfully!", Toast.LENGTH_SHORT).show();
                    /**/
                }
            }

            @Override
            public void onFailure(Call<Article> call, Throwable t) {
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
                    Toast.makeText(ArticleActivity.this, "Crud created successfully!", Toast.LENGTH_SHORT).show();
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
