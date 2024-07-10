package hsf.HSF301_BigAssignment.pojo;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;
@Entity
@Data
public class Payment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "final_price")
    private Double finalPrice;

    @Column(name = "pay_at")
    private LocalDate payAt = LocalDate.now();


}
