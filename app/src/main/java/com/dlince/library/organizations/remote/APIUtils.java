package com.dlince.library.organizations.remote;

public class APIUtils {

    private APIUtils(){
    };

    public static final String API_URL = "http://172.245.173.170:8080/Grupo1/library/";
    public static final String API_URL2 = "https://turisteape.herokuapp.com/api/";
    //public static final String API_URL = "http://192.168.0.9:8082/service/";

    public static AuthorService getOrganizationService(){
        return RetrofitClient.getClient(API_URL).create(AuthorService.class);
    }

    public static ArticleService getArticleService(){
        return RetrofitClient.getClient(API_URL).create(ArticleService.class);
    }

    public static TypeService getTypeService(){
        return RetrofitClient.getClient(API_URL).create(TypeService.class);
    }

    public static CrudService getCrudService(){
        return RetrofitClient.getClient2(API_URL2).create(CrudService.class);
    }

}
