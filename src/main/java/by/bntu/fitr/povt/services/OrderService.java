package by.bntu.fitr.povt.services;

import by.bntu.fitr.povt.dao.entities.Order;

import java.util.List;

public interface OrderService {
    int add(Order order);
    void remove(int id);
    void update(Order order);
    List<Order> getList();
    Order getById(int id);

    List<Order> getListByUserId(int id);
    double getWeightByOrderId(int orderId);
}
