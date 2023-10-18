package org.kainos.ea.db;

import org.kainos.ea.cli.Order;

import java.io.FileInputStream;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

public class DatabaseConnector {
    private static Connection conn;
    public static Connection getConnection() throws SQLException {
        String user, password, host, name;
        if(conn!=null && !conn.isClosed()){return conn;}
        try(FileInputStream propStream= new FileInputStream("db.properties")){
            Properties props = new Properties();
            props.load(propStream);

            user = props.getProperty("user");
            password=props.getProperty("password");
            host = props.getProperty("host");
            name= props.getProperty("name");

            if(user==null||password==null||host==null)
                throw new IllegalArgumentException("Properties file must exist "+
                        "and must contain user, password, name and host properties.");

            conn= DriverManager.getConnection("jdbc:mysql://"+host+"/"+name+"?useSSL=false", user, password);
            return conn;
        } catch (Exception e){
            System.err.println(e.getMessage());
        } finally{
            System.out.println("I will always run!");
        }
        return null;
    }

    public List<Order> getAllOrders(){
        try (Connection c=getConnection()){
            Statement st= c.createStatement();

            ResultSet rs =st.executeQuery("SELECT OrderID, CustomerID, OrderDate FROM `Order`");
            List<Order> orderList= new ArrayList<>();

            while(rs.next()){
                Order order=new Order(
                        rs.getInt("OrderID"),
                        rs.getInt("CustomerID"),
                        rs.getDate("OrderDate")
                );

                orderList.add(order);
            }
            return orderList;
        }catch(SQLException e){
            System.err.println(e.getMessage());
            return null;
        }

    }
}
