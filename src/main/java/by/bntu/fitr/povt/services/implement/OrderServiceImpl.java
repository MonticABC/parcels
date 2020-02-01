package by.bntu.fitr.povt.services.implement;

import by.bntu.fitr.povt.services.OrderService;
import by.bntu.fitr.povt.dao.entities.Order;
import by.bntu.fitr.povt.dao.interfaces.OrderDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class OrderServiceImpl implements OrderService {
    OrderDao orderDao;


    public void setOrderDao(OrderDao orderDao) {
        this.orderDao = orderDao;
    }

    @Override
    @Transactional
    public int  add(Order order) {
        return orderDao.add(order);
    }

    @Override
    @Transactional
    public void remove(int id) {
        orderDao.remove(id);
    }

    @Override
    @Transactional
    public void update(Order order) {
        orderDao.update(order);
    }

    @Override
    @Transactional
    public List<Order> getList() {
        return orderDao.getList();
    }

    @Override
    @Transactional
    public Order getById(int id) {
        return orderDao.getById(id);
    }

    @Override
    @Transactional
    public List<Order> getListByUserId(int id) {
        return orderDao.getListByUserId(id);
    }

    @Override
    @Transactional
    public double getWeightByOrderId(int orderId) {
        return orderDao.getWeightByOrderId(orderId);
    }
}
