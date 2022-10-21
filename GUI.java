package com.DMC99;


import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.util.ArrayList;
import java.util.Objects;


class gui {
    private static String Filename;
    private static String ID;
    static CustomerProfDB file;
    //A array list play as a memory for a session.


    public gui(String filename, String id) { //Take a filename as input, initialize the database
        Filename = filename;
        file = new CustomerProfDB(Filename);
        ID = id;
    }

    public static void main(String args[]) {

        //Creating the Frame
        JFrame frame = new JFrame("CustomerProf Management System");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 400);

        //Creating the MenuBar and adding components
        JMenuBar mb = new JMenuBar();
        JMenu m0 = new JMenu("Start");
        JMenu m1 = new JMenu("Edit");
        JMenu m2 = new JMenu("Find");
        mb.add(m0);
        mb.add(m1);
        mb.add(m2);
        JMenuItem m00 = new JMenuItem("Initialize Database");
        m00.addActionListener(new ImportActionListener());
        JMenuItem m11 = new JMenuItem("Add Customer");
        m11.addActionListener(new AddActionListener());
        JMenuItem m12 = new JMenuItem("Delete Customer");
        m12.addActionListener(new RemoveActionListener());
        JMenuItem m13 = new JMenuItem("Modify Customer");
        m13.addActionListener(new ModiActionListener());
        JMenuItem m14 = new JMenuItem("Write To Database");
        m14.addActionListener(new WriteActionListener());
        JMenuItem m21 = new JMenuItem("Find Customer");
        m21.addActionListener(new FindActionListener());
        JMenuItem m22 = new JMenuItem("Show All Customer");
        m22.addActionListener(new ShowActionListener());
        m0.add(m00);
        m1.add(m11);
        m1.add(m12);
        m1.add(m13);
        m1.add(m14);
        m2.add(m21);
        m2.add(m22);


