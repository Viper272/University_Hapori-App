package com.org.omicron.haporiapplication.restAPI.models;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

public class DBServices implements Parcelable {

    @SerializedName("_id")
    private String ID;

    @SerializedName("name")
    private String name;
    @SerializedName("shortDescription")
    private String shortDescription;
    @SerializedName("longDescription")
    private String fullDescription;

    @SerializedName("contact")
    private List<DBServiceContactDetails> contactDetails;

    @SerializedName("tags")
    private ArrayList<String> tags;

    public ArrayList<String> getTags() {
        return tags;
    }


    public DBServices(String ID, String serviceName, String shortDesc, String fullDescription, List<DBServiceContactDetails> contactDetails) {
        this.ID = ID;

        this.name = serviceName;
        this.shortDescription = shortDesc;
        this.fullDescription = fullDescription;
        this.contactDetails = contactDetails;
    }

    //Constructor for no database (Default for development)
    public DBServices(String name, String shortDescription) {
        this.name = name;
        this.shortDescription = shortDescription;
    }

    //Getters
    public String getServiceName() {
        return name;
    }
    public String getShortDesc() {
        return shortDescription;
    }
    public String getFullDesc() {
        return fullDescription;
    }

    //Setters
    public void setServiceName(String serviceName) {
        this.name = serviceName;
    }
    public void setShortDesc(String shortDesc) {
        this.shortDescription = shortDesc;
    }
    public void setFullDesc(String fullDesc) {
        this.fullDescription = fullDesc;
    }


    protected DBServices(Parcel in) {
        ID = in.readString();
        name = in.readString();
        shortDescription = in.readString();
        fullDescription = in.readString();
        tags = in.createStringArrayList();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(ID);
        dest.writeString(name);
        dest.writeString(shortDescription);
        dest.writeString(fullDescription);
        dest.writeStringList(tags);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<DBServices> CREATOR = new Creator<DBServices>() {
        @Override
        public DBServices createFromParcel(Parcel in) {
            return new DBServices(in);
        }

        @Override
        public DBServices[] newArray(int size) {
            return new DBServices[size];
        }
    };

    class DBServiceContactDetails {
        @SerializedName("_id")
        private String ID;
        @SerializedName("phone")
        private String phone;
        @SerializedName("email")
        private String email;

        public DBServiceContactDetails(String ID, String phone, String email) {
            this.ID = ID;
            this.phone = phone;
            this.email = email;
        }

        public String getPhone() {
            return phone;
        }
        public String getEmail() {
            return email;
        }
    }
}
