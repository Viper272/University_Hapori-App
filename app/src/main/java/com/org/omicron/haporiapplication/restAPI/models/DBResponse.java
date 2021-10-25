package com.org.omicron.haporiapplication.restAPI.models;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class DBResponse<T> {

    @SerializedName("success")
    private boolean success;
    @SerializedName("message")
    private String message;
    @SerializedName("data")
    private List<T> data;

    public DBResponse(boolean success, String message, List<T> data)
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
    public List<T> getData() {
        return data;
    }
}



//BACKUP
//public class DBResponse {
//
//    @SerializedName("success")
//    private boolean success;
//    @SerializedName("message")
//    private String message;
//    @SerializedName("data")
//    private List<DBServices> data;
//
//    public DBResponse(boolean success, String message, List<DBServices> data)
//    {
//        this.success = success;
//        this.message = message;
//        this.data = data;
//    }
//
//    public boolean isSuccess() {
//        return success;
//    }
//    public String getMessage() {
//        return message;
//    }
//    public List<DBServices> getData() {
//        return data;
//    }
//}
