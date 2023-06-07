package sg.edu.nus.iss.pizzaapp.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;

import sg.edu.nus.iss.pizzaapp.model.Delivery;
import sg.edu.nus.iss.pizzaapp.model.Order;
import sg.edu.nus.iss.pizzaapp.model.Pizza;
import sg.edu.nus.iss.pizzaapp.repository.PizzaRepo;

@Service
public class PizzaService {

    @Autowired
    private PizzaRepo repo;


    Set<String> pizzaNames = Set.of("bella", "margherita", 
    "marinara", "spianatacalabrese", "trioformaggio");
    
    Set<String> pizzaSizes = Set.of("sm", "md", "lg");


    // ObjectError is for validation = returns list of errors
    public List<ObjectError> validateOrder(Pizza pizza){
        List<ObjectError> errors = new ArrayList<>();

        // object to contain error 
        FieldError error;

        if (!pizzaNames.contains(pizza.getPizza())){
            error = new FieldError("pizza", "pizza", "Selection Unavailable");
            errors.add(error);
        }
        if (!pizzaSizes.contains(pizza.getSize())){
            error = new FieldError("pizza", "size", "Size Unavailable");
            errors.add(error); 
        }
        return errors;
    }

    public Order createOrder(Pizza p, Delivery d){
        Order order = new Order(p, d);
        String orderId = UUID.randomUUID().toString().substring(0,8);
        // set generated id into object orderid
        order.setOrderId(orderId);

        return order;
    }

    public double calcCost(Order order){
        double total = 0;
        switch (order.getPizzaName()) {
            case "margherita":
            total += 22;
                
                break;
            case "trioformaggio":
            total += 25;
                    
                break;
            case "bella", "marinara", "spianatacalabrese":
            total += 30;
                    
                break;
        }

        switch (order.getPizzaSize()) {
            case "sm":
            total *= 1;
                
                break;
            case "md":
            total *= 1.2;
                    
                break;
            case "lg":
            total *= 1.5;
                    
                break;
        }
        total *= order.getPizzaQty();

        // to check if order.getRush() is true
        if (order.getRush()){
            total += 2;
        }
        order.setTotalCost(total);
        return total;


    }

    // final method to be called in controller
    public Order saveOrder(Pizza p, Delivery d){
        Order order = createOrder(p, d);
        calcCost(order);
        repo.save(order);
        System.out.println(order);

        return order;
    }

    public Order getOrderbyId(String orderId){
        return this.repo.getOrder(orderId);
    }




}
