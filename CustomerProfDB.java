package com.DMC99;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class CustomerProfDB{

    int numCustomer;
    int CurrentCustomerIndex;
    String fileName;
    ArrayList<CustomerProf> customerList = new ArrayList<>();


    public CustomerProfDB(String file){  //Constructor of the database, initialize the file with input of filename.
        fileName = file;
        initializeDatabase(fileName);
    }
    public void insertNewProfile(CustomerProf cp){  //Insert a new customerProf object into the list.
        customerList.add(cp);
        numCustomer +=1;
    }

    public CustomerProf findProfile(String adminID, String lastname){ //Find a CustomerProf object in the list by AdminID and LastName.
        CustomerProf profile = null;
        for (CustomerProf now : customerList) {
            if (now.getadminID().equals(adminID) && now.getLastName().equals(lastname)) {
                 profile = now;
            }
        }
        return profile;
    }
    public Boolean deleteProfile(String adminid, String lastName){ //Delete a CustomerProf Object from the list by AdminID and LastName
        CustomerProf Now = findProfile(adminid, lastName);
        if(numCustomer == 0){
            return false;
        }
        if (Now.getadminID().equals(adminid) | Now.getLastName().equals(lastName)){
            customerList.remove(Now);
            numCustomer -= 1;
            return true;

        }else{
            return false;
        }
    }
    public void writeAllCustomer() { //Write all the CustomerProf object that in the list to the file.
        try{
            FileWriter file = new FileWriter(fileName,false);
            for (CustomerProf customerProf : customerList) {
                String insert = "\n" + customerProf.getadminID() + "\n" + customerProf.getFirstName() + "\n" + customerProf.getLastName() + "\n" + customerProf.getAddress() + "\n" +
                        customerProf.getPhone() + "\n" + customerProf.getIncome() + "\n" + customerProf.getStatus() + "\n" + customerProf.getUse() + "\n" +
                        customerProf.getVehicleInfo().getModel() + "\n" + customerProf.getVehicleInfo().getYear() + "\n" + customerProf.getVehicleInfo().getType() + "\n" +
                        customerProf.getVehicleInfo().getMethod();
                file.write(insert);
            }
            file.close();
        } catch (Exception e) {
            System.out.println("Error: Path is either incorrect or does not exist");
        }
    }
    public void initializeDatabase(String FileName){ //The Constuctor, take a file as input, if it exist, read it. If it is not, create it.
        try{
            File cp = new File(FileName);
            if (cp.createNewFile()){
                System.out.println(FileName + " Have Created!");
            }else{
                Scanner s = new Scanner(new File(FileName));
                ArrayList<String> list = new ArrayList<>();
                while (s.hasNext()){
                    list.add(s.nextLine());
                }
                s.close();
                readtxt(list);
                System.out.println("File Already Created");
            }
        } catch (IOException error){
            System.out.println("Error!");
            error.printStackTrace();
        }
    }

    public void readtxt(ArrayList<String> txtlist){

        int n = txtlist.size();

        for(int i = 1; (i+11) < n; i+=12){
            String ID = txtlist.get(i);
            String FN = txtlist.get(i+1);
            String LN = txtlist.get(i+2);
            String AD = txtlist.get(i+3);
            String PN = txtlist.get(i+4);
            Float IC = Float.parseFloat(txtlist.get(i+5));
            String ST = txtlist.get(i+6);
            String US = txtlist.get(i+7);
            String MD = txtlist.get(i+8);
            String YE = txtlist.get(i+9);
            String TP = txtlist.get(i+10);
            String ME = txtlist.get(i+11);
            VehicleInfo car = new VehicleInfo(MD,YE,TP,ME);
            CustomerProf cp = new CustomerProf(ID,FN,LN,AD,PN,IC,ST,US,car);
            customerList.add(cp);
            numCustomer +=1;
        }
        System.out.println("File Loading Complete");

    }


    public CustomerProf findFirstProfile(){ //find the first profile in the list.
        return customerList.get(0);

    }

    public CustomerProf findNextProfile(){//find the next profile in the list.
        return customerList.get(CurrentCustomerIndex++);
    }


    public String getFileName(){// get the name of thr file that we currently handle.
        return fileName;
    }
}

