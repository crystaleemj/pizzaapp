package sg.edu.nus.iss.pizzaapp.model;

import org.springframework.stereotype.Component;

@Component
public class Pizza {
    
    private String pizza;
    private String size;
    private int quantity;

    
    public Pizza() {
    }


    public String getPizza() {
        return pizza;
    }


    public void setPizza(String pizza) {
        this.pizza = pizza;
    }


    public String getSize() {
        return size;
    }


    public void setSize(String size) {
        this.size = size;
    }


    public int getQuantity() {
        return quantity;
    }


    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
    
     

}
