package com.org.omicron.haporiapplication.serviceScroll;

public class ServicesListItem {
    private String serviceID;
    private String serviceName;
    private String shortDesc;
    private String fullDesc;

    public ServicesListItem(String serviceID, String serviceName, String shortDesc) {
        this.serviceID = serviceID;
        this.serviceName = serviceName;
        this.shortDesc = shortDesc;
    }

    //Getters
    public String getServiceName() {
        return serviceName;
    }
    public String getShortDesc() {
        return shortDesc;
    }
    public String getFullDesc() {
        return fullDesc;
    }

    //Setters
    public void setServiceName(String serviceName) {
        this.serviceName = serviceName;
    }
    public void setShortDesc(String shortDesc) {
        this.shortDesc = shortDesc;
    }
    public void setFullDesc(String fullDesc) {
        this.fullDesc = fullDesc;
    }
}
