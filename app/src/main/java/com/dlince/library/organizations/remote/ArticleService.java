package com.dlince.library.organizations.remote;

import com.dlince.library.organizations.model.Article;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;

public interface ArticleService {

    @GET("article/show/")
    Call<List<Article>> getArticles();

    @POST("article/add/")
    Call<Article> addArticle(@Body Article article);

    @PUT("article/{id}")
    Call<Article> updateArticle(@Path("id") int id, @Body Article article);

    @DELETE("article/{id}")
    Call<Article> deleteArticle(@Path("id") int id);
}
