package Models;

import Controllers.CTEmployee;

import java.util.Date;

/**
 * Created by Vidush H. Namah on 11 Mar 2016.
 **/

public class Employee implements Comparable<Employee>{

    private String ID;
    private String FName;
    private String LName;
    private String Address;
    private char Gender;
    private String Email;
    private int Phone;
    private Date DOB;
    private Date DateJoined;
    private double Salary;

    private Account EAccount;

    public Account getEAccount() {
        return EAccount;
    }

    public void setEAccount(Account EAccount) {
        this.EAccount = EAccount;
    }

    public String getID() {
        return ID;
    }

    public void setID(String ID) {
        this.ID = ID;
    }

    public String getFName() {
        return FName;
    }

    public void setFName(String FName) {
        this.FName = FName;
    }

    public String getLName() {
        return LName;
    }

    public void setLName(String LName) {
        this.LName = LName;
    }

    public String getAddress() {
        return Address;
    }

    public void setAddress(String address) {
        Address = address;
    }

    public char getGender() {
        return Gender;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String email) {
        Email = email;
    }

    public void setGender(char gender) {
        Gender = gender;
    }

    public int getPhone() {
        return Phone;
    }

    public void setPhone(int phone) {
        Phone = phone;
    }

    public Date getDOB() {
        return DOB;
    }

    public void setDOB(Date DOB) {
        this.DOB = DOB;
    }

    public Date getDateJoined() {
        return DateJoined;
    }

    public void setDateJoined(Date dateJoined) {
        DateJoined = dateJoined;
    }

    public double getSalary() {
        return Salary;
    }

    public void setSalary(double salary) {
        Salary = salary;
    }

    public Employee() {
        this.ID="0000000";
        this.FName=null;
        this.LName=null;
        this.Address=null;
        this.Gender='\0';
        this.Email=null;
        this.Phone=0;
        this.DOB=new Date();
        this.DateJoined=new Date();
        this.Salary=0;
    }

    public Employee(String ID, String FName, String LName, String Address, char Gender, String Email, int Phone, Date DOB, Date DateJoined, double Salary) {
        this.ID=ID;
        this.FName=FName;
        this.LName=LName;
        this.Address=Address;
        this.Gender=Gender;
        this.Email=Email;
        this.Phone=Phone;
        this.DOB=DOB;
        this.DateJoined=DateJoined;
        this.Salary=Salary;
    }

    @Override
    public String toString() {
        String Buffer;
        Buffer=this.ID+" ";
        Buffer+=this.FName+" ";
        Buffer+=this.LName+" ";
        Buffer+=this.Address+" ";
        Buffer+=this.Gender+" ";
        Buffer+=this.Email+" ";
        Buffer+=this.Phone+" ";
        Buffer+=this.DOB+" ";
        Buffer+=this.DateJoined+" ";
        Buffer+=this.Salary;

        return Buffer;
    }

    @Override
    public int compareTo(Employee E) {
        return this.getID().compareToIgnoreCase(E.getID());
    }
}
