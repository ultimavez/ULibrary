package com.dlince.library.organizations;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.Toast;

import com.dlince.library.organizations.model.Article;
import com.dlince.library.organizations.model.Crud;
import com.dlince.library.organizations.model.Author;
import com.dlince.library.organizations.model.Type;
import com.dlince.library.organizations.remote.APIUtils;
import com.dlince.library.organizations.remote.ArticleService;
import com.dlince.library.organizations.remote.CrudService;
import com.dlince.library.organizations.remote.AuthorService;
import com.dlince.library.organizations.remote.TypeService;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    LinearLayout Au,Ar,Ty;

    Button btnAddUser;
    Button btnGetUsersList;
    ListView listView;

    Button btnAddArticle;
    Button btnGetArticlesList;
    ListView listViewAr;

    Button btnAddType;
    Button btnGetTypesList;
    ListView listViewTy;

    AuthorService authorService;
    List<Author> list = new ArrayList<Author>();

    ArticleService articleService;
    List<Article> list2 = new ArrayList<Article>();

    TypeService typeService;
    List<Type> list3 = new ArrayList<Type>();

    CrudService crudService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Au = (LinearLayout) findViewById(R.id.Au);
        Ar = (LinearLayout) findViewById(R.id.Ar);
        Ty = (LinearLayout) findViewById(R.id.Ty);

        findViewById(R.id.btnAuthor).setOnClickListener(this);
        findViewById(R.id.btnArticle).setOnClickListener(this);
        findViewById(R.id.btnType).setOnClickListener(this);




        setTitle("Grupo01 CRUD");

        btnAddUser = (Button) findViewById(R.id.btnAddUser);
        btnGetUsersList = (Button) findViewById(R.id.btnGetUsersList);
        listView = (ListView) findViewById(R.id.listView);
        authorService = APIUtils.getOrganizationService();

        btnAddArticle = (Button) findViewById(R.id.btnAddArticle);
        btnGetArticlesList = (Button) findViewById(R.id.btnGetArticlesList);
        listViewAr = (ListView) findViewById(R.id.listViewAr);
        articleService = APIUtils.getArticleService();

        btnAddType = (Button) findViewById(R.id.btnAddType);
        btnGetTypesList = (Button) findViewById(R.id.btnGetTypesList);
        listViewTy = (ListView) findViewById(R.id.listViewTy);
        typeService = APIUtils.getTypeService();

        //getOrganizationsList();

        crudService = APIUtils.getCrudService();

        btnGetUsersList.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //get organizations list
                Crud z = new Crud();
                z.setUser_id(2);
                z.setType("get");
                z.setEntity("authors");
                z.setBatch(1);
                getOrganizationsList();
                createCrud(z);
            }
        });

        btnAddUser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, AuthorActivity.class);
                intent.putExtra("organization_name", "");
                startActivity(intent);
            }
        });

        btnGetArticlesList.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Crud z = new Crud();
                z.setUser_id(2);
                z.setType("get");
                z.setEntity("articles");
                z.setBatch(1);
                getArticlesList();
                createCrud(z);
            }
        });

        btnAddArticle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, ArticleActivity.class);
                intent.putExtra("article_title", "");
                startActivity(intent);
            }
        });

        btnGetTypesList.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Crud z = new Crud();
                z.setUser_id(2);
                z.setType("get");
                z.setEntity("types");
                z.setBatch(1);
                getTypesList();
                createCrud(z);
            }
        });

        btnAddType.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, TypeActivity.class);
                intent.putExtra("type_name", "");
                startActivity(intent);
            }
        });
    }

    public void getOrganizationsList(){
        Call<List<Author>> call = authorService.getOrganizations();
        call.enqueue(new Callback<List<Author>>() {
            @Override
            public void onResponse(Call<List<Author>> call, Response<List<Author>> response) {
                if(response.isSuccessful()){
                    list = response.body();
                    listView.setAdapter(new AuthorAdapter(MainActivity.this, R.layout.list_organization, list));
                }
            }

            @Override
            public void onFailure(Call<List<Author>> call, Throwable t) {
                Log.e("ERROR: ", t.getMessage());
            }
        });
    }

    public void getArticlesList(){
        Call<List<Article>> call = articleService.getArticles();
        call.enqueue(new Callback<List<Article>>() {
            @Override
            public void onResponse(Call<List<Article>> call, Response<List<Article>> response) {
                if(response.isSuccessful()){
                    list2 = response.body();
                    listViewAr.setAdapter(new ArticleAdapter(MainActivity.this, R.layout.list_article, list2));
                }
            }

            @Override
            public void onFailure(Call<List<Article>> call, Throwable t) {
                Log.e("ERROR: ", t.getMessage());
            }
        });
    }

    public void getTypesList(){
        Call<List<Type>> call = typeService.getTypes();
        call.enqueue(new Callback<List<Type>>() {
            @Override
            public void onResponse(Call<List<Type>> call, Response<List<Type>> response) {
                if(response.isSuccessful()){
                    list3 = response.body();
                    listViewTy.setAdapter(new TypeAdapter(MainActivity.this, R.layout.list_type, list3));
                }
            }

            @Override
            public void onFailure(Call<List<Type>> call, Throwable t) {
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
                    Toast.makeText(MainActivity.this, "Crud created successfully!", Toast.LENGTH_SHORT).show();
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
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.btnAuthor:
                if(Au.getVisibility() == View.GONE){
                    Au.setVisibility(View.VISIBLE);
                    Ar.setVisibility(View.GONE);
                    Ty.setVisibility(View.GONE);
                }else {
                    Au.setVisibility(View.GONE);
                }break;
            case R.id.btnArticle:
                if(Ar.getVisibility() == View.GONE){
                    Ar.setVisibility(View.VISIBLE);
                    Au.setVisibility(View.GONE);
                    Ty.setVisibility(View.GONE);
                }else {
                    Ar.setVisibility(View.GONE);
                }break;
            case R.id.btnType:
                if(Ty.getVisibility() == View.GONE){
                    Ty.setVisibility(View.VISIBLE);
                    Ar.setVisibility(View.GONE);
                    Au.setVisibility(View.GONE);
                }else {
                    Ty.setVisibility(View.GONE);
                }break;
        }
    }
}