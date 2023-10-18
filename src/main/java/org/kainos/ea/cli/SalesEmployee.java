package org.kainos.ea.cli;

public class SalesEmployee extends Employee{

    private double monthlySales;
    private float commissionRate;

    public SalesEmployee(int employeeID, String name, double salary, double monthlySales, float commissionRate){
        super(employeeID, name, salary);
        setMonthlySales(monthlySales);
        setCommissionRate(commissionRate);
    }

    public double getMonthlySales() {
        return monthlySales;
    }

    public void setMonthlySales(double monthlySales) {
        this.monthlySales = monthlySales;
    }

    public float getCommissionRate() {
        return commissionRate;
    }

    public void setCommissionRate(float commissionRate) {
        this.commissionRate = commissionRate;
    }

    @Override
    public double calcPay(String role){
        return getSalary()/12+(monthlySales*commissionRate);
    }

}
