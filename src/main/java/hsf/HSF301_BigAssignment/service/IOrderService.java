package hsf.HSF301_BigAssignment.service;

import hsf.HSF301_BigAssignment.pojo.Order;

import java.util.List;

public interface IOrderService {
    public List<Order> getAllOrders();

    public List<Order> getByUserId(Long id);

    public Order getOrderById(Long id);

    public Order saveOrder(Order order);

    public void deleteOrder(Long id);

    List<Order> searchOrders(String search);

    void disableOrder(Long id);
}
