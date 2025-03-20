package UserServices;

import HotelService.HotelService;
import OrderService.IPlaceOrder;
import OrderService.OrderService;
import User.User;
import com.sun.tools.javac.Main;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class UserService implements IRegisterUser, ILoginUser,
                                    IGetUserByName, IRemoveUser,
                                    IViewUser, IGetUserCount, IGetLoggedInUser {

    private List<User> users = new ArrayList<>();
    private static Scanner scanner = new Scanner(System.in);
    private User loggedInUser = null;

    public User getUserByName (String userName) {

        for (User user: users){
            if (user.getUserName().equals(userName)){
                return user;
            }
        }
        return null;

    }

    public void registerUser () {

        System.out.print("Enter Username: ");
        String userName = scanner.next();
        scanner.nextLine();
        System.out.print("Enter Password: ");
        String userPassword = scanner.next();
        scanner.nextLine();
        int j = 2;
        for (int i=0; i<3;i++){
        System.out.print("Enter Contact number: ");
        String userContact = scanner.next();
        scanner.nextLine();
        Pattern pattern = Pattern.compile("(0|91)?[6-9][0-9]{9}");
        Matcher matcher = pattern.matcher(userContact);
        if (matcher.find()) {
           users.add(new User(userName, userContact, userPassword));
           System.out.println("Welcome " + userName + ", Registration successful!!!");
            System.out.println("-----------------------------------------------------");
           break;
        } else {
            System.out.println("Enter Valid Contact Number! only "+j+" tries left!!!!");
            j--;
        }
        }
        if (j<0) {
            System.out.println("Please try again with Valid Credentials!!!");
            System.out.println("--------------------------------------------");
        }

    }

    public void loginUser() {

        System.out.print("Enter Username: ");
        String userName = scanner.next();
        scanner.nextLine();
        System.out.print("Enter Password: ");
        String userPassword = scanner.next();
        scanner.nextLine();

        User user = getUserByName(userName);
        if (user != null && user.getUserPassword().equals(userPassword)) {
            loggedInUser = user;
            System.out.println("User Login Successful");
            System.out.println("--------------------------------");
            return;

        }
        System.out.println("Invalid username and password, Please try again.....");
        System.out.println("----------------------------------------------------");

    }

    public boolean removeUser (String userName) {

        for (User user: users) {
            if (user.getUserName().equals(userName)) {
                System.out.println("User found and deleting.....");
                System.out.println("----------------------------");
                return users.removeIf(users -> users.getUserName().equals(userName));
            }
        }
        return false;

    }

    public void viewUser () {

        if (users.isEmpty()) {
            System.out.println("No Users Found!");
            System.out.println("---------------");
        } else {
            System.out.println("Registered User:-");
            System.out.println("---------------");
            int i=1;
            for (User user : users) {
                System.out.print(i+". Username: " + user.getUserName());
                System.out.println("   User Contact: " + user.getUserContact());
                System.out.println();
                i++;
            }
            System.out.println("--------------------------------------");
        }

    }

    public int getUserCount () {

        return users.size();

    }

    public User getLoggedInUser() {

        return loggedInUser;

    }

}
