package org.kainos.ea.client;

public class OrderDoesNotExistException extends Throwable {
    @Override
    public String getMessage(){
        return "Sorry this Order does not exist";
    }
}
