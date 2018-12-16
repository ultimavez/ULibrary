package com.dlince.library.organizations.remote;
import com.dlince.library.organizations.model.Type;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;

public interface TypeService {

    @GET("types/show/")
    Call<List<Type>> getTypes();

    @POST("types/add/")
    Call<Type> addType(@Body Type type);

    @PUT("types/{id}")
    Call<Type> updateType(@Path("id") int id, @Body Type type);

    @DELETE("types/{id}")
    Call<Type> deleteType(@Path("id") int id);
}
