package org.kainos.ea.core;

import org.kainos.ea.cli.OrderRequest;
import org.kainos.ea.cli.ProductRequest;

import java.time.Duration;
import java.time.Instant;
import java.util.Date;

public class OrderValidator {
    public String isValidOrder(OrderRequest order){
        if(order.getCustomerID()<1){
            return "Customer does not exist";
        }

        if(order.getOrderDate().before(Date.from(Instant.now().minus(Duration.ofDays(365))))){
            return "Order date is older than 1 year";
        }



        return null;
    }
}
