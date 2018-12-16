package com.dlince.library.organizations.remote;

import com.dlince.library.organizations.model.Author;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;

public interface AuthorService {

    @GET("author/show/")
    Call<List<Author>> getOrganizations();

    @POST("author/add/")
    Call<Author> addOrganization(@Body Author author);

    @PUT("author/{id}")
    Call<Author> updateOrganization(@Path("id") int id, @Body Author author);

    @DELETE("author/{id}")
    Call<Author> deleteOrganization(@Path("id") int id);

}
