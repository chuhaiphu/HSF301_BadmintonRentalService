package hsf.HSF301_BigAssignment.service;

import hsf.HSF301_BigAssignment.pojo.Order;
import hsf.HSF301_BigAssignment.repo.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.aspectj.weaver.ast.Or;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class OrderService implements IOrderService{
    private final OrderRepository orderRepository;
    @Override
    public List<Order> getAllOrders() {
        return orderRepository.findAll();
    }

    @Override
    public List<Order> getByUserId(Long id) {
        return orderRepository.findAllByCustomer_Id(id);
    }

    @Override
    public Order getOrderById(Long id) {
        return orderRepository.findById(id).orElse(null);
    }

    @Override
    public Order saveOrder(Order order) {
        return orderRepository.save(order);
    }

    @Override
    public void deleteOrder(Long id) {
        orderRepository.deleteById(id);
    }

    @Override
    public List<Order> searchOrders(String search) {
        return orderRepository.findByCustomer_FirstNameContainingIgnoreCaseOrCustomer_LastNameContainingIgnoreCaseOrCourt_NameContainingIgnoreCase(search, search, search);
    }

    @Override
    public void disableOrder(Long id) {
        Order order = orderRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("Order not found"));
        order.setStatus(false);
        orderRepository.save(order);
    }
}
