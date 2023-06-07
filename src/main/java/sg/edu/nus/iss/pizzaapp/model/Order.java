package sg.edu.nus.iss.pizzaapp.model;

import java.io.Serializable;
import java.io.StringReader;

import jakarta.json.Json;
import jakarta.json.JsonObject;
import jakarta.json.JsonReader;

public class Order implements Serializable {
    private Pizza pizza;
    private Delivery delivery;
    private double totalCost = 0;
    private String orderId;


    public Order(Pizza pizza, Delivery delivery) {
        this.pizza = pizza;
        this.delivery = delivery;
    }
    public Pizza getPizza() {
        return pizza;
    }
    public void setPizza(Pizza pizza) {
        this.pizza = pizza;
    }
    public Delivery getDelivery() {
        return delivery;
    }
    public void setDelivery(Delivery delivery) {
        this.delivery = delivery;
    }
    public double getTotalCost() {
        return totalCost;
    }
    public void setTotalCost(double totalCost) {
        this.totalCost = totalCost;
    }
    public String getOrderId() {
        return orderId;
    }
    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public String getPizzaName(){
        return this.getPizza().getPizza();
    }

    public String getPizzaSize(){
        return this.getPizza().getSize();
    }

    public int getPizzaQty(){
        return this.getPizza().getQuantity();
    }

    public boolean getRush(){
        return this.getDelivery().isRush();
    }

    public String getName(){
        return this.getDelivery().getName();
    }

    public String getAddress(){
        return this.getDelivery().getAddress();
    }

    public String getNumber(){
        return this.getDelivery().getPhoneNum();
    }

    public String getComments(){
        return this.getDelivery().getComments();
    }

    // method for creating json object
    public JsonObject toJson(){
        return Json.createObjectBuilder()
        .add("orderId", this.getOrderId())
        .add("name", this.getName())
        .add("address", this.getAddress())
        .add("phone", this.getNumber())
        .add("rush", this.getRush())
        .add("comments", this.getComments())
        .add("pizza", this.getPizzaName())
        .add("size", this.getPizzaSize())
        .add("quantity", this.getPizzaQty())
        .add("total", this.getTotalCost())
        .build();
     
    }

    // method for converting json > pojo object
    public static Order create(String json){
        // create json reader to read string
        JsonReader reader = Json.createReader(new StringReader(json));
        // create json object
        JsonObject obj = reader.readObject();
        // create new order object
        Pizza p = Pizza.create(obj);
        Delivery d = Delivery.create(obj);
        Order order = new Order(p, d);
        // setting orderId 
        order.setOrderId(obj.getString("orderId"));
        // for numbers use getJsonNumber
        order.setTotalCost(obj.getJsonNumber("total").doubleValue());
        return order;
    }
    
}
