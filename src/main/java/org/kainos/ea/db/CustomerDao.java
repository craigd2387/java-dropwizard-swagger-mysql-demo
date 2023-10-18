package org.kainos.ea.db;

import org.kainos.ea.cli.Customer;
import org.kainos.ea.cli.Order;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;


public class CustomerDao {

    private DatabaseConnector databaseConnector= new DatabaseConnector();
    public List<Customer> getAllCustomers() throws SQLException {
        Connection c= databaseConnector.getConnection();

        Statement st = c.createStatement();

        ResultSet rs = st.executeQuery("SELECT CustomerID, Name, Address, Phone FROM Customer");
        List<Customer> customerList = new ArrayList<>();

        while (rs.next()) {
            Customer customer = new Customer(
                    rs.getInt("CustomerID"),
                    rs.getString("Name"),
                    rs.getString("Address"),
                    rs.getString("Phone")
            );

            customerList.add(customer);
        }
        return customerList;

    }

    public Customer getCustomerByID(int id) throws SQLException {
        Connection c=databaseConnector.getConnection();
        Statement st= c.createStatement();

        ResultSet rs =st.executeQuery("SELECT CustomerID, Name, Address, Phone FROM Customer WHERE OrderID=" +id);

        while(rs.next()){
            return new Customer(
                    rs.getInt("CustomerID"),
                    rs.getString("Name"),
                    rs.getString("Address"),
                    rs.getString("Phone")
            );
        }
        return null;


    }
}
