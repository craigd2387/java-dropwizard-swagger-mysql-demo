package org.kainos.ea.client;

public class ProductDoesNotExistException extends Exception {
    @Override
    public String getMessage(){
        return "Sorry this product does not exist";
    }
}
