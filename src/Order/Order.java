package Order;

import Hotel.*;
import User.User;

import java.util.HashMap;
import java.util.List;

public class Order {

    private static int orderCounter = 1;
    private int orderId;
    private User user;
    private Hotel hotel;
    private List<FoodItem> items;
    private String orderStatus;
    private boolean disputed;

    public Order () {
        this.orderId = 0;
    }


    public Order (User user, Hotel hotel, List<FoodItem> items) {
        this.orderId = orderCounter++;
        this.user = user;
        this.hotel = hotel;
        this.items = items;
        this.orderStatus = "Preparing";
        this.disputed = false;
    }

    public User getUser() {
        return user;
    }

    public Hotel getHotel() {
        return hotel;
    }

    public boolean isDisputed() {
        return disputed;
    }

    public int getOrderId () {
        return this.orderId;
    }

    public String getOrderStatus () {
        return orderStatus;
    }

    public void setOrderStatus (String orderStatus) {
        this.orderStatus = orderStatus;
    }

    public List<FoodItem> getItems() {
        return items;
    }

    public void setDisputed (boolean disputed) {
        this.disputed = disputed;
    }

    @Override
    public String toString() {
        return "Order ID: " +orderId +" | User: "
                +user.getUserName()+ " | Hotel: "
                +hotel.getHotelName() + " | Status: "
                +orderStatus;
    }

}
