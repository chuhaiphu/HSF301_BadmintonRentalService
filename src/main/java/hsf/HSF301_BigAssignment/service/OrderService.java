package hsf.HSF301_BigAssignment.service;

import hsf.HSF301_BigAssignment.pojo.Order;
import hsf.HSF301_BigAssignment.pojo.OrderDTO;
import hsf.HSF301_BigAssignment.pojo.Slot;
import hsf.HSF301_BigAssignment.repo.OrderRepository;
import hsf.HSF301_BigAssignment.repo.SlotRepository;
import lombok.RequiredArgsConstructor;
import org.aspectj.weaver.ast.Or;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;

@RequiredArgsConstructor
@Service
public class OrderService implements IOrderService{
    private final OrderRepository orderRepository;
    private final SlotRepository slotRepository;

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

    public boolean areSlotsContinuous(List<Long> selectedSlotIds) {
        // Fetch the slots from the repository
        List<Slot> slots = selectedSlotIds.stream()
                .map(slotId -> slotRepository.findById(slotId)
                        .orElseThrow(() -> new IllegalArgumentException("Slot not found: " + slotId)))
                .sorted(Comparator.comparing(Slot::getStartTime))
                .toList();
        if (slots.size() == 1) return true;
        // Check for continuity
        for (int i = 1; i < slots.size(); i++) {
            Slot previousSlot = slots.get(i - 1);
            Slot currentSlot = slots.get(i);

            // Check if the start time of the current slot is before the end time of the previous slot
            if (!currentSlot.getStartTime().equals(previousSlot.getEndTime())) {
                return false; // Not continuous
            }
        }

        return true; // All slots are continuous
    }



    public double calculateTotalPrice(List<Long> selectedSlotIds) {
        return 0;
    }

    public void createOrder(OrderDTO orderDTO) {

    }

    @Override
    public List<Order> getByCourtId(Long courtId) {
        return orderRepository.findAllByCourt_Id(courtId);
    }
}
