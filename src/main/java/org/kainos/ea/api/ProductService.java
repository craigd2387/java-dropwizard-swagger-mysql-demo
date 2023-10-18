package org.kainos.ea.api;

import org.kainos.ea.cli.Order;
import org.kainos.ea.cli.Product;
import org.kainos.ea.cli.ProductRequest;
import org.kainos.ea.client.*;
import org.kainos.ea.core.ProductValidator;
import org.kainos.ea.db.ProductDao;

import javax.ws.rs.core.Response;
import java.sql.SQLException;
import java.util.*;
import java.util.stream.Collectors;

public class ProductService {

    private ProductDao productDao= new ProductDao();
    private ProductValidator productValidator= new ProductValidator();
    public List<Product> getAllProducts() throws FailedToGetProductsException {

        List<Product> productList= null;
        try {
            productList = productDao.getAllProducts();
        } catch (SQLException e) {
            System.err.println(e.getMessage());

            throw new FailedToGetProductsException();
        }

        try {
            Product product= productList.get(10000);
        } catch (Exception e) {
            //throw new RuntimeException(e);
            System.err.println(e.getMessage());
        }


        return productList;
    }

    public Product getProductByID(int id) throws FailedToGetProductsException, ProductDoesNotExistException {
        try{
            Product product=productDao.getProductByID(id);

            if(product==null){
                throw new ProductDoesNotExistException();
            }
            return product;
        }catch (SQLException e){

            System.err.println(e.getMessage());
            throw new FailedToGetProductsException();


        }

    }

    public int createProduct(ProductRequest product) throws FailedToCreateProductException, InvalidProductException{
        try{

            String validation= productValidator.isValidProduct(product);

            if(validation!=null){
                throw new InvalidProductException(validation);
            }

            int id= productDao.createProduct(product);

            if(id==-1){
                throw new FailedToCreateProductException();
            }
            return id;
        } catch (SQLException e){
            System.err.println(e.getMessage());
            throw new FailedToCreateProductException();
        }
    }

    public void updateProduct(int id, ProductRequest product) throws InvalidProductException, ProductDoesNotExistException,
            FailedToUpdateProductException {
        try {
            String validation = productValidator.isValidProduct(product);
            if (validation != null) {
                throw new InvalidProductException(validation);
            }
            Product productToUpdate = productDao.getProductByID(id);

            if (productToUpdate == null) {
                throw new ProductDoesNotExistException();
            }

            productDao.updateProduct(id, product);
        } catch (SQLException e) {
            System.err.println(e.getMessage());

            throw new FailedToUpdateProductException();
        }
    }

    public void deleteProduct(int id) throws ProductDoesNotExistException,FailedToDeleteProductException{
        try{
            Product productToDelete= productDao.getProductByID(id);

            if(productToDelete==null){
                throw new ProductDoesNotExistException();
            }
            productDao.deleteProduct(id);
        } catch(SQLException e){
            System.err.println(e.getMessage());
            throw new FailedToDeleteProductException();
        }
    }
}
/*List<Product> productList= new ArrayList<>();
        Product product1= new Product(1,"Hat", "Keeps head warm", 10.00);
        Product product2= new Product(2, "Pen", "writes things", 0.60);
        Product product3= new Product(1, "Cooler", "Keeps things cool", 49.99);

        productList.add(product1);
        productList.add(product2);
        productList.add(product3);

        return productList;*/

//Double totalPriceOfProduct=0.0;
//Double totalPriceofExpensiveProduct=0.0;
//Iterator for use with while and do while loops
//Iterator<Product> productIterator =productList.iterator();
        /*for(int i=0; i<productList.size();i++){
            totalPriceOfProduct+=productList.get(i).getPrice();
        }
        OR
        for(Product product: productList){
            totalPriceOfProduct+=product.getPrice();
        }
        OR
         while(productIterator.hasNext()){
            Product product=productIterator.next();
            totalPriceOfProduct+=product.getPrice();
        }
        OR
         do{
           Product product=productIterator.next();
           totalPriceOfProduct+=product.getPrice();
       }while(productIterator.hasNext());
        */

//using stream
//totalPriceOfProduct=productList.stream().mapToDouble(product-> product.getPrice()).sum();

        /*for(Product product: productList){
            if(product.getPrice()<100) {
                totalPriceOfProduct += product.getPrice();
            }else{
                totalPriceofExpensiveProduct+=product.getPrice();
            }
        }*/

        /*for(Product product: productList){
            switch (product.getName()){
                case("Whatever"):
                    System.out.println("This is the whatever price "+product.getPrice());
                    break;
                case("Umbrella"):
                    System.out.println("This is the umbrella price "+product.getPrice());
                    break;
                case("Scissors"):
                    System.out.println("This is the scissors price "+product.getPrice());
                    break;
                default:
                    System.out.println("This is the other price "+ product.getPrice());

            }
        }
        System.out.println("Total price of all products: £"+totalPriceOfProduct);
        System.out.println("Total price of expensive products: £"+totalPriceofExpensiveProduct);*/


       /* List<Integer> intList = Arrays.asList(1,2,2,4,5);
        Set<Integer> intSet= new HashSet<>(intList);
        //intList.stream().forEach(System.out::println);
        intSet.stream().forEach(System.out::println);*/

//sorted list- according to Product Class stipulations
//Collections.sort(productList);
//productList.stream().forEach(System.out::println);

//cheapest
//System.out.println(Collections.min(productList));

//most expensive
//System.out.println(Collections.max(productList));

//price higher than £10
//productList.stream().filter(product -> product.getPrice()>10).forEach(System.out::println);

//prices lower than £10
        /*List<Product> cheapProducts =
                productList.stream().filter(product -> product.getPrice()<10).collect(Collectors.toList());
        cheapProducts.forEach(System.out::println);*/
