package com.DMC99;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Scanner;

public class CustomerProfInterface {
    private static String Filename;
    boolean exit;
    private static String ID;
    CustomerProfDB file;
    ArrayList<CustomerProf> new_list = new ArrayList<>(); //A array list play as a memory for a session.



    public CustomerProfInterface(String filename){ //Take a filename as input, initialize the database
        Filename = filename;
        file = new CustomerProfDB(Filename);
    }


    public static void main(String[] args) { //Main, run the menu, display the option for user.
        System.out.println("File pathname to import OR name to Create: ");
        Scanner input = new Scanner(System.in);
        Filename = input.nextLine();
        System.out.println("Please Enter Your AdminID: ");
        Scanner ad = new Scanner(System.in);
        ID = ad.nextLine();
        CustomerProfInterface menu = new CustomerProfInterface(Filename);
        menu.runMenu();
    }

    public void runMenu(){//run the menu
        printHeader();
        while (!exit){
            printMenu();
            int choice = getMenuChoice();
            performAction(choice);
        }

    }

    public void printHeader(){ //Header
        System.out.println("+-------------------------------------------+");
        System.out.println("|       CustomerProf Management System      |");
        System.out.println("|           Deluxe Version 1.1              |");
        System.out.println("+-------------------------------------------+");

    }

    public void printMenu(){ //Menu
        displayHeader("Command List");
        System.out.println("1) Enter a New Customer");
        System.out.println("2) Delete a Customer");
        System.out.println("3) Search a Customer");
        System.out.println("4) Profile Modification");
        System.out.println("5) Display All Profiles");
        System.out.println("6) Write To Database");
        System.out.println("0) Exit");
    }

    public void printCusMenu(){ //Customer info modification menu
        displayHeader("Customer Profile Attributes");
        System.out.println("1) Address");
        System.out.println("2) Phone Number");
        System.out.println("3) Status");
        System.out.println("4) Use");
        System.out.println("5) Vehicle Information");
    }

    public void printCarMenu(){ //Car info modification menu
        displayHeader("Vehicle Profile Attributes");
        System.out.println("1) Model");
        System.out.println("2) Year");
        System.out.println("3) Type");
        System.out.println("4) Method");
    }

    public void displayHeader(String message){ //Header Function, make it looks cool
        System.out.println();
        int width = message.length() + 30;
        StringBuilder sb = new StringBuilder();
        sb.append("+");
        sb.append("-".repeat(Math.max(0, width)));
        sb.append("+");
        System.out.println(sb);
        System.out.println("|               " + message + "               |");
        System.out.println(sb);
    }

    public int getCusChoice(){ //Get the Choice from user when making modification.

        Scanner key = new Scanner(System.in);
        int choice = -1;
        do{
            System.out.println( "Please Enter Your Choice: ");
            try{
                choice = Integer.parseInt(key.nextLine());
            }catch(NumberFormatException e){
                System.out.println("Invalid Command, Please Use Only Number");
            }
            if (choice < 0|| choice > 5){
                System.out.println("Invalid Command Outside of Range");
            }
        } while (choice < 0 || choice > 5);
        return choice;
    }

    public int getMenuChoice(){  //get choice from user in the menu.

        Scanner key = new Scanner(System.in);
        System.out.println("(ID Check) Please Enter Your AdminID: ");
        Scanner id = new Scanner(System.in);
        String id_check = id.nextLine();
        while(!Objects.equals(ID, id_check)){
            System.out.println("Alert! AdminID mismatch, System Shut Down.");
            System.exit(0);//check adminID
        }

        int choice = -1;
        do{
            System.out.println( " Please Enter Your Choice: ");
            try{
                choice = Integer.parseInt(key.nextLine());
            }catch(NumberFormatException e){
                System.out.println("Invalid Command, Please Use Only Number");
            }
            if (choice < 0|| choice > 6){
                System.out.println("Invalid Command Outside of Range");
            }
        } while (choice < 0 || choice > 6);
        return choice;

    }

    private String askQuestion(String question, List<String> answers) { //get the user input
        String response = "";
        Scanner keyboard = new Scanner(System.in);
        boolean choices = (answers != null) && answers.size() != 0;
        boolean firstRun = true;
        do {
            if (!firstRun) {
                System.out.println("Invalid selection. Please try again.");
            }
            System.out.print(question);
            if (choices) {
                System.out.print("(");
                for (int i = 0; i < answers.size() - 1; ++i) {
                    System.out.print(answers.get(i) + "/");
                }
                System.out.print(answers.get(answers.size() - 1));
                System.out.print("): ");
            }
            response = keyboard.nextLine();
            firstRun = false;
            if (!choices) {
                break;
            }
        } while (!answers.contains(response));
        return response;
    }

    public void CarperformAction(int choice, CustomerProf cp){ //Choice handler for car info modification.
        switch (choice) {
            case 1 -> {
                String new_model = askQuestion("New Model: ", null);
                cp.getVehicleInfo().updateModel(new_model);
            }
            case 2 -> {
                String new_year = askQuestion("New Year: ", null);
                cp.getVehicleInfo().updateYear(new_year);
            }
            case 3 -> {
                String new_type = askQuestion("New Type: ", null);
                cp.getVehicleInfo().updateType(new_type);
            }
            case 4 -> {
                String new_method = askQuestion("New Method: ", null);
                cp.getVehicleInfo().updateMethod(new_method);
            }
        }
    }

