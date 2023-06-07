package sg.edu.nus.iss.pizzaapp.model;

import java.io.Serializable;

import jakarta.json.JsonObject;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public class Delivery implements Serializable{

    @NotNull (message = "what name")
    @Size(min=3, message = "> 3 chars pls")
    private String name;

    @NotNull (message = "where u stay")
    private String address;

    @NotNull (message = "number pls")
    @Size(min=8, message = "min 8 num")
    private String phoneNum;

    private boolean rush = false;
    private String comments = "";

    
    public Delivery() {
    }

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getAddress() {
        return address;
    }
    public void setAddress(String address) {
        this.address = address;
    }
    public String getPhoneNum() {
        return phoneNum;
    }
    public void setPhoneNum(String phoneNum) {
        this.phoneNum = phoneNum;
    }
    public boolean isRush() {
        return rush;
    }
    public void setRush(boolean rush) {
        this.rush = rush;
    }
    public String getComments() {
        return comments;
    }
    public void setComments(String comments) {
        this.comments = comments;
    }

    public static Delivery create(JsonObject obj){
        Delivery d = new Delivery();
        d.setName(obj.getString("name"));
        d.setAddress(obj.getString("address"));
        d.setPhoneNum(obj.getString("phone"));
        d.setRush(obj.getBoolean("rush"));
        d.setComments(obj.getString("comments"));
        return d;
    }

    

}
