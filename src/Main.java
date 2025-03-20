import AdminServices.AdminServices;
import DeliveryAgentServices.DeliveryAgentService;
import DeliveryAgentServices.LoginDeliveryAgent;
import DeliveryAgentServices.RegisterDeliveryAgent;

import Hotel.*;
import HotelService.*;
import OrderService.*;
import User.*;
import UserServices.*;

import java.util.Objects;
import java.util.Scanner;

public class Main {

    static UserService userService = new UserService();
    static HotelService hotelService = new HotelService();
    static DeliveryAgentService deliveryAgentService = new DeliveryAgentService();
    static OrderService orderService = new OrderService(userService);
    static AdminServices adminServices = new AdminServices(userService,deliveryAgentService, hotelService);
    static Scanner scanner = new Scanner (System.in);

    public static void userMenu () {
        while (true) {
            System.out.println("----------User Menu-----------");
            System.out.println("1. View and Select Hotels");
            System.out.println("2. Update Profile ");
            System.out.println("3. Generate Invoice");
            System.out.println("4. Logout");
            System.out.println("-------------------------------");
            System.out.println("Choose from above options to proceed further");
            int option = scanner.nextInt();
            System.out.println("-------------------------------");
            switch (option) {
                case 1:
                    hotelService.viewHotels();
                    if (hotelService.getHotels().isEmpty()) {
                        break;
                    } else {
                        orderService.placeOrder(hotelService);
                    }
                    break;
                case 2:
                    userService.getLoggedInUser().updateUser();
                    break;
                case 3:
                    orderService.generateInvoice();
                case 4:
                    System.out.println("Logging out..");
                    return;
                default:
                    System.out.println("Enter a valid option....");
            }
        }
    }

    public static void main (String[] args) {

        while (true) {
            System.out.println("-----------Food Delivery System------------");
            System.out.println("1. Register User");
            System.out.println("2. User Login");
            System.out.println("3. Register Hotel");
            System.out.println("4. Update Hotel Profile / add food items");
            System.out.println("5. Register Delivery Agent");
            System.out.println("6. Delivery Agent Login");
            System.out.println("7. Admin Login");
            System.out.println("8. Exit");
            System.out.println("--------------------------------------------");
            System.out.print("Choose from above options to continue: ");
            int option = scanner.nextInt();
            System.out.println("--------------------------------------------");

            switch (option) {
                case 1:
                    userService.registerUser();
                    break;
                case 2:
                    userService.loginUser();
                    if (Objects.nonNull(userService.getLoggedInUser())){
                        userMenu();
                    } else {
                        break;
                    }
                case 3:
                    hotelService.registerHotel();
                    break;
                case 4:

                    System.out.println("-----------------------");
                    System.out.println("1. To Edit Hotel profile.");
                    System.out.println("2. To Add Food item into your menu.");
                    System.out.println("Please enter from above: ");
                    int opt = scanner.nextInt();
                    if (opt == 1){
                        hotelService.updateHotelProfile();
                    } else if (opt == 2) {
                        System.out.print("Please Enter Hotel Name to add Food Item: ");
                        String hotelName = scanner.nextLine();
                        scanner.next();
                        System.out.print("Please enter food item name: ");
                        String foodName = scanner.nextLine();
                        scanner.next();
                        System.out.println("Please enter food item price: ");
                        double foodPrice = scanner.nextDouble();
                        FoodItem foodItem = new FoodItem(foodName, foodPrice);
                        hotelService.getHotelByName(hotelName).addFoodItems(foodItem);
                    }
                    break;
                case 5:
                    deliveryAgentService.registerDeliveryAgent();
                    break;
                case 6:
                    deliveryAgentService.loginDeliveryAgent();
                    break;
                case 7:
                    adminServices.loginAdmin();
                    break;
                case 8:
                    System.out.println("Closing Application....");
                    return;
                default:
                    System.out.println("Please Enter Valid Option");
                    break;
            }
        }
    }

}