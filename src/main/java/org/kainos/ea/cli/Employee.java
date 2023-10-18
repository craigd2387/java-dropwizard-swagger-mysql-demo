package org.kainos.ea.cli;

public class Employee {
    private int employeeID;
    private String name;
    private double salary;


    public Employee(int employeeID, String name, double salary) {
        setEmployeeID(employeeID);
        setName(name);
        setSalary(salary);
    }

    public double calcPay(String role){
        if(role=="Software Engineer"){
            return getSalary()/12+1000;
        }else{
            return getSalary()/12;
        }

    }
    public int getEmployeeID() {
        return employeeID;
    }

    public void setEmployeeID(int employeeID) {
        this.employeeID = employeeID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getSalary() {
        return salary;
    }

    public void setSalary(double salary) {
        this.salary = salary;
    }
}
