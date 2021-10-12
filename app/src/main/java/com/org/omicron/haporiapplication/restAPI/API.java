package com.org.omicron.haporiapplication.restAPI;

import com.org.omicron.haporiapplication.restAPI.models.DBResponse;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface API {
    String BASE_URL = "http://localhost:port";
    String INTERNAL_TEST_URL = "NoInternalIPForyou;)";

    @GET("/services")
    Call<DBResponse> getAllServices();
    @GET("/services/{service}")
    Call<DBResponse> getServiceByID(@Path("service") String serviceID);

}
