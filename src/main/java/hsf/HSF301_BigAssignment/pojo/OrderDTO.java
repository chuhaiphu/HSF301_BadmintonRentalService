package hsf.HSF301_BigAssignment.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class OrderDTO {
    private Long id ;

    private Customer customer;

    private List<OrderDetail> orderDetails;

    private Court court;

    private Boolean status;

    private LocalDate bookDate;

    private double totalPrice;

    private List<Slot> slots;
}
