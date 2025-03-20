package DeliveryAgentServices;

import DeliveryAgent.DeliveryAgent;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class DeliveryAgentService implements RegisterDeliveryAgent, ViewDeliveryAgent,
        LoginDeliveryAgent, RemoveDeliveryAgent,
        GetLoggedInDeliveryAgent, GetDeliveryAgentByName{

    protected List<DeliveryAgent> deliveryAgents = new ArrayList<>();
    protected Scanner scanner = new Scanner(System.in);
    protected DeliveryAgent loggedInDeliveryAgent = null;

    public DeliveryAgent getDeliveryAgentByName(String deliveryAgentUserName) {

        for (DeliveryAgent Agent : deliveryAgents){

            if (Agent.getDeliveryAgentUserName().equalsIgnoreCase(deliveryAgentUserName)){
                return Agent;
            }
        }
        return null;
    }

    public void registerDeliveryAgent () {

        System.out.print("Enter Agent Name: ");
        String deliveryAgentName = scanner.next();
        scanner.nextLine();
        System.out.print("Enter Agent Password: ");
        String deliveryAgentPassword = scanner.next();
        scanner.nextLine();
        System.out.print("Enter Agent Contact Details: ");
        String deliveryAgentContact = scanner.next();
        scanner.nextLine();
        deliveryAgents.add(new DeliveryAgent(deliveryAgentName, deliveryAgentContact, deliveryAgentPassword));
        System.out.println("Registration process Completed!!!");
        System.out.println("----------------------------------");

    }

    public void loginDeliveryAgent() {

        System.out.println("Enter Agent Name: ");
        String agentName = scanner.next();
        scanner.nextLine();
        System.out.println("Enter Password: ");
        String deliveryAgentPassword = scanner.next();
        scanner.nextLine();

        DeliveryAgent deliveryAgent = getDeliveryAgentByName(agentName);
        if (deliveryAgent != null && deliveryAgent
                .getDeliveryAgentPassword().equals(deliveryAgentPassword)) {
            loggedInDeliveryAgent = deliveryAgent;
            System.out.println("Agent Login Successful");
            System.out.println("----------------------");
        }
        else {
            System.out.println("Invalid Agent Name and password, Please try again.....");
            System.out.println("------------------------------------------------------");
        }
    }

    public DeliveryAgent getLoggedInUser () {
        return loggedInDeliveryAgent;
    }

    public boolean removeDeliveryAgent (String deliveryAgentName) {
        return deliveryAgents.removeIf(deliveryAgent ->
                deliveryAgent.getDeliveryAgentUserName()
                        .equalsIgnoreCase(deliveryAgentName));
    }

    public void viewDeliveryAgent () {
        if (deliveryAgents.isEmpty()){
            System.out.println("Delivery Agent Not Found");
        } else{
            System.out.println("Registered Delivery Agent:-");

            for (DeliveryAgent deliveryAgent : deliveryAgents) {
                System.out.println("Delivery Agent Name: " + deliveryAgent.getDeliveryAgentUserName() );
                System.out.println("Delivery Agent Contact Details: "+deliveryAgent.getDeliveryAgentContact());
                System.out.println();
            }
            System.out.println("----------------------------------------");
        }
    }

}
