package com.DMC99;

public class VehicleInfo {
    String model;
    String year;
    String type;
    String method;


    public VehicleInfo(String md, String y, String t, String m){  //Constructor for Car Info
        model = md;
        year = y;
        type = t;
        method = m;
    }
    public String getModel(){ return model;}
    public String getYear(){return year;}
    public String getType(){return type;}
    public String getMethod(){return method;}
    public void updateModel(String input){model = input;}
    public void updateYear(String input){ year = input;}
    public void updateType(String input){ type = input;}
    public void updateMethod(String input){ method = input;}


}

