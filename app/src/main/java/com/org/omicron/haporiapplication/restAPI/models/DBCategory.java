package com.org.omicron.haporiapplication.restAPI.models;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

public class DBCategory implements Parcelable {

    @SerializedName("name")
    private String name;

    public DBCategory(String serviceName) {
        this.name = serviceName;
    }
    protected DBCategory(Parcel in) {
        name = in.readString();
    }

    //Getters
    public String getCategoryName() {
        return name;
    }


    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(name);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<DBCategory> CREATOR = new Creator<DBCategory>() {
        @Override
        public DBCategory createFromParcel(Parcel in) {
            return new DBCategory(in);
        }

        @Override
        public DBCategory[] newArray(int size) {
            return new DBCategory[size];
        }
    };
}
