package com.org.omicron.haporiapplication.restAPI.models;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class DBServices {

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
