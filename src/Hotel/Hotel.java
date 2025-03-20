package Hotel;

import java.util.*;

public class Hotel implements IUpdateHotelProfile, IEditFoodItem {

    private String hotelName;
    private String hotelLocation;
    private String hotelContactDetails;
    private List<FoodItem> menu = new ArrayList<>();
    Scanner scanner = new Scanner(System.in);
    public Hotel (String hotelName, String hotelLocation, String hotelContactDetails) {
        this.hotelName = hotelName;
        this.hotelLocation = hotelLocation;
        this.hotelContactDetails = hotelContactDetails;
    }

    public String getHotelContactDetails() {
        return this.hotelContactDetails;
    }

    public String getHotelLocation() {
        return this.hotelLocation;
    }

    public String getHotelName() {
        return this.hotelName;
    }

    @Override
    public void updateHotelProfile(String hotelName, String hotelLocation, String hotelContactDetails) {
        this.hotelName = hotelName;
        this.hotelLocation = hotelLocation;
        this.hotelContactDetails = hotelContactDetails;
    }

    public void addFoodItems (FoodItem foodItem) {
        menu.add(foodItem);
    }

    public void removeFoodItem (String foodName) {
        menu.removeIf(item -> item.getFoodName().equalsIgnoreCase(foodName));
    }

    @Override
    public List<FoodItem> getmenu () {
        return menu;
    }

}
