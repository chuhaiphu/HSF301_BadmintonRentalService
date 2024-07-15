package hsf.HSF301_BigAssignment.service;

import hsf.HSF301_BigAssignment.pojo.OrderDetail;

import java.util.List;

public interface IOrderDetailService {

    public List<OrderDetail> getAllOrderDetails();

    public List<OrderDetail> getByOrderId(Long id);

    public OrderDetail getOrderDetailById(Long id);

    public void saveOrderDetail(OrderDetail orderDetail);

    public void deleteOrderDetail(Long id);
}