    public void CusperformAction(int choice, CustomerProf cp) { //Choice handler for Customer Prof modification.
        switch (choice) {
            case 1 -> {
                String new_ad = askQuestion("New Address: ", null);
                cp.updateAddress(new_ad);
            }
            case 2 -> {
                String new_phone = askQuestion("New Phone: ", null);
                cp.updatePhone(new_phone);
            }
            case 3 -> {
                String new_status = askQuestion("New Status: ", null);
                cp.updateStatus(new_status);
            }
            case 4 -> {
                String new_use = askQuestion("New Use: ", null);
                cp.updateUse(new_use);
            }
            case 5 -> {
                printCarMenu();
                int carchoice = getCusChoice();
                CarperformAction(carchoice, cp);
            }
        }
    }

    public void performAction(int choice){ //Choice handler for menu options.
        switch (choice) {
            case 0 -> {
                System.out.println("System Shut Down, Thank You!");
                System.exit(0);
            }
            case 1 -> {
                CustomerProf cus = createNewCustomerProf(ID);
                file.customerList.add(cus);
            }
            case 2 -> deleteCus(ID);
            case 3 -> findCustomerProf(ID);
            case 4 -> {
                String LN = askQuestion("Last Name: ", null);
                CustomerProf CP = file.findProfile(ID, LN);
                updateCustomerProf(CP);
            }
            case 5 -> displayAllCustomerProf();
            case 6 -> writeToDB();
        }
    }

    public CustomerProf createNewCustomerProf(String adminID){ //Function to display the requirement info and create a new CustomerInfo object.
        displayHeader("Customer Profile");
        String FN = askQuestion("First Name: ", null);
        String LN = askQuestion("Last Name: ",null);
        String PH = askQuestion("Phone Number: ", null);
        String AD = askQuestion("Address:",null);
        Float IC = Float.parseFloat(askQuestion("Income: ",null));
        String ST = askQuestion("Status: ", null);
        String US = askQuestion("Use: ",null);
        VehicleInfo vc = createVehicleInfo();
        return new CustomerProf(adminID,FN,LN,PH,AD,IC,ST,US,vc);

    }

    public VehicleInfo createVehicleInfo() {// create a new vehilcleinfo object, added to the new CustomerInfo object.
        displayHeader("Vehicle Information");
        String MD = askQuestion("Model: ", null);
        String YR = askQuestion("Year: ", null);
        String TP = askQuestion("Type: ", null);
        String ME = askQuestion("Method: ", null);
        return new VehicleInfo(MD,YR,TP,ME);
    }

    public void deleteCus(String ID){  // Function to delete a customer from the file by ask user the ID and Lastname
        displayHeader("Delete Customer");
        String LN = askQuestion("Customer Last Name: ",null);
        if (file.deleteProfile(ID,LN)){
            System.out.println("Delete Complete");
        }else{
            System.out.println(file.numCustomer);
            System.out.println("Fail to Delete");
        }

    }

    public void findCustomerProf(String adminID){ //Find a customer and display it by ask the user for ID and Lastname.
        displayHeader("Find a Customer");
        String LN = askQuestion("Customer Last Name: ", null);
        CustomerProf cp = file.findProfile(adminID,LN);
        displyCustomerProf(cp);
    }

    public void displyCustomerProf(CustomerProf cp){// Display a CustomerProf object in proper format.
        System.out.println("adminID: "+ cp.getadminID());
        System.out.println("First Name: "+ cp.getFirstName());
        System.out.println("Last Name: "+ cp.getLastName());
        System.out.println("Address: "+cp.getAddress());
        System.out.println("Phone Number: "+cp.getPhone());
        System.out.println("Income: "+cp.getIncome());
        System.out.println("Status: "+cp.getStatus());
        System.out.println("Use: "+cp.getUse());
        System.out.println("Vehicle Model: "+ cp.getVehicleInfo().getModel());
        System.out.println("Vehicle Year: "+ cp.getVehicleInfo().getYear());
        System.out.println("Vehicle Type: "+ cp.getVehicleInfo().getType());
        System.out.println("Vehicle Method: "+ cp.getVehicleInfo().getMethod());
    }

    public void updateCustomerProf(CustomerProf cp){ //update infomation for customer prof
        printCusMenu();
        int Cuschoice = getCusChoice();
        CusperformAction(Cuschoice,cp);
    }

    public void displayAllCustomerProf(){ //Display all customerProf object associated with one specific adminID.
        displayHeader("Display All Profile");
        String adminid = askQuestion("Enter Your AdminID: ", null);
        CustomerProf profile;
        for (CustomerProf now : file.customerList) {
            if (now.getadminID().equals(adminid)) {
                profile = now;
                displyCustomerProf(profile);
            }else{
                System.out.println("No Profile Found!");
            }
        }
    }
    public void writeToDB(){ //Write all CustomerProf object that currently in the memory to the database.
        file.writeAllCustomer();
    }

    }


