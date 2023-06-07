package sg.edu.nus.iss.pizzaapp.model;

import java.io.Serializable;

import jakarta.json.JsonObject;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

public class Pizza implements Serializable {
    
    @NotNull (message = "no pizz")
    private String pizza;

    @NotNull (message = "what size")
    private String size;

    @NotNull (message = "how many")
    @Min(value=1)
    @Max(value=10)
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


    @Override
    public String toString() {
        return "Pizza [pizza=" + pizza + ", size=" + size + ", quantity=" + quantity + "]";
    }
    
    public static Pizza create(JsonObject obj){
        Pizza p = new Pizza();
        p.setPizza(obj.getString("pizza"));
        p.setSize(obj.getString("size"));
        p.setQuantity(obj.getInt("quantity"));
        return p;
    }
    
     

}
