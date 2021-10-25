package com.org.omicron.haporiapplication.serviceScroll;

import com.google.gson.annotations.SerializedName;

public class ServicesListItem {

    @SerializedName("name")
    private String name;

    @SerializedName("shortDescription")
    private String shortDescription;
    private String fullDescription;

    public ServicesListItem(String serviceName, String shortDesc) {
        this.name = serviceName;
        this.shortDescription = shortDesc;
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
}
