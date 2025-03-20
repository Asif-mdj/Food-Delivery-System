package HotelService;

import Hotel.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Scanner;

public class HotelService implements RegisterHotel, UpdateHotelProfile, GetHotelByName, RemoveHotel, ViewHotels, GetHotelCount {

    private List<Hotel> hotels = new ArrayList<>();
    private Scanner scanner = new Scanner(System.in);
    private Hotel hotel;

    public List<Hotel> getHotels() {
        return hotels;
    }

    public void registerHotel() {

        System.out.print("Enter Hotel Name: ");
        String hotelName = scanner.next();
        scanner.nextLine();
        System.out.print("Enter Hotel Location: ");
        String hotelLocation = scanner.next();
        scanner.nextLine();
        System.out.print("Enter Hotel Contact Number: ");
        String hotelContactDetails = scanner.next();
        scanner.nextLine();

        hotel = new Hotel (hotelName, hotelLocation, hotelContactDetails);
        hotels.add(hotel);

        System.out.println("Please add 5 food items with price to add in your menu....");

        for (int i=1;i<=5;i++) {
            System.out.print(i + ") a. Enter Food Item Name: ");
            String foodName = scanner.next();
            scanner.nextLine();
            System.out.print("   b. Enter the price of the food item: ");
            Double foodPrice = scanner.nextDouble();
            FoodItem foodItem = new FoodItem(foodName, foodPrice);
            hotel.addFoodItems(foodItem);
        }
        System.out.println("Registration Successful!!");
        System.out.println("--------------------------");
    }

    public Hotel getHotelByName(String hotelName) {

        for (Hotel hotel : hotels) {
            if (hotel.getHotelName().equalsIgnoreCase(hotelName)){
                return hotel;
            }
        }
        return null;
    }

    public void updateHotelProfile () {

        System.out.println("Enter Hotel Name to update profile: ");
        String hotelName = scanner.nextLine();
        scanner.next();

        hotel = getHotelByName(hotelName);

        if (Objects.nonNull(hotel)) {
            System.out.println("Enter New Name: ");
            String newHotelName = scanner.nextLine();
            scanner.nextLine();
            System.out.println("Enter New Location: ");
            String newHotelLocation = scanner.nextLine();
            scanner.nextLine();
            System.out.println("Enter New Contact Details: ");
            String newHotelContactDetails = scanner.nextLine();
            scanner.nextLine();
            hotel.updateHotelProfile(newHotelName, newHotelLocation, newHotelContactDetails);
            System.out.println("Hotel Profile Updated Successfully!");
            System.out.println("-----------------------------------");
        } else {
            System.out.println("Hotel Not Found, Please do the registration first");
            System.out.println("-------------------------------------------------");
        }

    }

    public boolean removeHotel (String hotelName) {
        return hotels.removeIf(hotel -> hotel.getHotelName().equalsIgnoreCase(hotelName));
    }

    public void viewHotels () {

        if (hotels.isEmpty()){
            System.out.println("No Hotels Found");
            System.out.println("---------------");
        } else {
            System.out.println("Registered Hotels:-");
            System.out.println("-----------------");
            int i=1;
            for (Hotel hotel : hotels) {
                System.out.println(i+" Hotel Name: " + hotel.getHotelName());
                System.out.println("  Hotel Location: " + hotel.getHotelLocation());
                System.out.println("  Hotel Contact Details: " + hotel.getHotelContactDetails());
                System.out.println();
                i++;
            }
            System.out.println("--------------------------------------");
        }
    }

    public int getHotelCount () {
        return hotels.size();
    }

}
