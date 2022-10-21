package com.DMC99;

public class CustomerProf {
    String adminID;
    String firstName;
    String lastName;
    String Address;
    String Phone;
    Float Income;
    String Status;
    String Use;
    VehicleInfo vehicleInfo;

    //Constructor for Customer Profile
    public CustomerProf(String ID, String first,String last, String address, String phone, Float income,
                        String status, String use, VehicleInfo vi){
        adminID = ID;
        firstName = first;
        lastName = last;
        Phone = phone;
        Address = address;
        Income = income;
        Status = status;
        Use = use;
        vehicleInfo = vi;

    }
    public String getadminID(){
        return adminID;
    }
    public String getFirstName(){
        return firstName;
    }
    public String getLastName(){
        return lastName;
    }
    public String getPhone(){
        return Phone;
    }
    public Float getIncome(){
        return Income;
    }
    public  String getAddress(){
        return Address;
    }
    public String getStatus(){
        return Status;
    }
    public String getUse(){
        return Use;
    }
    public VehicleInfo getVehicleInfo(){ return vehicleInfo;}
    public void updatefirstName(String input){
        firstName = input;
    }
    public void updateLastName(String input){
        lastName = input;
    }
    public void updateAddress(String input) {
        Address = input;
    }
    public void updatePhone(String input){
        Phone = input;
    }
    public void updateIncome(Float input){
        Income = input;
    }
    public void updateStatus(String input){
        Status = input;
    }
    public void updateUse(String input) {Use = input;}
    public void updateVehicleInfo(VehicleInfo input){ vehicleInfo = input;}

}
