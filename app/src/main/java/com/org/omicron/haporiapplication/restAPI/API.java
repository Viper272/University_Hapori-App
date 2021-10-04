package com.org.omicron.haporiapplication.restAPI;

import com.org.omicron.haporiapplication.restAPI.models.Services;
import com.org.omicron.haporiapplication.serviceScroll.ServicesListItem;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface API {
    String BASE_URL = "https://localhost:PORT";

    @GET("")
    Call<List<ServicesListItem>> getAllServices();
    @GET("{service}")
    Call<List<Services>> getServiceByID(@Path("service") String serviceID);

}