        frame.getContentPane().add(BorderLayout.NORTH, mb);
        frame.setVisible(true);
    }

    static class ShowActionListener implements ActionListener{
        public void actionPerformed(ActionEvent e) {
            System.out.println("Selected: " + e.getActionCommand());
            String admin = JOptionPane.showInputDialog("Please Enter Your AdminID");
            if (!Objects.equals(admin, ID)) {
                JOptionPane.showMessageDialog(null, "ID Mismatch");
            } else {
                ArrayList<CustomerProf> lst = file.customerList;
                for (CustomerProf cp : lst){
                    if (cp.getadminID().equals(ID)) {
                        JPanel show = new JPanel();
                        show.add(new JLabel("AdminID: "));
                        show.add(new JLabel(cp.adminID));
                        show.add(new JLabel("First Name: "));
                        show.add(new JLabel(cp.firstName));
                        show.add(new JLabel("Last Name: "));
                        show.add(new JLabel(cp.lastName));
                        show.add(new JLabel("Address: "));
                        show.add(new JLabel(cp.Address));
                        show.add(new JLabel("Phone: "));
                        show.add(new JLabel(cp.Phone));
                        show.add(new JLabel("Income: "));
                        show.add(new JLabel(cp.Income.toString()));
                        show.add(new JLabel("Status: "));
                        show.add(new JLabel(cp.Status));
                        show.add(new JLabel("Use: "));
                        show.add(new JLabel(cp.Use));
                        show.add(new JLabel("Vehicle Model: "));
                        show.add(new JLabel(cp.getVehicleInfo().getModel()));
                        show.add(new JLabel("Vehicle Year: "));
                        show.add(new JLabel(cp.getVehicleInfo().getYear()));
                        show.add(new JLabel("Vehicle Type: "));
                        show.add(new JLabel(cp.getVehicleInfo().getType()));
                        show.add(new JLabel("Vehicle Method: "));
                        show.add(new JLabel(cp.getVehicleInfo().getMethod()));
                        GridLayout showlay = new GridLayout(12, 2);
                        show.setLayout(showlay);
                        JOptionPane.showInputDialog(show);
                    }

                }
            }
        }
    }

    static class FindActionListener implements ActionListener{
        public void actionPerformed(ActionEvent e) {
            System.out.println("Selected: " + e.getActionCommand());
            JTextField adminID = new JTextField(10);
            JTextField lastName = new JTextField(10);
            JPanel info = new JPanel();
            info.add(new JLabel("Associated AdminID:"));
            info.add(adminID);
            info.add(new JLabel("Customer lastName:"));
            info.add(lastName);
            GridLayout infolay = new GridLayout(2, 1);
            info.setLayout(infolay);
            String admin = JOptionPane.showInputDialog("Please Enter Your AdminID");
            if (!Objects.equals(admin, ID)) {
                JOptionPane.showMessageDialog(null, "ID Mismatch");
            } else {
                JOptionPane.showInputDialog(info);
                String LN = lastName.getText();
                String id = adminID.getText();
                CustomerProf cp = file.findProfile(id, LN);
                if (cp == null) {
                    JOptionPane.showMessageDialog(null, "File Not Found");
                } else {
                    JPanel show = new JPanel();
                    show.add(new JLabel("AdminID: "));
                    show.add(new JLabel(cp.adminID));
                    show.add(new JLabel("First Name: "));
                    show.add(new JLabel(cp.firstName));
                    show.add(new JLabel("Last Name: "));
                    show.add(new JLabel(cp.lastName));
                    show.add(new JLabel("Address: "));
                    show.add(new JLabel(cp.Address));
                    show.add(new JLabel("Phone: "));
                    show.add(new JLabel(cp.Phone));
                    show.add(new JLabel("Income: "));
                    show.add(new JLabel(cp.Income.toString()));
                    show.add(new JLabel("Status: "));
                    show.add(new JLabel(cp.Status));
                    show.add(new JLabel("Use: "));
                    show.add(new JLabel(cp.Use));
                    show.add(new JLabel("Vehicle Model: "));
                    show.add(new JLabel(cp.getVehicleInfo().getModel()));
                    show.add(new JLabel("Vehicle Year: "));
                    show.add(new JLabel(cp.getVehicleInfo().getYear()));
                    show.add(new JLabel("Vehicle Type: "));
                    show.add(new JLabel(cp.getVehicleInfo().getType()));
                    show.add(new JLabel("Vehicle Method: "));
                    show.add(new JLabel(cp.getVehicleInfo().getMethod()));
                    GridLayout showlay = new GridLayout(12, 2);
                    show.setLayout(showlay);
                    JOptionPane.showInputDialog(show);

                }
            }
        }
    }

    static class ModiActionListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            System.out.println("Selected: " + e.getActionCommand());
            JTextField adminID = new JTextField(10);
            JTextField lastName = new JTextField(10);
            JPanel info = new JPanel();
            info.add(new JLabel("Associated AdminID:"));
            info.add(adminID);
            info.add(new JLabel("Customer lastName:"));
            info.add(lastName);
            String[] changes = {"Address", "Phone", "Status", "Use", "Income", "Model", "Year", "Type", "Method"};
            info.add(new JLabel("Update: "));
            JComboBox type = new JComboBox(changes);
            info.add(type);

            GridLayout infolay = new GridLayout(3, 1);
            info.setLayout(infolay);
            String admin = JOptionPane.showInputDialog("Please Enter Your AdminID");
            if (!Objects.equals(admin, ID)) {
                JOptionPane.showMessageDialog(null, "ID Mismatch");
            } else {
                JOptionPane.showInputDialog(info);
                String LN = lastName.getText();
                String id = adminID.getText();
                int CG = type.getSelectedIndex();
                CustomerProf cp = file.findProfile(id, LN);
                if (cp == null) {
                    JOptionPane.showMessageDialog(null, "File Not Found");
                } else {
                    if (CG == 0) {
                        JPanel modi = new JPanel();
                        JTextField address = new JTextField(10);
                        modi.add(new JLabel("New Address:"));
                        modi.add(address);
                        JOptionPane.showInputDialog(modi);
                        String AD = address.getText();
                        cp.updateAddress(AD);
                    }
                    if (CG == 1) {
                        JPanel modi = new JPanel();
                        JTextField phone = new JTextField(10);
                        modi.add(new JLabel("New Phone:"));
                        modi.add(phone);
                        JOptionPane.showInputDialog(modi);
                        String PH = phone.getText();
                        cp.updatePhone(PH);
                    }
                    if (CG == 2) {
                        JPanel modi = new JPanel();
                        modi.add(new JLabel("New Status:"));
                        String[] Status = {"Active", "Inactive"};
                        JComboBox status = new JComboBox(Status);
                        modi.add(status);
                        JOptionPane.showInputDialog(modi);
                        String ST = "" + status.getItemAt(status.getSelectedIndex());
                        cp.updateStatus(ST);
                    }
                    if (CG == 3) {
                        JPanel modi = new JPanel();
                        modi.add(new JLabel("New Use:"));
                        String[] Uses = {"Business", "Personal", "Both"};
                        JComboBox use = new JComboBox(Uses);
                        modi.add(use);
                        JOptionPane.showInputDialog(modi);
                        String US = "" + use.getItemAt(use.getSelectedIndex());
                        cp.updateUse(US);
                    }
                    if (CG == 4) {
                        JPanel modi = new JPanel();
                        JTextField income = new JTextField(10);
                        modi.add(new JLabel("New Income:"));
                        modi.add(income);
                        JOptionPane.showInputDialog(modi);
                        Float IC = Float.parseFloat(income.getText());
                        cp.updateIncome(IC);
                    }
                    if (CG == 5) {
                        JPanel modi = new JPanel();
                        JTextField model = new JTextField(10);
                        modi.add(new JLabel("New Model:"));
                        modi.add(model);
                        JOptionPane.showInputDialog(modi);
                        String MD = model.getText();
                        cp.vehicleInfo.updateModel(MD);
                    }
                    if (CG == 6) {
                        JPanel modi = new JPanel();
                        JTextField year = new JTextField(10);
                        modi.add(new JLabel("New Year:"));
                        modi.add(year);
                        JOptionPane.showInputDialog(modi);
                        String YR = year.getText();
                        cp.vehicleInfo.updateYear(YR);
                    }
                    if (CG == 7) {
                        JPanel modi = new JPanel();
                        modi.add(new JLabel("New Type:"));
                        String[] types = {"sedan", "hatchback", "luxury", "sport", "other"};
                        JComboBox Type = new JComboBox(types);
                        modi.add(Type);
                        JOptionPane.showInputDialog(modi);
                        String TP = "" + Type.getItemAt(Type.getSelectedIndex());
                        cp.vehicleInfo.updateType(TP);
                    }
                    if (CG == 8) {
                        JPanel modi = new JPanel();
                        modi.add(new JLabel("New Method:"));
                        String[] methods = {"new", "certified pre-owned", "used", "other"};
                        JComboBox method = new JComboBox(methods);
                        modi.add(method);
                        JOptionPane.showInputDialog(modi);
                        String MD = "" + method.getItemAt(method.getSelectedIndex());
                        cp.vehicleInfo.updateMethod(MD);
                    }


                }

            }
        }
    }


    static class RemoveActionListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            System.out.println("Selected: " + e.getActionCommand());
            JTextField adminID = new JTextField(10);
            JTextField lastName = new JTextField(10);
            JPanel info = new JPanel();
            info.add(new JLabel("Associated AdminID:"));
            info.add(adminID);
            info.add(new JLabel("Customer lastName:"));
            info.add(lastName);

            GridLayout infolay = new GridLayout(2,1);
            info.setLayout(infolay);


            String result = null;
            String admin = JOptionPane.showInputDialog("Please Enter Your AdminID");
            if (!Objects.equals(admin, ID)){
                JOptionPane.showInputDialog("Incorrect AdminID");
            }else{
                result = JOptionPane.showInputDialog(info);
            }

            if (result == null){
                JOptionPane.showInputDialog("Fail to Delete");
            }else{
                String LN = lastName.getText();
                String id = adminID.getText();
                file.deleteProfile(id,LN);
                JOptionPane.showInputDialog("Delete Complete");
            }

        }
    }

    static class ImportActionListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {


            System.out.println("Selected: " + e.getActionCommand());

        //Creating the file import panel

            JPanel imp = new JPanel();
            JLabel file_name = new JLabel("File Pathname to Import OR Name to Create: ");
            JLabel id = new JLabel("Admin ID: ");
            JTextField File_name = new JTextField(30);
            JTextField admin = new JTextField(30);
            imp.add(file_name);
            imp.add(File_name);
            imp.add(id);
            imp.add(admin);

            GridLayout filelay = new GridLayout(2,2);
            imp.setLayout(filelay);

            JOptionPane.showInputDialog(imp);

            Filename = File_name.getText();
            ID = admin.getText();

            JOptionPane.showMessageDialog(null, "thank you "+ ID);

            gui start = new gui(Filename,ID);



        }
    }





    static class AddActionListener implements ActionListener {

        public void actionPerformed(ActionEvent e) {
            System.out.println("Selected: " + e.getActionCommand());
            String adminID;

            JTextField firstName = new JTextField(10);
            JTextField lastName = new JTextField(10);
            JTextField address = new JTextField(20);
            JTextField phone = new JTextField(10);


            JTextField income = new JTextField(10);
            String[] Status = {"Active", "Inactive"};
            JComboBox status = new JComboBox(Status);
            String[] Uses = {"Business","Personal","Both"};
            JComboBox use = new JComboBox(Uses);

            JPanel info = new JPanel();




            info.add(new JLabel("firstName:"));
            info.add(firstName);
            info.add(new JLabel("lastName:"));
            info.add(lastName);
            info.add(new JLabel("Phone Number:"));
            info.add(phone);
            info.add(new JLabel("address:"));
            info.add(address);
            info.add(new JLabel("Income:"));
            info.add(income);
            info.add(new JLabel("Status:"));
            info.add(status);
            info.add(new JLabel("Use:"));
            info.add(use);

            GridLayout infolay = new GridLayout(7,2);
            info.setLayout(infolay);



            JPanel car = new JPanel();

            JTextField model = new JTextField(10);
            JTextField year = new JTextField(10);
            String[] types = {"sedan", "hatchback", "luxury", "sport", "other"};
            JComboBox type = new JComboBox(types);
            String[] methods = {"new", "certified pre-owned", "used", "other"};
            JComboBox method = new JComboBox(methods);

            GridLayout carlay = new GridLayout(4,2);
            info.setLayout(carlay);


            car.add(new JLabel("Model:"));
            car.add(model);
            car.add(new JLabel("Year:"));
            car.add(year);
            car.add(new JLabel("Type:"));
            car.add(type);
            car.add(new JLabel("Method:"));
            car.add(method);



            adminID = JOptionPane.showInputDialog("Please Enter Your AdminID");
            if(!Objects.equals(adminID, ID)){
                JOptionPane.showMessageDialog(null, "ID Mismatch");
            }else{
                JOptionPane.showMessageDialog(null, "Welcome! " + adminID, "Results", JOptionPane.PLAIN_MESSAGE);
                JOptionPane.showInputDialog(info);

                String FN = firstName.getText();
                String LN = lastName.getText();
                String PH = phone.getText();
                String AD = address.getText();
                Float IC = Float.parseFloat(income.getText());
                String ST = ""+status.getItemAt(status.getSelectedIndex());
                String US = ""+use.getItemAt(use.getSelectedIndex());

                System.out.println(FN);

                if(PH.length() != 10){
                    JOptionPane.showMessageDialog(null, "Phone Number Format Error");
                }else{
                    JOptionPane.showInputDialog(car);
                    JOptionPane.showMessageDialog(null, "thx");

                    String  MD = model.getText();
                    String  YR= year.getText();
                    String  TP= ""+type.getItemAt(type.getSelectedIndex());
                    String  ME= ""+method.getItemAt(method.getSelectedIndex());

                    VehicleInfo new_car = new VehicleInfo(MD,YR,TP,ME);

                    CustomerProf new_cus = new CustomerProf(adminID,FN,LN,AD,PH,IC,ST,US,new_car);

                    file.customerList.add(new_cus);
                }
            }



        }
    }

    static class WriteActionListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            System.out.println("Selected: " + e.getActionCommand());
            file.writeAllCustomer();
            JOptionPane.showMessageDialog(null, "thank you "+ ID+", Writing Completed!");

        }
}}

