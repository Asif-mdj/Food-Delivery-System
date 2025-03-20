package Order;

import OrderService.OrderService;

public class Invoice {

    private Order order;
    private OrderService orderService;

    public Invoice (Order order) {
        this.order = order;
    }

}
