package service;

import models.Order;

import java.util.List;

public interface IOrderService {
    List<Order> getAllOrders();

    void add(Order order);

    Order findById(long orderId);

    boolean existByID(long id);

    void update(Order newOrder);
}
