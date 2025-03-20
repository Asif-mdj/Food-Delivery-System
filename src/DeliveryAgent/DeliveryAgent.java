package DeliveryAgent;

public class DeliveryAgent {

    public String deliveryAgentUserName;
    public String deliveryAgentContact;
    public String deliveryAgentPassword;

    public DeliveryAgent (String deliveryAgentUserName,
                          String deliveryAgentContact,
                          String deliveryAgentPassword) {

        this.deliveryAgentUserName = deliveryAgentUserName;
        this.deliveryAgentContact = deliveryAgentContact;
        this.deliveryAgentPassword = deliveryAgentPassword;
    }

    public String getDeliveryAgentUserName() {
        return deliveryAgentUserName;
    }

    public void setDeliveryAgentUserName(String deliveryAgentUserName) {
        this.deliveryAgentUserName = deliveryAgentUserName;
    }

    public String getDeliveryAgentContact() {
        return deliveryAgentContact;
    }

    public void setDeliveryAgentContact(String deliveryAgentContact) {
        this.deliveryAgentContact = deliveryAgentContact;
    }

    public String getDeliveryAgentPassword() {
        return deliveryAgentPassword;
    }

    public void setDeliveryAgentPassword(String deliveryAgentPassword) {
        this.deliveryAgentPassword = deliveryAgentPassword;
    }

}
