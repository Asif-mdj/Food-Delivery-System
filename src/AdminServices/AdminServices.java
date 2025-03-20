package AdminServices;

import Admin.Admin;
import DeliveryAgentServices.*;
import HotelService.*;
import OrderService.*;
import UserServices.*;


import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class AdminServices implements ILoginAdmin, IAdminMenu, IMonitorSystemPerformace, IResolveOrderDispute{

    private List<Admin> admins = new ArrayList<>();
    private Scanner scanner = new Scanner(System.in);

    private UserService userService;
    private DeliveryAgentService deliveryAgentService;
    private HotelService hotelService;
    private OrderService orderService;

    public AdminServices (UserService userService,
                          DeliveryAgentService deliveryAgentService,
                          HotelService hotelService) {
        this.userService = userService;
        this.deliveryAgentService = deliveryAgentService;
        this.hotelService = hotelService;

        admins.add(new Admin("admin", "admin123"));
    }

    @Override
    public void loginAdmin() {
        System.out.print("Enter Admin UserName: ");
        String adminUserName = scanner.next();
        System.out.print("Enter Admin Password: ");
        String adminPassword = scanner.next();

        for (Admin admin : admins) {
            if (admin.getAdminUserName().equalsIgnoreCase(adminUserName)
                    && admin.getAdminPassword().equalsIgnoreCase(adminPassword)) {
                System.out.println("Login Successful!!!");
                System.out.println("-------------------");
                adminMenu();
                return;
            } else
                System.out.println("Invalid Admin username and password!");
            System.out.println("----------------------------------------");
        }
    }

    @Override
    public void adminMenu() {
        while (true) {
            System.out.println("Admin Dashboard");
            System.out.println("------------------------------");
            System.out.println("1.View All Users");
            System.out.println("2.View All Hotels");
            System.out.println("3.View All Delivery Agents");
            System.out.println("4.Remove User");
            System.out.println("5.Remove Hotel");
            System.out.println("6.Remove Delivery Agent");
            System.out.println("7.Monitor System Performance");
            System.out.println("8.Resolve Order Disputes");
            System.out.println("9.Logout");
            System.out.println("-------------------------------");
            System.out.println("Choose an Option to continue: ");
            int option = scanner.nextInt();
            System.out.println("-------------------------------");
            switch (option) {
                case 1:
                    userService.viewUser();
                    break;
                case 2:
                    hotelService.viewHotels();
                    break;
                case 3:
                    deliveryAgentService.viewDeliveryAgent();
                    break;
                case 4:
                    System.out.println("Enter User to remove: ");
                    String userName = scanner.next();
                    scanner.nextLine();
                    boolean u = userService.removeUser(userName);
                    if (!u) {
                        System.out.println("User Not found!");
                        System.out.println("-------------------------------");
                    } else {
                        System.out.println("User deleted from the database");
                        System.out.println("-------------------------------");
                    }
                    break;

                case 5:
                    System.out.println("Enter Hotel to remove: ");
                    String hotelName = scanner.next();
                    boolean h = hotelService.removeHotel(hotelName);
                    if (!h) {
                        System.out.println("Hotel Not found!");
                        System.out.println("-------------------------------");
                    } else {
                        System.out.println("Hotel deleted from the database");
                        System.out.println("-------------------------------");
                    }
                    break;
                case 6:
                    System.out.println("Enter Delivery Agent to remove: ");
                    String deliveryAgentName = scanner.next();
                    boolean d = deliveryAgentService.removeDeliveryAgent(deliveryAgentName);
                    if (!d) {
                        System.out.println("Delivery Agent Not found!");
                        System.out.println("-------------------------------");
                    } else {
                        System.out.println("Delivery Agent deleted from the database");
                        System.out.println("----------------------------------------");
                    }
                    break;
                case 7:
                    try{
                        monitorSystemPerformance();
                    } catch (NullPointerException nullPointerException) {
                        continue;
                    }
                    break;
                case 8:
                    resolveOrderDispute();
                    break;
                case 9: {
                    System.out.println("Logging Out......");
                    System.out.println("-------------------------------");
                    return;
                }
                default:
                    System.out.println("Invalid Choice, Please Try Again");
                    System.out.println("-------------------------------");
                    break;
            }
        }
    }

    @Override
    public void monitorSystemPerformance() {
        System.out.println("System Performance Report");
        System.out.println("-------------------------------");
        System.out.println("Total Users: " +userService.getUserCount());
        System.out.println("Total Hotels: "+hotelService.getHotelCount());
        System.out.println("Total Orders: "+orderService.getTotalOrders());
        System.out.println("Total Completed Orders: "+orderService.getCompletedOrderCounts());
        System.out.println("Total Pending Orders: " +orderService.getPendingOrders());
        System.out.println("-------------------------------");
    }

    @Override
    public void resolveOrderDispute() {
        System.out.println("Enter Order ID for Dispute Resolution: ");
        int orderId = scanner.nextInt();
        if (orderService.resolveDispute(orderId)) {
            System.out.println("Order Dispute Resolved Successfully!!");
            System.out.println("-------------------------------------");
        } else {
            System.out.println("Invalid Order ID or No Dispute Found!");
            System.out.println("-------------------------------------");
        }
    }
}
