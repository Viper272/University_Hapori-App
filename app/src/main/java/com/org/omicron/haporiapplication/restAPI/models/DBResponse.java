package com.org.omicron.haporiapplication.restAPI.models;

import com.google.gson.annotations.SerializedName;
import com.org.omicron.haporiapplication.serviceScroll.ServicesListItem;

import java.util.List;

public class DBResponse {

    @SerializedName("success")
    private boolean success;
    @SerializedName("message")
    private String message;
    @SerializedName("data")
    private List<ServicesListItem> data;

    public DBResponse(boolean success, String message, List<ServicesListItem> data)
    {
        this.success = success;
        this.message = message;
        this.data = data;
    }

    public boolean isSuccess() {
        return success;
    }
    public String getMessage() {
        return message;
    }
    public List<ServicesListItem> getData() {
        return data;
    }
}
