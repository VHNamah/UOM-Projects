package Controllers;

import Helpers.SQLServer;
import Models.Employee;

import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

/**
 * Created by Vidush H. Namah on 11 Mar 2016.
 **/

public class CTEmployee {

    private static Connection C = SQLServer.getConnect();

    public static ArrayList<Employee> LISTEmployee = new ArrayList<>();
    public static String[] ARRPermission = {"Cashier","Store Manager","Product Manager","System Administrator"};

    public static void fetchEmployees() throws SQLException {
        LISTEmployee.clear();

        String SQL = "SELECT * FROM TBLEMPLOYEE E JOIN TBLACCOUNT A ON E.employeeId=A.employeeId WHERE A.permission>0";
        Statement S = C.createStatement();
        ResultSet R = S.executeQuery(SQL);

        //ADDING TO ARRAYLIST
        while (R.next()) {
            String ID=R.getString("employeeId");
            String FName=R.getString("fname");
            String LName=R.getString("lname");
            String Address=R.getString("address");
            char Gender=R.getString("gender").charAt(0);
            String Email=R.getString("email");
            int Phone=R.getInt("phone");
            Date DOB=R.getDate("dob");
            Date DateJoined=R.getDate("dateJoined");
            double Salary=R.getDouble("salary");

            Employee Buffer = new Employee(ID,FName,LName,Address,Gender,Email,Phone,DOB,DateJoined,Salary);

            LISTEmployee.add(Buffer);
        }

        R.close();
        S.close();
    }

    public static void updateEmployee(Employee E) throws SQLException {
        SimpleDateFormat D = new SimpleDateFormat("yyyy-MM-dd");

        String SQL="UPDATE TBLEMPLOYEE SET " +
                "fname=?," +
                "lname=?," +
                "address=?," +
                "email=?," +
                "gender=?," +
                "phone=?," +
                "dob=?," +
                "dateJoined=?," +
                "salary=? " +
                "WHERE employeeId=?";

        PreparedStatement S = C.prepareStatement(SQL);

        S.setString(1, E.getFName());
        S.setString(2, E.getLName());
        S.setString(3, E.getAddress());
        S.setString(4, E.getEmail());
        S.setString(5, String.valueOf(E.getGender()));
        S.setString(6, String.valueOf(E.getPhone()));
        S.setString(7, String.valueOf(D.format(E.getDOB())));
        S.setString(8, String.valueOf(D.format(E.getDateJoined())));
        S.setString(9, String.valueOf(E.getSalary()));
        S.setString(10, E.getID());

        S.execute();
    }

    public static void insertEmployee(Employee E) throws SQLException {
        SimpleDateFormat D = new SimpleDateFormat("yyyy-MM-dd");

        String SQL="INSERT INTO TBLEMPLOYEE VALUES(?,?,?,?,?,?,?,?,?,?)";
        PreparedStatement S = C.prepareStatement(SQL);

        S.setString(1, E.getID());
        S.setString(2, E.getFName());
        S.setString(3, E.getLName());
        S.setString(4, E.getAddress());
        S.setString(5, E.getEmail());
        S.setString(6, String.valueOf(E.getGender()));
        S.setString(7, String.valueOf(E.getPhone()));
        S.setString(8, String.valueOf(D.format(E.getDOB())));
        S.setString(9, String.valueOf(D.format(E.getDateJoined())));
        S.setString(10, String.valueOf(E.getSalary()));

        S.execute();
    }

    public static void displayAll() {
        for(Employee E : LISTEmployee)
            System.out.println(E + "\n");
    }
}
