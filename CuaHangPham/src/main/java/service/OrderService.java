package service;

import models.Order;
import utils.FileUtils;

import java.util.List;

public class OrderService  implements IOrderService {
    private final String pathOrder = "./data/order.csv";
    private static OrderService instance;
    public static OrderService getInstance() {
        if (instance == null)
            instance = new OrderService();
        return instance;
    }

    @Override
    public List<Order> getAllOrders() {
        return FileUtils.readData(pathOrder, Order.class);
    }

    @Override
    public void add(Order order) {
        List<Order> orders = getAllOrders();
        orders.add(order);
        FileUtils.writeDataToFile(pathOrder, orders);
    }

    @Override
    public Order findById(long orderId) {
        List<Order> orders = getAllOrders();
        for (Order order : orders) {
            if (order.getId() == orderId)
                return order;
        }
        return null;
    }

    @Override
    public boolean existByID(long id) {
        return findById(id) != null;
    }

    @Override
    public void update(Order newOrder) {
        List<Order> orders = getAllOrders();
        for (Order order : orders) {
            if (order.getId() == newOrder.getId()) {
                order.setFullName(newOrder.getFullName());
                order.setPhone(newOrder.getPhone());
                order.setGrandTotal(newOrder.getGrandTotal());

                FileUtils.writeDataToFile(pathOrder, orders);
                break;
            }
        }
    }
}
