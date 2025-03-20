package OrderService;

import Hotel.FoodItem;
import Hotel.Hotel;
import HotelService.HotelService;
import Order.*;
import UserServices.*;


import java.util.*;

public class OrderService implements IPlaceOrder, ViewAllOrders, GetTotalOrders, GetCompletedOrderCounts,
        GetPendingOrderCount, ResolveDispute, IUpdateOrderStatus, IGenerateInvoice {

    private List<Order> orders = new ArrayList<>();
    private Scanner scanner = new Scanner(System.in);
    private UserService userService;
    private HotelService hotelService;
    private Order order;

    public List<Order> getOrders() {
        return orders;
    }

    public OrderService(UserService userService) {
        this.userService = userService;
    }

    public void placeOrder(HotelService hotelService) {

        List<FoodItem> orderedFoodItems = new ArrayList<>();

        int estimatedTime = 30;

        System.out.println("--------------------------");

        if (userService.getLoggedInUser() == null) {
            System.out.print("Please Log in to place order....");
            return;
        } else {
            System.out.print("Enter Hotel Name: ");
            String hotelName = scanner.next();
            scanner.nextLine();
            for (Hotel hotel : hotelService.getHotels()) {
                if (hotel.getHotelName().equalsIgnoreCase(hotelName)) {
                    for (FoodItem foodItem : hotel.getmenu()){
                        System.out.println(foodItem.getFoodName()+ " : "+foodItem.getFoodPrice());
                    }
                    System.out.println("--------------------------------------");
                    System.out.print("Please enter your food from above list: ");
                    String foodItem = scanner.next();
                    scanner.nextLine();
                    for (FoodItem foodItems : hotel.getmenu()) {
                        while (true) {
                        if (foodItems.getFoodName().equalsIgnoreCase(foodItem)) {
                            orderedFoodItems.add(foodItems);
                            order = new Order(userService.getLoggedInUser(), hotel, orderedFoodItems);
                            orders.add(order);
                            System.out.println("Order Placed Successfully!!! and Your Order ID is "
                                    + order.getOrderId() + ", Estimated Time: " + estimatedTime);
                            System.out.println();
                            System.out.println("-------------------------------------------------------");
                            System.out.print("Please enter yes to continue or done to close the order: ");
                            String string = scanner.nextLine();
                            if ("yes".equalsIgnoreCase(string)){
                                continue;
                            } else {
                                System.out.println("------------------------------------------------------");
                                break;

                            }
                        } else {
                            System.out.println("Food Item not available, please try others");
                            System.out.println("-------------------------------------------");
                            break;
                        }
                        }
                    }

                }else {
                    System.out.print("Hotel Not Found, Please try again with some other Hotel");
                }
            }
        }
    }

    public void viewAllOrders () {

        if (orders.isEmpty()) {
            System.out.println("No Orders Available");
        } else {
            System.out.println("All Orders.....");
            for (Order order : orders) {
                System.out.println("Order ID: "+order.getOrderId());
                System.out.println("Status: "+order.getOrderStatus());
            }
            System.out.println("----------------------------------");
        }

    }

    public int getTotalOrders () {
        try {
            return orders.size();
        } catch (NullPointerException nullPointerException) {
            return 0;
        }
    }

    public int getCompletedOrderCounts () {
        try{
            return (int)orders.stream().filter(o-> o.getOrderStatus().equals("Delivered")).count();
        } catch (NullPointerException nullPointerException) {
            return 0;
        }
    }

    public int getPendingOrders () {
        try {
            return (int) orders.stream().filter(o -> o.getOrderStatus().equals("Preparing")).count();
        } catch (NullPointerException nullPointerException){
            return 0;
        }

    }

    public boolean resolveDispute (int orderId) {
        for (Order order : orders) {
            if (order.getOrderId() == orderId && order.isDisputed()){
                order.setDisputed(false);
                order.setOrderStatus("Resolved");
                return true;
            }
        }
        return false;
    }

    @Override
    public void updateOrderStatus() {

        switch (this.order.getOrderStatus()) {
            case "Preparing":
                this.order.setOrderStatus("Ready For Pickup");
            case "Ready For Pickup":
                this.order.setOrderStatus("Out For Delivery");
            case "Out For Delivery":
                this.order.setOrderStatus("Delivered");
        }

    }

    public void generateInvoice () {
        if (Objects.nonNull(order)){
        System.out.println("--------Invoice--------");
        System.out.println("Order Id = " +order.getOrderId());
        System.out.println("User = " +order.getUser().getUserName());
        System.out.println("Hotel = " +order.getHotel().getHotelName());
        System.out.print("Items Ordered = ");
        for (int i=0;i< orders.size();i++){
            System.out.print(orders.get(i)+", ");
        }
        System.out.println();
        double totalAmount = 0.0d;
        for (int i=0;i< orders.size();i++){
            FoodItem foodItem = order.getItems().get(i);
            double d = foodItem.getFoodPrice();
            totalAmount = totalAmount+d;
        }
        System.out.print("Total Amount = "+totalAmount);
        System.out.println("-------------------------------");
        } else {
            System.out.println("No Orders Found to Invoice!!!!");
            System.out.println("-------------------------------");
        }
    }
}