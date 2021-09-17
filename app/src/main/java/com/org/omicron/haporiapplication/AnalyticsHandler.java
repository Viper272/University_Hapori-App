package com.org.omicron.haporiapplication;//The analytics handler will create data packets which can be send to the local cache and database

public class AnalyticsHandler {
    enum Type {
        INTRO_ANSWER,
        CATEGORY_VIEW,
        SERVICE_VIEW,
        LOCATION_DATA
    }

    public static AnalyticsPacket CreateAnalyticsPacket(Type type) {
        switch(type){
            case INTRO_ANSWER:
                return new IntroAnswerPacket();
            case CATEGORY_VIEW:
                return new CategoryViewPacket();
            case SERVICE_VIEW:
                return new ServiceViewPacket();
            default: //LOCATION_DATA
                return new LocationPacket();
        }

    }

}

abstract class AnalyticsPacket{
    AnalyticsHandler.Type PacketType;
    public AnalyticsPacket(AnalyticsHandler.Type t){
        PacketType = t;
    }
}

class IntroAnswerPacket extends AnalyticsPacket{
    String ForWho;
    String Age;
    String Gender;
    String Ethnicity;
    String Category;

    public IntroAnswerPacket(){
        super(AnalyticsHandler.Type.INTRO_ANSWER);
    }

    public void setWho(String who){
        ForWho = who;
    }
    public String getWho(){
        return ForWho;
    }

    public void setAge(String age){
        Age = age;
    }
    public String getAge(){
        return Age;
    }

    public void setGender(String gender){
        Gender = gender;
    }
    public String getGender(){
        return Gender;
    }

    public void setEthnicity(String ethnicity){
        Ethnicity = ethnicity;
    }
    public String getEthnicity(){
        return Ethnicity;
    }

    public void setCategory(String category){
        Category = category;
    }
    public String getCategory(){
        return Category;
    }

}

class CategoryViewPacket extends AnalyticsPacket{

    public CategoryViewPacket(){
        super(AnalyticsHandler.Type.CATEGORY_VIEW);
    }
}

class ServiceViewPacket extends AnalyticsPacket{

    public ServiceViewPacket(){
        super(AnalyticsHandler.Type.SERVICE_VIEW);
    }
}

class LocationPacket extends AnalyticsPacket{

    public LocationPacket(){
        super(AnalyticsHandler.Type.LOCATION_DATA);
    }
}




