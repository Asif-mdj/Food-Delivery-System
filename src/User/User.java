package User;

import java.util.Scanner;

public class User implements IUpdateUser{

    private String userName;
    private String userContact;
    private String userPassword;

    public User () {
        this.userName = "No User Found";
        this.userContact = "000000000000";
        this.userPassword = "xxxxxxxxxxxx";
    }
    public User (String userName, String userContact, String userPassword) {
        this.userName = userName;
        this.userContact = userContact;
        this.userPassword = userPassword;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserContact() {
        return userContact;
    }

    public void setUserContact(String userContact) {
        this.userContact = userContact;
    }

    public String getUserPassword() {
        return userPassword;
    }

    public void setUserPassword(String userPassword) {
        this.userPassword = userPassword;
    }

    public void updateUser () {

        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter your Existing UserName: ");
        String existingUserName = scanner.next();
        scanner.nextLine();
        System.out.print("Enter your Existing Password: ");
        String existingUserPassword = scanner.next();
        scanner.nextLine();

        if (this.getUserName().equals(existingUserName) && this.getUserPassword().equals(existingUserPassword)) {

            System.out.print("Enter New User Name: ");
            String userName = scanner.next();
            scanner.nextLine();
            System.out.print("Enter New Contact Number: ");
            String userContact = scanner.next();
            scanner.nextLine();
            System.out.print("Enter New Password: ");
            String userPassword = scanner.next();
            scanner.nextLine();

            this.userName = userName;
            this.userContact = userContact;
            this.userPassword = userPassword;

        } else {
            System.out.println("User Not Found");
        }

        System.out.println("Profile Updated Successfully.......");

    }

}
