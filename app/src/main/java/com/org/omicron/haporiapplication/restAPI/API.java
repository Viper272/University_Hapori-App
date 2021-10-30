package com.org.omicron.haporiapplication.restAPI;

import com.org.omicron.haporiapplication.restAPI.models.DBCategory;
import com.org.omicron.haporiapplication.restAPI.models.DBResponse;
import com.org.omicron.haporiapplication.restAPI.models.DBServices;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface API {
    String BASE_URL = "http://localhost:port";
    String INTERNAL_TEST_URL = "http://192.168.1.13:12346";

    //region Service Methods
    @GET("/services")
    Call<DBResponse<DBServices>> getAllServices();
    @GET("/services/{service}")
    Call<DBResponse<DBServices>> getServiceByID(@Path("service") String serviceID);
    @GET("/services/category/{category}")
    Call<DBResponse<DBServices>> getServiceByCategory(@Path("category") String categoryID);
    //endregion

    //region Category Methods
    @GET("/categories")
    Call<DBResponse<DBCategory>> getAllCategories();
    //endregion
}