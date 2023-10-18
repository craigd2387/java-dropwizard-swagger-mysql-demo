package org.kainos.ea.api;

import org.kainos.ea.cli.Order;
import org.kainos.ea.cli.OrderRequest;
import org.kainos.ea.cli.Product;
import org.kainos.ea.cli.ProductRequest;
import org.kainos.ea.client.*;
import org.kainos.ea.core.OrderValidator;
import org.kainos.ea.db.OrderDao;

import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.stream.Collectors;

public class OrderService {

    private OrderDao orderDao= new OrderDao();
    private OrderValidator orderValidator= new OrderValidator();
    public List<Order> getAllOrders() throws FailedToGetOrdersException {
        /*List<Order> orderList= new ArrayList<>();
        Order order1= new Order(1,1, new Date());
        Order order2= new Order(2,1, new Date());

        orderList.add(order1);
        orderList.add(order2);*/
        List<Order> orderList= null;
        try {
            orderList = orderDao.getAllOrders();
        } catch (SQLException e) {
            System.err.println(e.getMessage());

            throw new FailedToGetOrdersException();
        }



        /*for(Order order: orderList){

            System.out.println("ID: "+order.getOrderID()+" Customer ID: "+order.getCustomerID()+" Date: "+order.getOrderDate());
        }*/

        /*Print out using Stream
        orderList.stream().forEach(System.out::println);*/

        /*date ascending
        Collections.sort(orderList);
        orderList.stream().forEach(System.out::println);*/

        /*date descending
        Collections.sort(orderList);
        Collections.reverse(orderList);
        orderList.stream().forEach(System.out::println);*/

        /*Date d1= new Date(2023,10,15);
        Date d2= new Date(2023, 10, 9);
        for(Order order:orderList){
            if(order.getOrderDate().after(d2)&&order.getOrderDate().before(d1)){
                System.out.println(order.getOrderID());

            }

        }*/
        //Find orders from certain week
        //String d1= "2023-10-15";
        //String d2= "2023-10-08";

        /*DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate localDate1 = LocalDate.parse(d1, formatter);
        LocalDate localDate2 = LocalDate.parse(d2, formatter);
        orderList.stream()
                .filter(e -> !LocalDate.parse(e.getOrderDate().toString(), formatter).isBefore(localDate2)
                        && !LocalDate.parse(e.getOrderDate().toString(), formatter).isAfter(localDate1))
                .collect(Collectors.toList())
                .forEach(System.out::println);*/

        /* return orders for customer 1
        for(Order order: orderList){
            if(order.getCustomerID()==1){
                System.out.println("ID: "+order.getOrderID()+" Customer ID: "+order.getCustomerID()+" Date: "+order.getOrderDate());
            }
        }*/

        /* Most recent order
        Collections.sort(orderList);
        Collections.reverse(orderList);

        System.out.println(orderList.get(0));*/

        /* oldest order
        Collections.sort(orderList);
        System.out.println(orderList.get(0));*/

        /*total count of all orders
        System.out.println(orderList.size());*/

        //customer ID with the most orders
        //Map<Integer, Integer> ordersMap= new HashMap<Integer,Integer>();
        Map<Integer, Long> ordersMap= orderList.stream()
                .collect(Collectors
                        .groupingBy(Order::getCustomerID, Collectors.counting()));

        int customerWithMostOrders=Collections.max(ordersMap.entrySet(), Map.Entry.comparingByValue()).getKey();
        //int customerWithLeastOrders=Collections.min(ordersMap.entrySet(), Map.Entry.comparingByValue()).getKey();

        System.out.println("Customer with most orders: "+customerWithMostOrders);
        //System.out.println("Customer with least orders:"+ customerWithLeastOrders);






        return orderList;
    }

    public Order getOrderByID(int id) throws FailedToGetOrdersException, OrderDoesNotExistException {
        try{
            Order order=orderDao.getOrderByID(id);

            if(order==null){
                throw new OrderDoesNotExistException();
            }
            return order;
        }catch (SQLException e){

            System.err.println(e.getMessage());
            throw new FailedToGetOrdersException();


        }

    }
    public int createOrder(OrderRequest order) throws FailedToCreateOrderException, InvalidOrderException{
        try{

            String validation= orderValidator.isValidOrder(order);

            if(validation!=null){
                throw new InvalidOrderException(validation);
            }

            int id= orderDao.createOrder(order);

            if(id==-1){
                throw new FailedToCreateOrderException();
            }
            return id;
        } catch (SQLException e){
            System.err.println(e.getMessage());
            throw new FailedToCreateOrderException();
        }
    }

    public void updateOrder(int id, OrderRequest order) throws InvalidOrderException, OrderDoesNotExistException,
            FailedToUpdateOrderException {
        try {
            String validation = orderValidator.isValidOrder(order);
            if (validation != null) {
                throw new InvalidOrderException(validation);
            }
            Order orderToUpdate = orderDao.getOrderByID(id);

            if (orderToUpdate == null) {
                throw new OrderDoesNotExistException();
            }

            orderDao.updateOrder(id, order);
        } catch (SQLException e) {
            System.err.println(e.getMessage());

            throw new FailedToUpdateOrderException();
        }
    }

    public void deleteOrder(int id) throws OrderDoesNotExistException,FailedToDeleteOrderException{
        try{
            Order orderToDelete= orderDao.getOrderByID(id);

            if(orderToDelete==null){
                throw new OrderDoesNotExistException();
            }
            orderDao.deleteOrder(id);
        } catch(SQLException e){
            System.err.println(e.getMessage());
            throw new FailedToDeleteOrderException();
        }
    }



}
