package com.dlince.library.organizations.remote;

import com.dlince.library.organizations.model.Crud;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface CrudService {
    @POST("cruds/create/")
    Call<Crud> createCrud(@Body Crud crud);
}
