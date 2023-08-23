package sg.edu.nus.iss.server.models;

import java.util.List;

public class Order {

    private String orderID;
    private String name;
    private String email;
    private Boolean express;
    private List<OrderDetails> orderDetails;

    public Order() {
    }

    public Order(String orderID, String name, String email, Boolean express, List<OrderDetails> orderDetails) {
        this.orderID = orderID;
        this.name = name;
        this.email = email;
        this.express = express;
        this.orderDetails = orderDetails;
    }

    public String getOrderID() {
        return orderID;
    }

    public void setOrderID(String orderID) {
        this.orderID = orderID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Boolean getExpress() {
        return express;
    }

    public void setExpress(Boolean express) {
        this.express = express;
    }

    public List<OrderDetails> getOrderDetails() {
        return orderDetails;
    }

    public void setOrderDetails(List<OrderDetails> orderDetails) {
        this.orderDetails = orderDetails;
    }

    @Override
    public String toString() {
        return "Order [orderID=" + orderID + ", name=" + name + ", email=" + email + ", express=" + express
                + ", orderDetails=" + orderDetails + "]";
    }

}
